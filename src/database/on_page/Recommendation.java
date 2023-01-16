package database.on_page;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.movie.MoviesInput;
import database.on_page.subscription.Notification;
import input.user.UsersInput;
import write.Write;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Recommendation {
    ArrayList<String> subscribedGenres;
    ArrayList<MoviesInput> allowedMovies;
    Notification recommendation;

    public Recommendation(final ArrayList<String> subscribedGenres, final ArrayList<MoviesInput> allowedMovies) {
        this.subscribedGenres = subscribedGenres;
        this.allowedMovies = allowedMovies;
    }

    public void notify(final UsersInput currentUser, final ArrayNode output) {
        createRecommendation(currentUser);
        Write.writeReccomandation(currentUser, output);
    }

    private ArrayList<String> createGenreTop(UsersInput currentUser) {
        ArrayList<MoviesInput> movies = currentUser.getLikedMovies();
        HashMap<String, Integer> genreTop = new HashMap<>();
        for (MoviesInput movie : movies) {
            for (String genre : movie.getGenres()) {
                int count = genreTop.getOrDefault(genre, 0);
                genreTop.put(genre, count + 1);
            }
        }

        ArrayList<String> genres = new ArrayList<>(genreTop.keySet());

        genres.sort((g1, g2) -> {
            int countDiff = genreTop.get(g2) - genreTop.get(g1);
            if (countDiff != 0) {
                return countDiff;
            }
            return g1.compareTo(g2);
        });

        return genres;
    }

    private ArrayList<MoviesInput> createTopMovies(ArrayList<MoviesInput> movies) {
        Collections.sort(movies, (o1, o2) -> o2.getNumLikes() - o1.getNumLikes());
        return movies;
    }

    private void createRecommendation(UsersInput currentUser) {
        ArrayList<String> genres = createGenreTop(currentUser);
        ArrayList<MoviesInput> movies = createTopMovies(allowedMovies);

        for (String genre : genres) {
            for (MoviesInput movie : movies) {
                if (movie.getGenres().contains(genre) && !currentUser.getWatchedMovies().contains(movie)) {
                    recommendation = new Notification(movie.getName(), "Recommendation");
                    currentUser.getNotifications().add(recommendation);
                    return;
                }
            }
        }

        recommendation = new Notification("No recommendation", "Recommendation");
        currentUser.getNotifications().add(recommendation);
    }
}

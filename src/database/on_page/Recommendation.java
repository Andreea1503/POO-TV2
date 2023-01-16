package database.on_page;

import com.fasterxml.jackson.databind.node.ArrayNode;
import input.movie.MoviesInput;
import database.on_page.subscription.Notification;
import input.user.UsersInput;
import write.Write;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Class that handles movie recommendations for a user.
 */
public class Recommendation {
    private ArrayList<String> subscribedGenres;
    private ArrayList<MoviesInput> allowedMovies;
    private Notification recommendation;

    public Recommendation(final ArrayList<String> subscribedGenres,
                          final ArrayList<MoviesInput> allowedMovies) {
        this.subscribedGenres = subscribedGenres;
        this.allowedMovies = allowedMovies;
    }

    /**
     * Notifies the user of a movie recommendation.
     *
     * @param currentUser the current user
     * @param output the output JSON object
     */
    public void notify(final UsersInput currentUser, final ArrayNode output) {
        createRecommendation(currentUser);
        Write.writeReccomandation(currentUser, output);
    }

    /**
     * Creates a top list of genres based on the movies the user liked.
     *
     * @param currentUser the current user
     * @return a list of the user's top genres
     */
    private ArrayList<String> createGenreTop(final UsersInput currentUser) {
        ArrayList<MoviesInput> movies = currentUser.getLikedMovies();
        HashMap<String, Integer> genreTop = new HashMap<>();
        for (MoviesInput movie : movies) {
            for (String genre : movie.getGenres()) {
                int count = genreTop.getOrDefault(genre, 0);
                genreTop.put(genre, count++);
            }
        }

        ArrayList<String> genres = new ArrayList<>(genreTop.keySet());

        genres.sort((g1, g2) -> {
            int diff = genreTop.get(g2) - genreTop.get(g1);
            if (diff != 0) {
                return diff;
            }
            return g1.compareTo(g2);
        });

        return genres;
    }

    /**
     *Helper method that creates a list of top movies for a user based on the allowed movies
     *@param movies list of allowed movies
     *@return list of top movies for the user
     */
    private ArrayList<MoviesInput> createTopMovies(final ArrayList<MoviesInput> movies) {
        Collections.sort(movies, (o1, o2) -> o2.getNumLikes() - o1.getNumLikes());
        return movies;
    }

    /**
     *Helper method that creates the personalized recommendation for a user
     *@param currentUser current user for whom the recommendation is being created
     */
    private void createRecommendation(final UsersInput currentUser) {
        ArrayList<String> genres = createGenreTop(currentUser);
        ArrayList<MoviesInput> movies = createTopMovies(allowedMovies);

        for (String genre : genres) {
            for (MoviesInput movie : movies) {
                if (movie.getGenres().contains(genre)
                        && !currentUser.getWatchedMovies().contains(movie)) {
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

package write;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import input.action.ActionsInput;
import input.movie.MoviesInput;
import input.user.UsersInput;

import java.util.ArrayList;

/**
 * Class that contains the methods that write the output
 */
public final class Write {
    private Write() {
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Method that writes the output
     * @param currentUser
     * @param action
     * @param output
     */
    public static void writePageError(final UsersInput currentUser, final ActionsInput action,
                                      final ArrayNode output) {
        ObjectNode loginError = objectMapper.createObjectNode();

        loginError.put("error", action.getError());

        if (currentUser != null) {
            ArrayNode movies = movieList(currentUser.getCurrentMoviesList());
            loginError.set("currentMoviesList", movies);
        } else {
            ArrayNode movies = movieList(new ArrayList<>());
            loginError.set("currentMoviesList", movies);
        }

        if (currentUser != null) {
            ObjectNode user = user(currentUser);
            loginError.set("currentUser", user);
        } else {
            loginError.set("currentUser", null);
        }
        output.add(loginError);
    }

    /**
     * Method that writes the movie list in the output
     * @param currentMoviesList
     */
    public static ArrayNode movieList(final ArrayList<MoviesInput> currentMoviesList) {
        ArrayNode movies = objectMapper.createArrayNode();

        if (currentMoviesList != null) {
            for (int i = 0; i < currentMoviesList.size(); i++) {
                ObjectNode movie = movie(currentMoviesList.get(i));
                movies.add(movie);
            }
        }

        return movies;
    }

    /**
     * Method that writes the movie in the output
     * @param movie
     */
    public static ObjectNode movie(final MoviesInput movie) {
        ObjectNode movieOutput = objectMapper.createObjectNode();

        movieOutput.put("name", movie.getName());
        movieOutput.put("year", movie.getYear());
        movieOutput.put("duration", movie.getDuration());

        ArrayNode genres = objectMapper.createArrayNode();
        for (int i = 0; i < movie.getGenres().size(); i++) {
            genres.add(movie.getGenres().get(i));
        }
        movieOutput.set("genres", genres);

        ArrayNode actors = objectMapper.createArrayNode();
        for (int i = 0; i < movie.getActors().size(); i++) {
            actors.add(movie.getActors().get(i));
        }
        movieOutput.set("actors", actors);

        ArrayNode countriesBanned = objectMapper.createArrayNode();
        for (int i = 0; i < movie.getCountriesBanned().size(); i++) {
            countriesBanned.add(movie.getCountriesBanned().get(i));
        }
        movieOutput.set("countriesBanned", countriesBanned);

        movieOutput.put("numLikes", movie.getNumLikes());
        movieOutput.put("numRatings", movie.getNumRatings());
        movieOutput.put("rating", movie.getRating());

        return movieOutput;
    }

    /**
     * Method that writes the user in the output
     * @param currentUser
     */
    public static ObjectNode user(final UsersInput currentUser) {
        ObjectNode user = objectMapper.createObjectNode();

        ObjectNode credentials = objectMapper.createObjectNode();

        if (currentUser != null && currentUser.getCredentials() != null) {
        credentials.put("name", currentUser.getCredentials().getName());
        credentials.put("password", currentUser.getCredentials().getPassword());
        credentials.put("accountType", currentUser.getCredentials().getAccountType());
        credentials.put("country", currentUser.getCredentials().getCountry());
        credentials.put("balance", currentUser.getCredentials().getBalance());

        user.set("credentials", credentials);

        user.put("tokensCount", currentUser.getTokensCount());
        user.put("numFreePremiumMovies", currentUser.getNumFreePremiumVideos());
        ArrayNode purchasedMovies = objectMapper.createArrayNode();
        purchasedMovies.addAll(movieList(currentUser.getPurchasedMovies()));
        user.set("purchasedMovies", purchasedMovies);

        ArrayNode watchedMovies = objectMapper.createArrayNode();
        watchedMovies.addAll(movieList(currentUser.getWatchedMovies()));
        user.set("watchedMovies", watchedMovies);

        ArrayNode likedMovies = objectMapper.createArrayNode();
        likedMovies.addAll(movieList(currentUser.getLikedMovies()));
        user.set("likedMovies", likedMovies);

        ArrayNode ratedMovies = objectMapper.createArrayNode();
        ratedMovies.addAll(movieList(currentUser.getRatedMovies()));
        user.set("ratedMovies", ratedMovies);

        ArrayNode notifications = objectMapper.createArrayNode();
        for (int i = 0; i < currentUser.getNotifications().size(); i++) {
            ObjectNode notification = objectMapper.createObjectNode();
            notification.put("message", currentUser.getNotifications().get(i).getMessage());
            notification.put("movieName", currentUser.getNotifications().get(i).getMovieName());
            notifications.add(notification);
        }
        user.set("notifications", notifications);
        }
        return user;
    }

    /**
     * Method that writes the output for the recommendation
     * @param currentUser
     * @param output
     */
    public static void writeReccomandation(final UsersInput currentUser,
                                           final ArrayNode output) {
        ObjectNode loginError = objectMapper.createObjectNode();

        String error = null;
        loginError.put("error", error);

        loginError.set("currentMoviesList", null);

        if (currentUser != null) {
            ObjectNode user = user(currentUser);
            loginError.set("currentUser", user);
        }

        output.add(loginError);
    }

}

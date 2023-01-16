package input.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import database.on_page.subscription.Observer;
import input.movie.MoviesInput;
import database.on_page.subscription.Notification;

import java.util.ArrayList;
/**
 * Class that contains the users input
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersInput implements Observer {
    private CredentialsInput credentials;
    private int tokensCount;
    private int numFreePremiumVideos;
    private ArrayList<MoviesInput> purchasedMovies;
    private ArrayList<MoviesInput> watchedMovies;
    private ArrayList<MoviesInput> likedMovies;
    private ArrayList<MoviesInput> ratedMovies;
    private ArrayList<MoviesInput> currentMoviesList;
    private ArrayList<Notification> notifications;
    private ArrayList<String> subscribedGenres;

    private boolean rated;


    public UsersInput() {
        this.tokensCount = 0;
        this.numFreePremiumVideos = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
        this.currentMoviesList = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.subscribedGenres = new ArrayList<>();
        this.rated = false;
    }

    public UsersInput(final CredentialsInput credentials) {
        this.credentials = credentials;
        this.tokensCount = 0;
        this.numFreePremiumVideos = 15;
        this.purchasedMovies = new ArrayList<>();
        this.watchedMovies = new ArrayList<>();
        this.likedMovies = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
        this.currentMoviesList = new ArrayList<>();
        this.notifications = new ArrayList<>();
        this.subscribedGenres = new ArrayList<>();
        this.rated = false;
    }

    @Override
    public void update(final Notification notification) {
        this.notifications.add(notification);
    }
    /**
     * Method that returns the credentials of the user
     * @return credentials
     */
    public CredentialsInput getCredentials() {
        return credentials;
    }

    /**
     * Method that sets the credentials of the user
     * @param credentials
     */
    public void setCredentials(final CredentialsInput credentials) {
        this.credentials = credentials;
    }

    /**
     * Method that returns the number of tokens of the user
     * @return tokensCount
     */
    public int getTokensCount() {
        return tokensCount;
    }

    /**
     * Method that sets the number of tokens of the user
     * @param tokensCount
     */
    public void setTokensCount(final int tokensCount) {
        this.tokensCount = tokensCount;
    }

    /**
     * Method that returns the number of free premium videos of the user
     * @return numFreePremiumVideos
     */
    public int getNumFreePremiumVideos() {
        return numFreePremiumVideos;
    }

    /**
     * Method that sets the number of free premium videos of the user
     * @param numFreePremiumVideos
     */
    public void setNumFreePremiumVideos(final int numFreePremiumVideos) {
        this.numFreePremiumVideos = numFreePremiumVideos;
    }

    /**
     * Method that returns the list of purchased movies of the user
     * @return purchasedMovies
     */
    public ArrayList<MoviesInput> getPurchasedMovies() {
        return purchasedMovies;
    }

    /**
     * Method that sets the list of purchased movies of the user
     * @param purchasedMovies
     */
    public void setPurchasedMovies(final ArrayList<MoviesInput> purchasedMovies) {
        this.purchasedMovies = purchasedMovies;
    }

    /**
     * Method that returns the list of watched movies of the user
     * @return watchedMovies
     */
    public ArrayList<MoviesInput> getWatchedMovies() {
        return watchedMovies;
    }

    /**
     * Method that sets the list of watched movies of the user
     * @param watchedMovies
     */
    public void setWatchedMovies(final ArrayList<MoviesInput> watchedMovies) {
        this.watchedMovies = watchedMovies;
    }

    /**
     * Method that returns the list of liked movies of the user
     * @return likedMovies
     */
    public ArrayList<MoviesInput> getLikedMovies() {
        return likedMovies;
    }

    /**
     * Method that sets the list of liked movies of the user
     * @param likedMovies
     */
    public void setLikedMovies(final ArrayList<MoviesInput> likedMovies) {
        this.likedMovies = likedMovies;
    }

    /**
     * Method that returns the list of rated movies of the user
     * @return ratedMovies
     */
    public ArrayList<MoviesInput> getRatedMovies() {
        return ratedMovies;
    }

    /**
     * Method that sets the list of rated movies of the user
     * @param ratedMovies
     */
    public void setRatedMovies(final ArrayList<MoviesInput> ratedMovies) {
        this.ratedMovies = ratedMovies;
    }

    /**
     * Method that returns the list of current movies of the user
     * @return currentMoviesList
     */
    public ArrayList<MoviesInput> getCurrentMoviesList() {
        return currentMoviesList;
    }

    /**
     * Method that sets the list of current movies of the user
     * @param currentMoviesList
     */
    public void setCurrentMoviesList(final ArrayList<MoviesInput> currentMoviesList) {
        this.currentMoviesList = currentMoviesList;
    }

    /**
     * Method that returns the notifications received by the user
     *
     * @return filteredMovies
     */
    public ArrayList<Notification> getNotifications() {
        return notifications;
    }

    /**
     * Method that sets the notifications received by the user
     * @param notifications
     */
    public void setNotifications(ArrayList<Notification> notifications) {
        this.notifications = notifications;
    }

    /**
     * Method that returns the list of subscribed genres of the user
     * @return subscribedGenres
     */
    public ArrayList<String> getSubscribedGenres() {
        return subscribedGenres;
    }

    /**
     * Method that sets the list of subscribed genres of the user
     * @param subscribedGenres
     */
    public void setSubscribedGenres(ArrayList<String> subscribedGenres) {
        this.subscribedGenres = subscribedGenres;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }
}

package database.on_page.subscription;

/**
 * The Observer interface defines a method for updating an observer with a new notification.
 * Classes that implement this interface are able to receive notifications from a subject
 * (class that implements the Subject interface)
 */
public interface Observer {
    /**
     * This method is called by the subject when a new notification is available.
     * @param notification the new notification that the observer should be updated with.
     */
    void update(Notification notification);
}

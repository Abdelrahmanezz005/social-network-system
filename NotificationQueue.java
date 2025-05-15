import java.util.LinkedList;
import java.util.Queue;

public class NotificationQueue {
    Queue<String> notifications = new LinkedList<>();

    public void addNotification(String notification) {
        notifications.offer(notification);
    }

    public String removeNotification() {
        return notifications.poll();
    }

    public void sendNotifications() {
        while (!notifications.isEmpty()) {
            System.out.println("Notification: " + notifications.poll());
        }
    }
}

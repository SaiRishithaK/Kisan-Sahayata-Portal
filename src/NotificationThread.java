//Course Based Project
//NotificationThread.java
package threads;

import data.NotificationManager;
import model.Notification;

public class NotificationThread extends Thread {

    private String farmerId;

    public NotificationThread(String farmerId) {
        this.farmerId = farmerId;
    }

    @Override
    public void run() {
        NotificationManager nDAO = new NotificationManager();

        while (true) {
            try {
                Thread.sleep(30000); // 30 seconds
            } catch (Exception e) {}

            // Add a dummy notification
            nDAO.addNotification(farmerId, 
                new Notification("Remember to check today's market prices!"));
        }
    }
}

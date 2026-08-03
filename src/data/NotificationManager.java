//Course Based Project
//NotificationManager.java
package data;

import model.Notification;
import java.io.*;
import java.util.*;

public class NotificationManager {

    private static final String FILE_PATH = "notifications.dat";

    // Load all notifications (HashMap<String farmerId, List<Notification>>)
    private HashMap<String, List<Notification>> loadAll() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (HashMap<String, List<Notification>>) ois.readObject();
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    // Save all
    private void saveAll(HashMap<String, List<Notification>> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(data);
        } catch (Exception e) {
            System.out.println("Error saving notifications");
        }
    }

    // Add notification for a farmer
    public void addNotification(String farmerId, Notification n) {
        HashMap<String, List<Notification>> data = loadAll();

        data.putIfAbsent(farmerId, new ArrayList<>());
        data.get(farmerId).add(n);

        saveAll(data);
    }

    // Get notifications of a farmer
    public List<Notification> getNotifications(String farmerId) {
        HashMap<String, List<Notification>> data = loadAll();

        return data.getOrDefault(farmerId, new ArrayList<>());
    }
}

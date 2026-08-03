//Course Based Project
//NotificationsFrame.java
package ui;

import data.NotificationManager;
import model.Notification;
import model.Farmer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class NotificationsFrame extends JFrame {

    public NotificationsFrame(Farmer farmer) {

        setTitle("Notifications");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        NotificationManager dao = new NotificationManager();
        List<Notification> list = dao.getNotifications(farmer.getFarmerID());

        JTextArea area = new JTextArea();
        area.setEditable(false);

        if (list.isEmpty()) {
            area.setText("No notifications.");
        } else {
            for (Notification n : list) {
                area.append(n.toString() + "\n\n");
            }
        }

        add(new JScrollPane(area), BorderLayout.CENTER);

        setVisible(true);
    }
}

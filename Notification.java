//Couse Based Project
//Notification.java
package model;

import java.io.Serializable;

public class Notification implements Serializable {
    protected String message;
    protected long time;

    public Notification(String message) {
        this.message = message;
        this.time = System.currentTimeMillis();
    }

    public String getMessage() {
        return message;
    }

    public String getFormattedTime() {
        return new java.util.Date(time).toString();
    }

    @Override
    public String toString() {
        return "[" + getFormattedTime() + "] " + message;
    }
}
s
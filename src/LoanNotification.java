//Course Based Project
//LoanNotification.java
package model;

public class LoanNotification extends Notification {

    public LoanNotification(String message) {
        super("LOAN UPDATE: " + message);
    }
}

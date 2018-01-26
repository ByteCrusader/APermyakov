package ru.apermyakov.servlets;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.List;

@ThreadSafe
public class Sessions {

    /**
     * Field for INSTANCE.
     */
    @GuardedBy("this")
    private static final Sessions INSTANCE = new Sessions();

    /**
     * Field for map of sessions.
     */
    private List<String> sessions = new LinkedList<>();

    /**
     * Private design of sessions.
     */
    private Sessions() { }

    /**
     * Method for get sessions INSTANCE.
     *
     * @return INSTANCE
     */
    public static Sessions getInstance() {
        return INSTANCE;
    }

    public boolean isContainCookie(String cookie) {
        return this.sessions.contains(cookie);
    }

    public void putSession(String cookie) {
        this.sessions.add(cookie);
    }

    public void deleteSession(String cookie) {
        this.sessions.remove(cookie);
    }
}

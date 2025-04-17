package Utils;

import Models.KhachHang;

public class UserSession {
    private static KhachHang currentUser;

    public static KhachHang getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(KhachHang user) {
        currentUser = user;
    }

    public static void clearSession() {
        currentUser = null;
    }
}
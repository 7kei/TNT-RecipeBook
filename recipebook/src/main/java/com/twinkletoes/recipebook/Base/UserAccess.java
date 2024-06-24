package com.twinkletoes.recipebook.Base;

public enum UserAccess {
    EMPLOYEE,
    MANAGER,
    ADMIN;

    public static UserAccess fromInt(int num) {
        return UserAccess.values()[num];
    }

    public static boolean isAccessible(UserAccess recipe, UserAccess user) {
        if (recipe == ADMIN && user == ADMIN) { return true; }
        if (recipe == MANAGER && (user == ADMIN || user == MANAGER)) { return true; }
        if (recipe == EMPLOYEE && (user == ADMIN || user == MANAGER || user == EMPLOYEE)) { return true; }
        return false;
    }

    public static String toString(UserAccess in) {
        String toReturn = "Unknown"; 
        switch (in) {
            case ADMIN:
                toReturn = "Admins Only";
                break;
            case MANAGER:
                toReturn = "Admins and Managers";
                break;
            case EMPLOYEE:
                toReturn = "All Employees";
                break;
            default:
                break;
        }
        return toReturn;
    }
}

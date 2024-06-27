package com.twinkletoes.recipebook.Base;

public enum UserAccess {
    EMPLOYEE,
    MANAGER,
    ADMIN,
    UNKNOWN;

    public static UserAccess fromInt(int num) {
        switch (num) {
            case 0:
                return EMPLOYEE;
            case 1:
                return MANAGER;
            case 2:
                return ADMIN;
        }
        return UNKNOWN;
    }

    public static int toInt(UserAccess inputAccess) {
        switch (inputAccess) {
            case EMPLOYEE:
                return 0;
            case MANAGER:
                return 1;
            case ADMIN:
                return 2;
            case UNKNOWN:
                return -1;
        }
        return -1;
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

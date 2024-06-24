package com.twinkletoes.recipebook.Base;

public enum UserAccess {
    EMPLOYEE,
    MANAGER,
    ADMIN;

    public static UserAccess fromInt(int num) {
        return UserAccess.values()[num];
    }

    public static boolean isAccessible(UserAccess recipe, UserAccess user) {
        switch (recipe) {
            case EMPLOYEE:
                if (user == EMPLOYEE) return true;
            case MANAGER:
                if (user == MANAGER) return true;
            case ADMIN:
                return true;
            default:
                return false;
        }
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

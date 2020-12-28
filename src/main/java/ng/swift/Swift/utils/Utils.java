package ng.swift.Swift.utils;

public class Utils {
    public static String formatPhoneNumber(String phoneNumber) {
        if (phoneNumber.contains("+234")) {
            return phoneNumber;
        } else {
            return "+234" + phoneNumber.substring(1);
        }
    }

    public static boolean isPhoneNumber(String identifier) {
        return !identifier.contains("@");
    }
}

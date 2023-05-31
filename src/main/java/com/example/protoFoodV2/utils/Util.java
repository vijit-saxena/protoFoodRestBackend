package com.example.protoFoodV2.utils;

import java.security.InvalidParameterException;

public class Util {
    private static final double EARTH_RADIUS = 6371;
    public static String refactorPhoneNumber(String contact) {
        // remove any leading/trailing spaces
        contact = contact.trim();
        if (contact.length() < 10) {
            throw new InvalidParameterException("Invalid contact data. Length should be " +
                    "greater than or equal to 10. Found : " + contact.length());
        }
        return "+91" + contact.substring(contact.length() - 10);
    }

    // Source -> ChatGPT
    public static double calculateLatLngDistance(double lat1, double lon1, double lat2, double lon2) {
        // Convert latitude and longitude to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Calculate the differences between coordinates
        double latDiff = lat2Rad - lat1Rad;
        double lonDiff = lon2Rad - lon1Rad;

        // Calculate the distance using the Haversine formula
        double a = Math.sin(latDiff / 2) * Math.sin(latDiff / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(lonDiff / 2) * Math.sin(lonDiff / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;

        return distance;
    }
}

package org.firstinspires.ftc.swerveteen750.helpers;

public class ColorHelper {
    public static int hue(int hsv) {
        // Get the upper 16 bits for the hue
        return hsv >> 16;
    }

    public static int red(int rgb) {
        return (rgb >> 16) & 0xFF;
    }

    public static int blue(int rgb) {
        return rgb & 0xFF;
    }

    public static int sat(int hsv) {
        // Get the 'middle' 8 bits for the saturation
        return (hsv >> 8) & 0xFF;
    }

    public static int val(int hsv) {
        return hsv & 0xFF;
    }

    // wrap is the max value of the hue to allow detection "around" 0 degrees
    // OpenCV wraps at 180, while the Color Sensor's HSV values appear to wrap at 360
    public static boolean isColor(int hsv, int h, int h_range, int sat_min, int val_min, int wrap) {
        if (sat(hsv) < sat_min && val(hsv) < val_min) {
            return false;
        }
        int hh = hue(hsv);
        // This tells us the shortest distance between h and hh, given a 'wrap-around'
        return Math.min(Math.floorMod(h - hh, wrap), Math.floorMod(hh - h, wrap)) <= h_range;
    }

    // Default for isColor wrap-around is 360...
    public static boolean isColor(int hsv, int h, int h_range, int sat_min, int val_min) {
        return isColor(hsv, h, h_range, sat_min, val_min, 360);
    }
}

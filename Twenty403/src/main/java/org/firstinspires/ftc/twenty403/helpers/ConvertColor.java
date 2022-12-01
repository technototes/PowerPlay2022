package org.firstinspires.ftc.twenty403.helpers;

public class ConvertColor {
    // Shamelessly stolen from https://www.rapidtables.com/convert/color/rgb-to-hsv.html
    // Modified to return hue as a 0-180, not 0-360, because that's what OpenCV does, too...
    public static int rgbToHsv(int rgb) {
        double r = ((rgb >> 16) & 0xFF) / 255.0;
        double g = ((rgb >> 8) & 0xFF) / 255.0;
        double b = (rgb & 0xFF) / 255.0;
        double cmin = Math.min(Math.min(r, g), b);
        double cmax = Math.max(Math.max(r, g), b);
        double delta = cmax - cmin;
        int h = 0;
        if (delta == 0.0) {
            h = 0; // No difference: we're a gray scale!
        } else if (cmax == r) {
            // The modulo operation should make the values "wrap around" from 5 to 0,
            // instead of going from -1 to +1
            h = 60 * Math.floorMod((int) ((g - b) / delta), 6);
        } else if (cmax == g) {
            h = 60 * ((int) ((b - r) / delta) + 2);
        } else {
            // cmax == b
            h = 60 * ((int) ((r - g) / delta) + 4);
        }
        int s = cmax == 0.0 ? 0 : (int) Math.floor(delta / cmax);
        return ((h / 2) << 16) | (s << 8) | (int) (cmax * 255);
    }

    public static int hue(int hsv) {
        // Get the upper 16 bits for the hue
        return hsv >> 16;
    }

    public static int sat(int hsv) {
        // Get the 'middle' 8 bits for the saturation
        return (hsv >> 8) & 0xFF;
    }

    public static int val(int hsv) {
        return hsv & 0xFF;
    }

    public static boolean isColor(int hsv, int h, int h_range, int sat_min, int val_min) {
        if (sat(hsv) < sat_min && val(hsv) < val_min) {
            return false;
        }
        if (h < h_range) {
            // Deal with wrap-around for reddish hues...
            int hh = hue(hsv);
            return (hh <= h + h_range || hh >= 180 + h - h_range);
        } else {
            return Math.abs(hue(hsv) - h) <= h_range;
        }
    }
}

package io.github.marine.utils;

import java.text.DateFormat;

public final class TimeUtil {

    private TimeUtil() {}

    public static String formatTimeFromMillis(long millis) {

        int months = (int) (millis / (1000L * 60 * 60 * 24 * 30));
        int days = (int) ((millis % (1000L * 60 * 60 * 24 * 30)) / (1000L * 60 * 60 * 24));
        int hours = (int) ((millis % (1000L * 60 * 60 * 24)) / (1000L * 60 * 60));

        return String.format("%d months %d days %d hours", months, days, hours);
    }
}

package org.cloudfoundry.multiapps.controller.core.util;

public final class StringPadUtil {

    private StringPadUtil() {
    }

    public static String padLeft(String input, int totalWidth, char padChar) {
        if (input == null) {
            throw new IllegalArgumentException("input must not be null");
        }
        if (totalWidth <= input.length()) {
            return input;
        }
        StringBuilder builder = new StringBuilder(totalWidth);
        for (int i = 0, missing = totalWidth - input.length(); i < missing; i++) {
            builder.append(padChar);
        }
        builder.append(input);
        return builder.toString();
    }

    public static String padRight(String input, int totalWidth, char padChar) {
        if (input == null) {
            throw new IllegalArgumentException("input must not be null");
        }
        if (totalWidth <= input.length()) {
            return input;
        }
        StringBuilder builder = new StringBuilder(totalWidth);
        builder.append(input);
        for (int i = 0, missing = totalWidth - input.length(); i < missing; i++) {
            builder.append(padChar);
        }
        return builder.toString();
    }

    public static boolean isPaddedWith(String input, char padChar) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.charAt(0) == padChar || input.charAt(input.length() - 1) == padChar;
    }
}

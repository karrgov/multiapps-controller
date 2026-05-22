package org.cloudfoundry.multiapps.controller.core.util;

public final class LogLevelClassifier {

    private LogLevelClassifier() {

    }

    public static String classify(String level) {
        if (level == null) {
            return "UNKNOWN";
        }
        String normalized = level.trim()
                                 .toUpperCase();
        if (normalized.equals("INFO")) {
            return "INFO";
        }
        if (normalized.equals("WARN") || normalized.equals("WARNING")) {
            return "WARN";
        }
        if (normalized.equals("ERROR") || normalized.equals("ERR")) {
            return "ERROR";
        }
        return "UNKNOWN";
    }

}

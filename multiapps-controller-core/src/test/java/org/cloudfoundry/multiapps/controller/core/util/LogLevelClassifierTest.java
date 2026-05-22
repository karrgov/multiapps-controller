package org.cloudfoundry.multiapps.controller.core.util;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogLevelClassifierTest {

    static Stream<Arguments> testClassify() {
        return Stream.of(
                         // null input
                         Arguments.of(null, "UNKNOWN"),
                         // exact canonical values
                         Arguments.of("INFO", "INFO"),
                         Arguments.of("WARN", "WARN"),
                         Arguments.of("WARNING", "WARN"),
                         Arguments.of("ERROR", "ERROR"),
                         Arguments.of("ERR", "ERROR"),
                         // whitespace-padded inputs
                         Arguments.of("  INFO  ", "INFO"),
                         Arguments.of("\tWARN\n", "WARN"),
                         Arguments.of(" WARNING", "WARN"),
                         Arguments.of("ERROR ", "ERROR"),
                         Arguments.of("  ERR  ", "ERROR"),
                         // mixed-case inputs
                         Arguments.of("info", "INFO"),
                         Arguments.of("Info", "INFO"),
                         Arguments.of("warn", "WARN"),
                         Arguments.of("Warning", "WARN"),
                         Arguments.of("warning", "WARN"),
                         Arguments.of("Error", "ERROR"),
                         Arguments.of("error", "ERROR"),
                         Arguments.of("err", "ERROR"),
                         Arguments.of("Err", "ERROR"),
                         // mixed-case combined with whitespace
                         Arguments.of("  Info  ", "INFO"),
                         Arguments.of("\twarning\t", "WARN"),
                         // unrecognized strings
                         Arguments.of("", "UNKNOWN"),
                         Arguments.of("   ", "UNKNOWN"),
                         Arguments.of("DEBUG", "UNKNOWN"),
                         Arguments.of("TRACE", "UNKNOWN"),
                         Arguments.of("FATAL", "UNKNOWN"),
                         Arguments.of("INFORMATION", "UNKNOWN"),
                         Arguments.of("WARNS", "UNKNOWN"),
                         Arguments.of("ERRORS", "UNKNOWN"),
                         Arguments.of("12345", "UNKNOWN"),
                         Arguments.of("INFO WARN", "UNKNOWN"));
    }

    @ParameterizedTest
    @MethodSource
    void testClassify(String input, String expected) {
        assertEquals(expected, LogLevelClassifier.classify(input));
    }

}

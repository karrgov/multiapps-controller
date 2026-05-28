package org.cloudfoundry.multiapps.controller.core.util;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringPadUtilTest {

    static Stream<Arguments> testPadLeft() {
        return Stream.of(Arguments.of("42", 5, '0', "00042"),
                         Arguments.of("abc", 3, '*', "abc"),
                         Arguments.of("abcdef", 4, '*', "abcdef"),
                         Arguments.of("", 3, 'x', "xxx"),
                         Arguments.of("x", 0, '0', "x"));
    }

    static Stream<Arguments> testPadRight() {
        return Stream.of(Arguments.of("42", 5, '0', "42000"),
                         Arguments.of("abc", 3, '*', "abc"),
                         Arguments.of("abcdef", 4, '*', "abcdef"),
                         Arguments.of("", 3, 'x', "xxx"),
                         Arguments.of("x", 0, '0', "x"));
    }

    static Stream<Arguments> testIsPaddedWith() {
        return Stream.of(Arguments.of(null, '0', false),
                         Arguments.of("", '0', false),
                         Arguments.of("00042", '0', true),
                         Arguments.of("42000", '0', true),
                         Arguments.of("00042000", '0', true),
                         Arguments.of("42", '0', false),
                         Arguments.of("0", '0', true),
                         Arguments.of("abc", '0', false));
    }

    @ParameterizedTest
    @MethodSource
    void testPadLeft(String input, int totalWidth, char padChar, String expected) {
        assertEquals(expected, StringPadUtil.padLeft(input, totalWidth, padChar));
    }

    @ParameterizedTest
    @MethodSource
    void testPadRight(String input, int totalWidth, char padChar, String expected) {
        assertEquals(expected, StringPadUtil.padRight(input, totalWidth, padChar));
    }

    @ParameterizedTest
    @MethodSource
    void testIsPaddedWith(String input, char padChar, boolean expected) {
        if (expected) {
            assertTrue(StringPadUtil.isPaddedWith(input, padChar));
        } else {
            assertFalse(StringPadUtil.isPaddedWith(input, padChar));
        }
    }

    @Test
    void testPadLeftThrowsOnNullInput() {
        assertThrows(IllegalArgumentException.class, () -> StringPadUtil.padLeft(null, 5, '0'));
    }

    @Test
    void testPadRightThrowsOnNullInput() {
        assertThrows(IllegalArgumentException.class, () -> StringPadUtil.padRight(null, 5, '0'));
    }
}

/*
 * Copyright (c) 2019-2021, Istomin Andrei
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.aistomin.testist.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The tests for {@link SimpleAnswer}.
 *
 * @since 0.1
 */
final class SimpleAnswerTest {

    /**
     * Check that we correctly validate the answers.
     */
    @Test
    void testValidate() {
        final String correct = "Correct answer";
        final String wrong = "Wrong answer";
        Assertions.assertTrue(
            new SimpleAnswer(correct).validate(new SimpleAnswer(correct))
        );
        Assertions.assertFalse(
            new SimpleAnswer(wrong).validate(new SimpleAnswer(correct))
        );
        Assertions.assertFalse(
            new SimpleAnswer(correct).validate(new SimpleAnswer(wrong))
        );
    }

    /**
     * Check that we correctly convert the answer to the JSON string.
     */
    @Test
    void testToJsonString() {
        final String expected = "Expected answer";
        Assertions.assertEquals(
            expected, new SimpleAnswer(expected).toJson().get("text")
        );
    }

    /**
     * Check that we correctly display the answer.
     */
    @Test
    void testToDisplayableString() {
        final String expected = "Some text";
        Assertions.assertEquals(
            expected, new SimpleAnswer(expected).toDisplayableString()
        );
    }

    /**
     * Check that we ignore any occasional trailing spaces in the answer.
     */
    @Test
    void testTrailingSpaces() {
        final String expected = "Normalised text.";
        Assertions.assertEquals(
            expected,
            new SimpleAnswer("Normalised text. ").toDisplayableString()
        );
        Assertions.assertEquals(
            expected,
            new SimpleAnswer("Normalised text.   ").toDisplayableString()
        );
        Assertions.assertEquals(
            expected,
            new SimpleAnswer(" Normalised text.").toDisplayableString()
        );
        Assertions.assertEquals(
            expected,
            new SimpleAnswer("  Normalised text.").toDisplayableString()
        );
        Assertions.assertEquals(
            expected,
            new SimpleAnswer(" Normalised text. ").toDisplayableString()
        );
        Assertions.assertEquals(
            expected,
            new SimpleAnswer("  Normalised text.  ").toDisplayableString()
        );
    }

    /**
     * Check that we ignore any occasionally repeated spaces within the answer.
     */
    @Test
    void testIgnoreRepeatedSpaces() {
        final String expected = "Some text with a lot of spaces.";
        Assertions.assertEquals(
            expected,
            new SimpleAnswer("Some  text   with a    lot   of  spaces.")
                .toDisplayableString()
        );
    }
}

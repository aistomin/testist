/*
 * Copyright (c) 2019, Istomin Andrei
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

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test for {@link SimpleResult}.
 *
 * @since 0.1
 * @checkstyle MagicNumberCheck (300 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
class SimpleResultTest {

    /**
     * Check that we can correctly create the simple test result if we provide
     * correct arguments.
     */
    @Test
    void testConstructor() {
        final String nulls = "All the constructor parameters must be provided.";
        Assertions.assertEquals(
            nulls, Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(null, 0, 0).isFinished()
            ).getMessage()
        );
        Assertions.assertEquals(
            nulls,
            Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(0, null, 0).isPassed()
            ).getMessage()
        );
        Assertions.assertEquals(
            nulls, Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(0, 0, null).toDisplayableString()
            ).getMessage()
        );
        final String positives = "All the constructor parameters must be positive.";
        Assertions.assertEquals(
            positives, Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(-1, 0, 0).isFinished()
            ).getMessage()
        );
        Assertions.assertEquals(
            positives, Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(0, -1, 0).isPassed()
            ).getMessage()
        );
        Assertions.assertEquals(
            positives, Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(0, 0, -1).isFinished()
            ).getMessage()
        );
        final String common = "Constructor parameters must not contradict the common sense.";
        Assertions.assertEquals(
            common, Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(6, 7, 4).isPassed()
            ).getMessage()
        );
        final String percentage =
            "'percentage' parameter must be between 0 and 100.";
        Assertions.assertEquals(
            percentage, Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(
                    new SimpleResult.Input(1, 1, 1), -1
                ).isPassed()
            ).getMessage()
        );
        Assertions.assertEquals(
            percentage, Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new SimpleResult(
                    new SimpleResult.Input(1, 1, 1), 101
                ).isFinished()
            ).getMessage()
        );
    }

    /**
     * Check that we correctly detect if the test is finished.
     */
    @Test
    void testIsFinished() {
        Assertions.assertTrue(
            new SimpleResult(new SimpleResult.Input(2, 2, 1), 60)
                .isFinished()
        );
        Assertions.assertFalse(
            new SimpleResult(new SimpleResult.Input(3, 1, 1), 50)
                .isFinished()
        );
    }

    /**
     * Check that we correctly detect if the test is passed.
     */
    @Test
    void testIsPassed() {
        Assertions.assertFalse(
            new SimpleResult(new SimpleResult.Input(3, 2, 2), 50)
                .isPassed()
        );
        Assertions.assertFalse(
            new SimpleResult(new SimpleResult.Input(4, 4, 2), 60)
                .isPassed()
        );
        Assertions.assertTrue(
            new SimpleResult(new SimpleResult.Input(5, 5, 3), 50)
                .isPassed()
        );
        Assertions.assertTrue(
            new SimpleResult(new SimpleResult.Input(6, 6, 6), 50)
                .isPassed()
        );
    }

    /**
     * Check that we correctly convert the result to JSON string.
     */
    @Test
    void toJsonString() {
        final int total = 7;
        final int answered = 6;
        final int correct = 4;
        final int wrong = 2;
        final int percentage = 50;
        final JSONObject json = new SimpleResult(
            new SimpleResult.Input(total, answered, correct), percentage
        ).toJson();
        Assertions.assertEquals(total, Integer.parseInt(json.get("total").toString()));
        Assertions.assertEquals(
            answered, Integer.parseInt(json.get("answered").toString())
        );
        Assertions.assertEquals(correct, Integer.parseInt(json.get("correct").toString()));
        Assertions.assertEquals(wrong, Integer.parseInt(json.get("wrong").toString()));
        Assertions.assertEquals(
            percentage, Integer.parseInt(json.get("percentage").toString())
        );
    }

    @Test
    void toDisplayableString() {
        final String partial =
            new SimpleResult(new SimpleResult.Input(7, 6, 4), 90)
                .toDisplayableString();
        Assertions.assertTrue(partial.contains("YOU TEST IS NOT FINISHED."));
        Assertions.assertTrue(partial.contains("TOTAL: 7"));
        Assertions.assertTrue(partial.contains("ANSWERED: 6"));
        Assertions.assertTrue(partial.contains("CORRECT: 4"));
        Assertions.assertTrue(partial.contains("WRONG: 2"));
        Assertions.assertTrue(partial.contains("PASSING PERCENTAGE: 90"));
        Assertions.assertTrue(partial.contains("PLEASE CONTINUE."));
        final String failed =
            new SimpleResult(new SimpleResult.Input(7, 7, 5), 90)
                .toDisplayableString();
        Assertions.assertTrue(failed.contains("YOUR TEST IS FINISHED."));
        Assertions.assertTrue(failed.contains("CORRECT: 5"));
        Assertions.assertTrue(failed.contains("WRONG: 2"));
        Assertions.assertTrue(failed.contains("PASSING PERCENTAGE: 90"));
        Assertions.assertTrue(failed.contains("PREPARE AND TRY AGAIN LATER"));
        final String success =
            new SimpleResult(new SimpleResult.Input(7, 7, 5), 50)
                .toDisplayableString();
        Assertions.assertTrue(success.contains("YOUR TEST IS FINISHED."));
        Assertions.assertTrue(success.contains("CORRECT: 5"));
        Assertions.assertTrue(success.contains("WRONG: 2"));
        Assertions.assertTrue(success.contains("PASSING PERCENTAGE: 50"));
        Assertions.assertTrue(success.contains("CONGRATULATIONS!!!"));
    }
}

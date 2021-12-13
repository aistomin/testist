/*
 * Copyright (c) 2019-2022, Istomin Andrei
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

import com.github.aistomin.testist.MagicNumbers;
import java.util.Arrays;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test for {@link SimpleResult}.
 *
 * @since 0.1
 */
final class SimpleResultTest {

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
                () ->
                    new SimpleResult(
                        MagicNumbers.SIX.number(),
                        MagicNumbers.SEVEN.number(),
                        MagicNumbers.FOUR.number()
                    ).isPassed()
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
                    new SimpleResult.Input(1, 1, 1),
                    MagicNumbers.ONE_HUNDRED_ONE.number()
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
            new SimpleResult(
                new SimpleResult.Input(2, 2, 1), MagicNumbers.SIXTY.number()
            ).isFinished()
        );
        Assertions.assertFalse(
            new SimpleResult(
                new SimpleResult.Input(MagicNumbers.THREE.number(), 1, 1),
                MagicNumbers.FIFTY.number()
            ).isFinished()
        );
    }

    /**
     * Check that we correctly detect if the test is passed.
     */
    @Test
    void testIsPassed() {
        Assertions.assertFalse(
            new SimpleResult(
                new SimpleResult.Input(MagicNumbers.THREE.number(), 2, 2),
                MagicNumbers.FIFTY.number()
            ).isPassed()
        );
        Assertions.assertFalse(
            new SimpleResult(
                new SimpleResult.Input(
                    MagicNumbers.FOUR.number(), MagicNumbers.FOUR.number(), 2
                ), MagicNumbers.SIXTY.number()
            ).isPassed()
        );
        Assertions.assertTrue(
            new SimpleResult(
                new SimpleResult.Input(
                    MagicNumbers.FIVE.number(),
                    MagicNumbers.FIVE.number(),
                    MagicNumbers.THREE.number()
                ), MagicNumbers.FIFTY.number()
            ).isPassed()
        );
        Assertions.assertTrue(
            new SimpleResult(
                new SimpleResult.Input(
                    MagicNumbers.SIX.number(),
                    MagicNumbers.SIX.number(),
                    MagicNumbers.SIX.number()
                ), MagicNumbers.FIFTY.number()
            ).isPassed()
        );
    }

    /**
     * Check that we correctly convert the result to JSON string.
     */
    @Test
    void toJsonString() {
        final int total = MagicNumbers.SEVEN.number();
        final int answered = MagicNumbers.SIX.number();
        final int correct = MagicNumbers.FOUR.number();
        final int wrong = 2;
        final int percentage = MagicNumbers.FIFTY.number();
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
        final String partial = new SimpleResult(
            new SimpleResult.Input(
                MagicNumbers.SEVEN.number(),
                MagicNumbers.SIX.number(),
                MagicNumbers.FOUR.number()
            ), MagicNumbers.NINETY.number()
        ).toDisplayableString();
        Arrays.asList(
            "YOU TEST IS NOT FINISHED.",
            "TOTAL: 7",
            "ANSWERED: 6"
        ).forEach(item -> Assertions.assertTrue(partial.contains(item)));
        final String correct = "CORRECT: %d";
        Assertions.assertTrue(
            partial.contains(
                String.format(correct, MagicNumbers.FOUR.number())
            )
        );
        final String wrong = "WRONG: %d";
        Assertions.assertTrue(partial.contains(String.format(wrong, 2)));
        final String passing = "PASSING PERCENTAGE: 90";
        Assertions.assertTrue(partial.contains(passing));
        Assertions.assertTrue(partial.contains("PLEASE CONTINUE."));
        final String failed = new SimpleResult(
            new SimpleResult.Input(
                MagicNumbers.SEVEN.number(),
                MagicNumbers.SEVEN.number(),
                MagicNumbers.FIVE.number()
            ), MagicNumbers.NINETY.number()
        ).toDisplayableString();
        final String finish = "YOUR TEST IS FINISHED.";
        Assertions.assertTrue(failed.contains(finish));
        Assertions.assertTrue(
            failed.contains(
                String.format(correct, MagicNumbers.FIVE.number())
            )
        );
        Arrays.asList(
            String.format(wrong, 2),
            passing,
            "PREPARE AND TRY AGAIN LATER"
        ).forEach(item -> Assertions.assertTrue(failed.contains(item)));
        final String success = new SimpleResult(
            new SimpleResult.Input(
                MagicNumbers.SEVEN.number(),
                MagicNumbers.SEVEN.number(),
                MagicNumbers.FIVE.number()
            ), MagicNumbers.FIFTY.number()
        ).toDisplayableString();
        Arrays.asList(
            finish,
            String.format(correct, MagicNumbers.FIVE.number()),
            String.format(wrong, 2),
            "PASSING PERCENTAGE: 50",
            "CONGRATULATIONS!!!"
        ).forEach(item -> Assertions.assertTrue(success.contains(item)));
    }
}

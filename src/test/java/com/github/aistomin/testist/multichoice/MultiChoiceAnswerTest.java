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
package com.github.aistomin.testist.multichoice;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test for {@link MultiChoiceAnswer}.
 *
 * @since 0.1
 */
final class MultiChoiceAnswerTest {

    /**
     * Check that we correctly validate the answers.
     */
    @Test
    void validate() {
        final Set<Choice> expected = new HashSet<>(
            Arrays.asList(Choice.G, Choice.F, Choice.E)
        );
        final Set<Choice> correct = new HashSet<>(
            Arrays.asList(Choice.F, Choice.E, Choice.G)
        );
        final MultiChoiceAnswer answer = new MultiChoiceAnswer(expected);
        Assertions.assertTrue(answer.validate(new MultiChoiceAnswer(correct)));
        final Set<Choice> incorrect = new HashSet<>(
            Arrays.asList(Choice.F, Choice.L, Choice.G)
        );
        Assertions.assertFalse(
            answer.validate(new MultiChoiceAnswer(incorrect))
        );
        Assertions.assertFalse(answer.validate(null));
    }

    /**
     * Check that we correctly convert the answer to the JSON string.
     */
    @Test
    void toJsonString() {
        final Set<Choice> selected =
            new HashSet<>(Arrays.asList(Choice.C, Choice.D));
        Assertions.assertEquals(
            "C; D", new MultiChoiceAnswer(selected).toJson().get("text")
        );
    }

    /**
     * Check that we correctly display the answer.
     */
    @Test
    void toDisplayableString() {
        final Set<Choice> selected = new HashSet<>(
            Arrays.asList(Choice.A, Choice.F, Choice.B)
        );
        Assertions.assertEquals(
            "A; B; F", new MultiChoiceAnswer(selected).toDisplayableString()
        );
    }
}

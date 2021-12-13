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
package com.github.aistomin.testist.multichoice;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test for {@link MultiChoiceQuestionText}.
 *
 * @since 0.1
 */
final class MultiChoiceQuestionTextTest {

    /**
     * Check that we correctly display the text.
     */
    @Test
    void toDisplayableString() {
        final Map<Choice, String> choices = new HashMap<>();
        choices.put(Choice.A, "Andrej");
        choices.put(Choice.B, "John");
        choices.put(Choice.C, "Tom");
        Assertions.assertEquals(
            "What is your name?\n\nA. Andrej\nB. John\nC. Tom",
            new MultiChoiceQuestionText("What is your name?", choices)
                .toDisplayableString()
        );
    }

    /**
     * Check that we correctly convert the test to the JSON.
     */
    @SuppressWarnings("unchecked")
    @Test
    void toJson() {
        final Map<Choice, String> expected = new HashMap<>();
        expected.put(Choice.A, "Java");
        expected.put(Choice.B, "PHP");
        expected.put(Choice.C, "C++");
        final String question = "What is your favourite programming language?";
        final JSONObject json = new MultiChoiceQuestionText(question, expected)
            .toJson();
        Assertions.assertEquals(question, json.get("text"));
        final Map<Choice, String> got = (Map<Choice, String>) json.get("choices");
        Assertions.assertEquals(expected.get(Choice.A), got.get(Choice.A));
        Assertions.assertEquals(expected.get(Choice.B), got.get(Choice.B));
        Assertions.assertEquals(expected.get(Choice.C), got.get(Choice.C));
    }
}

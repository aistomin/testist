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
package com.github.aistomin.testist.multichoice;

import com.github.aistomin.testist.simple.SimpleQuestion;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test for {@link MultiChoiceQuestion}.
 *
 * @since 0.1
 */
final class MultiChoiceQuestionTest {

    /**
     * Check that we can correctly answer a multi-choice questions.
     */
    @Test
    void testAnswer() {
        final Map<Choice, String> choices = this.sampleQuestion();
        final String text = "What is the best prog. rock band?";
        final Set<Choice> answer =
            new HashSet<>(Arrays.asList(Choice.B, Choice.C));
        final MultiChoiceQuestion correct = new MultiChoiceQuestion(
            text, choices, answer
        );
        Assertions.assertFalse(correct.isAnswered());
        Assertions.assertFalse(correct.isCorrect());
        correct.answer(
            new MultiChoiceAnswer(
                new HashSet<>(Arrays.asList(Choice.C, Choice.B))
            )
        );
        Assertions.assertTrue(correct.isAnswered());
        Assertions.assertTrue(correct.isCorrect());
        final MultiChoiceQuestion incomplete = new MultiChoiceQuestion(
            text, choices, answer
        );
        Assertions.assertFalse(incomplete.isAnswered());
        Assertions.assertFalse(incomplete.isCorrect());
        incomplete.answer(
            new MultiChoiceAnswer(
                new HashSet<>(Collections.singletonList(Choice.B))
            )
        );
        Assertions.assertTrue(incomplete.isAnswered());
        Assertions.assertFalse(incomplete.isCorrect());
        final MultiChoiceQuestion wrong = new MultiChoiceQuestion(
            text, choices, answer
        );
        Assertions.assertFalse(wrong.isAnswered());
        wrong.answer(
            new MultiChoiceAnswer(
                new HashSet<>(Arrays.asList(Choice.A, Choice.D))
            )
        );
        Assertions.assertTrue(wrong.isAnswered());
        Assertions.assertFalse(wrong.isCorrect());
    }

    /**
     * Check that "help" method displays the correct answer.
     */
    @Test
    void testHelp() {
        final Map<Choice, String> choices = new HashMap<>();
        choices.put(Choice.A, UUID.randomUUID().toString());
        choices.put(Choice.B, UUID.randomUUID().toString());
        choices.put(Choice.C, UUID.randomUUID().toString());
        final String text = UUID.randomUUID().toString();
        final Set<Choice> answer =
            new HashSet<>(Arrays.asList(Choice.B, Choice.C));
        final MultiChoiceQuestion question = new MultiChoiceQuestion(
            text, choices, answer
        );
        Assertions.assertEquals("B; C", question.help().toDisplayableString());
    }

    /**
     * Check that we can correctly convert question to JSON and to displayable
     * string.
     */
    @Test
    void testDisplay() {
        final Map<Choice, String> choices = new HashMap<>();
        choices.put(Choice.A, UUID.randomUUID().toString());
        choices.put(Choice.B, UUID.randomUUID().toString());
        choices.put(Choice.C, UUID.randomUUID().toString());
        final String text = UUID.randomUUID().toString();
        final Set<Choice> answer =
            new HashSet<>(Arrays.asList(Choice.B, Choice.C));
        final MultiChoiceQuestion question = new MultiChoiceQuestion(
            text, choices, answer
        );
        final String string = question.toDisplayableString();
        Assertions.assertTrue(string.contains(text));
        Assertions.assertTrue(
            string.contains(String.format("A. %s", choices.get(Choice.A)))
        );
        Assertions.assertTrue(
            string.contains(String.format("B. %s", choices.get(Choice.B)))
        );
        Assertions.assertTrue(
            string.contains(String.format("C. %s", choices.get(Choice.C)))
        );
        final JSONObject json = question.toJson();
        Assertions.assertEquals(
            new SimpleQuestion(
                new MultiChoiceQuestionText(text, choices),
                new MultiChoiceAnswer(answer)
            ).toJson().toString(),
            json.toString()
        );
    }

    /**
     * Create sample question.
     * @return Sample question.
     */
    private Map<Choice, String> sampleQuestion() {
        final Map<Choice, String> choices = new HashMap<>();
        choices.put(Choice.A, "Yes");
        choices.put(Choice.B, "ELP");
        choices.put(Choice.C, "Genesis");
        choices.put(Choice.D, "Dream Theater");
        return choices;
    }
}

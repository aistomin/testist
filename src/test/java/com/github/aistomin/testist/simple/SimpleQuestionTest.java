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
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The tests for {@link SimpleQuestion}.
 *
 * @since 0.1
 */
final class SimpleQuestionTest {

    /**
     * Check that we can correctly answer the questions.
     */
    @Test
    void answer() {
        final SimpleAnswer expected = new SimpleAnswer("Andrej");
        final SimpleQuestion wrong = new SimpleQuestion(
            new SimpleText("What is your name?"), expected
        );
        Assertions.assertEquals(expected, wrong.help());
        Assertions.assertFalse(wrong.isAnswered());
        Assertions.assertFalse(wrong.isCorrect());
        wrong.answer(new SimpleAnswer("John"));
        Assertions.assertTrue(wrong.isAnswered());
        Assertions.assertFalse(wrong.isCorrect());
        Assertions.assertEquals(
            "Can not answer the same question twice.",
            Assertions.assertThrows(
                IllegalStateException.class,
                () -> wrong.answer(new SimpleAnswer("Alex"))
            ).getMessage()
        );
        final String answer = "Istomin";
        final SimpleQuestion correct = new SimpleQuestion(
            new SimpleText("What is your surname?"), new SimpleAnswer(answer)
        );
        Assertions.assertFalse(correct.isAnswered());
        Assertions.assertFalse(correct.isCorrect());
        correct.answer(new SimpleAnswer(answer));
        Assertions.assertTrue(correct.isAnswered());
        Assertions.assertTrue(correct.isCorrect());
    }

    /**
     * Check that we correctly convert question to JSON string.
     *
     * @throws ParseException On JSON parsing error.
     */
    @Test
    void toJsonString() throws ParseException {
        final String question = "Who are you?";
        final String answer = "It's me";
        final SimpleQuestion test = new SimpleQuestion(
            new SimpleText(question), new SimpleAnswer(answer)
        );
        final JSONObject unanswered = test.toJson();
        final String quest = "question";
        final String text = "text";
        Assertions.assertEquals(
            question, ((JSONObject) unanswered.get(quest)).get(text)
        );
        final String expected = "expected";
        Assertions.assertEquals(
            answer, ((JSONObject) unanswered.get(expected)).get(text)
        );
        final String got = "got";
        Assertions.assertNull(unanswered.get(got));
        final String wrong = "It's he";
        test.answer(new SimpleAnswer(wrong));
        final JSONObject answered = test.toJson();
        Assertions.assertEquals(
            question, ((JSONObject) answered.get(quest)).get(text)
        );
        Assertions.assertEquals(
            answer, ((JSONObject) answered.get(expected)).get(text)
        );
        Assertions.assertEquals(
            wrong, ((JSONObject) answered.get(got)).get(text)
        );
    }

    /**
     * Check that we correctly display the question.
     */
    @Test
    void toDisplayableString() {
        final String question = "How old are you?";
        final String answer = "33";
        final SimpleQuestion test = new SimpleQuestion(
            new SimpleText(question), new SimpleAnswer(answer)
        );
        final String unanswered = test.toDisplayableString();
        Assertions.assertTrue(unanswered.contains(question));
        Assertions.assertFalse(unanswered.contains(answer));
        final String wrong = "15";
        test.answer(new SimpleAnswer(wrong));
        final String answered = test.toDisplayableString();
        Assertions.assertTrue(answered.contains(question));
        Assertions.assertTrue(answered.contains(answer));
        Assertions.assertTrue(answered.contains(wrong));
    }
}

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

import com.github.aistomin.testist.Question;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The tests for {@link SimpleTest}.
 *
 * @since 0.1
 */
final class SimpleTestTest {

    /**
     * Check that test can be passed or failed.
     */
    @Test
    void testRandom() {
        final TestQuestionsProvider provider = new TestQuestionsProvider();
        final List<Question> questions = provider.questions();
        final SimpleTest test = new SimpleTest(provider);
        final List<Question> total = new ArrayList<>(questions.size());
        final List<Question> correct = new ArrayList<>(questions.size());
        while (test.hasMoreQuestions()) {
            final Question question = test.nextQuestion();
            total.add(question);
            final ArrayList<Question> shuffle = new ArrayList<>(questions);
            Collections.shuffle(shuffle);
            question.answer(shuffle.get(0).help());
            if (question.isCorrect()) {
                correct.add(question);
            }
        }
        Assertions.assertTrue(test.currentTestResult().isFinished());
        Assertions.assertEquals(
            total.size() == correct.size(), test.currentTestResult().isPassed()
        );
    }

    /**
     * Check that test can be passed correctly.
     */
    @Test
    void testPassed() {
        final TestQuestionsProvider provider = new TestQuestionsProvider();
        final List<Question> questions = provider.questions();
        final SimpleTest test = new SimpleTest(provider);
        for (final Question item : questions) {
            final Question question = test.nextQuestion();
            question.answer(item.help());
            Assertions.assertTrue(question.isCorrect());
        }
        Assertions.assertTrue(test.currentTestResult().isFinished());
        Assertions.assertTrue(test.currentTestResult().isPassed());
    }
}

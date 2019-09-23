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
import com.github.aistomin.testist.QuestionsProvider;
import com.github.aistomin.testist.Result;
import com.github.aistomin.testist.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * The simple implementation of {@link Test}.
 *
 * @since 0.1
 */
public final class SimpleTest implements Test {

    /**
     * The list of the questions of the test.
     */
    private final List<Question> questions;

    /**
     * Mutex object.
     */
    private final Object mutex;

    /**
     * Ctor.
     *
     * @param questions Questions provider.
     */
    public SimpleTest(final QuestionsProvider questions) {
        this.questions = questions.questions();
        this.mutex = new Object();
    }

    public Boolean hasMoreQuestions() {
        synchronized (this.mutex) {
            return this.nextIndex() < this.questions.size();
        }
    }

    public Question nextQuestion() {
        synchronized (this.mutex) {
            return this.questions.get(this.nextIndex());
        }
    }

    public Result currentTestResult() {
        synchronized (this.mutex) {
            final List<Question> correct = new ArrayList<>(0);
            final List<Question> wrong = new ArrayList<>(0);
            for (final Question question : this.questions) {
                if (question.isAnswered()) {
                    if (question.isCorrect()) {
                        correct.add(question);
                    } else {
                        wrong.add(question);
                    }
                }
            }
            final int cor = correct.size();
            final int wrg = wrong.size();
            return new SimpleResult(
                this.questions.size(), cor + wrg, cor, wrg
            );
        }
    }

    /**
     * The next question's index.
     *
     * @return The index.
     */
    private Integer nextIndex() {
        int index = this.questions.size();
        for (final Question question : this.questions) {
            if (!question.isAnswered()) {
                index = this.questions.indexOf(question);
                break;
            }
        }
        return index;
    }
}

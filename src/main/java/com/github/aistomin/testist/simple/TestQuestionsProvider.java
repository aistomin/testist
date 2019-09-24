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
import java.util.Arrays;
import java.util.List;

/**
 * The sample/test implementation of the {@link QuestionsProvider}.
 *
 * @since 0.1
 */
final class TestQuestionsProvider implements QuestionsProvider {

    /**
     * Test questions list.
     */
    private final List<Question> items;

    /**
     * Ctor.
     */
    TestQuestionsProvider() {
        this.items = Arrays.asList(
            new SimpleQuestion(
                new SimpleText("1 + 1 = ?"), new SimpleAnswer("2")
            ),
            new SimpleQuestion(
                new SimpleText("3 + 6 = ?"), new SimpleAnswer("9")
            ),
            new SimpleQuestion(
                new SimpleText("6 - 2 = ?"), new SimpleAnswer("4")
            ),
            new SimpleQuestion(
                new SimpleText("2 * 2 = ?"), new SimpleAnswer("4")
            ),
            new SimpleQuestion(
                new SimpleText("6 / 2 = ?"), new SimpleAnswer("3")
            )
        );
    }

    @Override
    public List<Question> questions() {
        return this.items;
    }
}

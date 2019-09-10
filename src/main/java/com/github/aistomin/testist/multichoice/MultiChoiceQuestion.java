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

import com.github.aistomin.testist.Answer;
import com.github.aistomin.testist.Question;
import com.github.aistomin.testist.simple.SimpleQuestion;
import java.util.Map;
import java.util.Set;
import org.json.simple.JSONObject;

/**
 * The multi-choice implementation of the {@link Question}. Actually it
 * is a decorator for {@link SimpleQuestion} which accepts multi-choice answer.
 *
 * @since 0.1
 */
public final class MultiChoiceQuestion implements Question {

    /**
     * Simple question which we decorate.
     */
    private final SimpleQuestion simple;

    /**
     * Ctor.
     *
     * @param text The question's text.
     * @param choices The choices that will be displayed to the user.
     * @param correct The correct choices.
     */
    public MultiChoiceQuestion(
        final String text, final Map<Choice, String> choices,
        final Set<Choice> correct
    ) {
        this(text, choices, new MultiChoiceAnswer(correct));
    }

    /**
     * Ctor.
     *
     * @param text The question's text.
     * @param choices The choices that will be displayed to the user.
     * @param answer The correct answer.
     */
    public MultiChoiceQuestion(
        final String text, final Map<Choice, String> choices,
        final Answer answer
    ) {
        this.simple = new SimpleQuestion(
            new MultiChoiceQuestionText(text, choices), answer
        );
    }

    @Override
    public void answer(final Answer answer) {
        this.simple.answer(answer);
    }

    @Override
    public Boolean isCorrect() {
        return this.simple.isCorrect();
    }

    @Override
    public Boolean isAnswered() {
        return this.simple.isAnswered();
    }

    @Override
    public Answer help() {
        return this.simple.help();
    }

    @Override
    public JSONObject toJson() {
        return this.simple.toJson();
    }

    @Override
    public String toDisplayableString() {
        return this.simple.toDisplayableString();
    }
}

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

import com.github.aistomin.testist.Answer;
import com.github.aistomin.testist.Question;
import com.github.aistomin.testist.QuestionsText;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * The simple implementation of {@link Question}.
 *
 * @since 0.1
 */
@SuppressFBWarnings
public final class SimpleQuestion implements Question {

    /**
     * String buffer capacity.
     */
    public static final int CAPACITY = 100;

    /**
     * Question's text.
     */
    private final QuestionsText text;

    /**
     * Expected answer.
     */
    private final Answer expected;

    /**
     * The answer which we got from the client.
     */
    private Answer got;

    /**
     * Mutex object.
     */
    private final Object mutex;

    /**
     * Ctor.
     *
     * @param text Question's text.
     * @param answer Expected answer to the question.
     */
    public SimpleQuestion(final QuestionsText text, final Answer answer) {
        this.text = text;
        this.expected = answer;
        this.mutex = new Object();
    }

    @Override
    public void answer(final Answer answer) {
        synchronized (this.mutex) {
            if (this.got != null) {
                throw new IllegalStateException("Can not answer the same question twice.");
            }
            this.got = answer;
        }
    }

    @Override
    public Boolean isCorrect() {
        synchronized (this.mutex) {
            return this.isAnswered() && this.expected.validate(this.got);
        }
    }

    @Override
    public Boolean isAnswered() {
        synchronized (this.mutex) {
            return this.got != null;
        }
    }

    @Override
    public Answer help() {
        return this.expected;
    }

    @Override
    public JSONObject toJson() {
        synchronized (this.mutex) {
            final Map<String, Object> json = new HashMap<>();
            json.put("question", this.text.toJson());
            json.put("expected", this.expected.toJson());
            if (this.isAnswered()) {
                json.put("got", this.got.toJson());
            }
            return new JSONObject(json);
        }
    }

    @Override
    public String toDisplayableString() {
        synchronized (this.mutex) {
            final StringBuilder builder = new StringBuilder(
                SimpleQuestion.CAPACITY
            );
            builder.append(
                String.format(
                    "%n**********************************%n%s%n",
                    this.text.toDisplayableString()
                )
            );
            if (this.isAnswered()) {
                if (this.isCorrect()) {
                    builder.append(
                        String.format(
                            "YOUR ANSWER IS CORRECT!%nANSWER: %s%n",
                            this.expected.toDisplayableString()
                        )
                    );
                } else {
                    builder.append(
                        String.format(
                            "YOUR ANSWER IS NOT CORRECT!%nCORRECT ANSWER: %s%n",
                            this.expected.toDisplayableString()
                        )
                    );
                    builder.append(
                        String.format(
                            "PROVIDED ANSWER: %s%n", this.got.toDisplayableString()
                        )
                    );
                }
            }
            builder.append("**********************************");
            return builder.toString();
        }
    }
}

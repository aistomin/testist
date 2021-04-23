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
package com.github.aistomin.testist.simple;

import com.github.aistomin.testist.Answer;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * The simple implementation of {@link Answer}.
 *
 * @since 0.1
 */
public final class SimpleAnswer implements Answer {

    /**
     * The answer's text.
     */
    private final String text;

    /**
     * Ctor.
     *
     * @param text The answer's text.
     */
    public SimpleAnswer(final String text) {
        this.text = text;
    }

    @Override
    public Boolean validate(final Answer answer) {
        return answer != null && this.text.equals(answer.toDisplayableString());
    }

    @Override
    public JSONObject toJson() {
        final Map<String, String> json = new HashMap<>();
        json.put("text", this.text);
        return new JSONObject(json);
    }

    @Override
    public String toDisplayableString() {
        return this.text;
    }
}

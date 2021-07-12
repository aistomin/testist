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
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
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
     * Additional configurations.
     */
    private final Set<Conf> configs;

    /**
     * Ctor.
     *
     * @param text The answer's text.
     */
    public SimpleAnswer(final String text) {
        this(text, new HashSet<>(0));
    }

    /**
     * Ctor.
     *
     * @param text The answer's text.
     * @param misc Additional configurations.
     */
    public SimpleAnswer(final String text, final Set<Conf> misc) {
        this.configs = misc;
        this.text = text.trim().replaceAll("\\s+", " ");
    }

    @Override
    public Boolean validate(final Answer answer) {
        return answer != null
            && this.normalise(this.toDisplayableString()).equals(
                this.normalise(answer.toDisplayableString())
            );
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

    /**
     * Normalise the original string.
     *
     * @param str Original string.
     * @return Normalised string.
     */
    private String normalise(final String str) {
        return this.fixPunctuationIfNecessary(this.fixCaseIfNecessary(str));
    }

    /**
     * If question is configured to ignore the case, then we need to normalise
     * the string.
     *
     * @param str Original string.
     * @return Normalised string.
     */
    private String fixCaseIfNecessary(final String str) {
        final String res;
        if (this.configs.contains(Conf.IGNORE_CASE)) {
            res = str.toLowerCase(Locale.getDefault());
        } else {
            res = str;
        }
        return res;
    }

    /**
     * If question is configured to ignore the punctuation, then we need to
     * normalise the string.
     *
     * @param str Original string.
     * @return Normalised string.
     */
    private String fixPunctuationIfNecessary(final String str) {
        final String res;
        if (this.configs.contains(Conf.IGNORE_PUNCTUATION)) {
            res = str.replaceAll("[^\\p{IsAlphabetic}\\p{IsDigit}]", "");
        } else {
            res = str;
        }
        return res;
    }

    /**
     * Additional answer configuration.
     */
    public enum Conf {

        /**
         * Ignore text's case.
         */
        IGNORE_CASE,

        /**
         * Ignore punctuation inside the text.
         */
        IGNORE_PUNCTUATION
    }
}

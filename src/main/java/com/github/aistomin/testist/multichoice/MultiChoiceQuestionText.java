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

import com.github.aistomin.testist.QuestionsText;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * The multi-choice implementation of the {@link QuestionsText}.
 *
 * @since 0.1
 * @todo: Issue #55. Let's fix FindBugs warning and remove the suppression.
 */
@SuppressFBWarnings
public final class MultiChoiceQuestionText implements QuestionsText {

    /**
     * The question's text.
     */
    private final String text;

    /**
     * The choices that will be displayed to the user.
     */
    private final Map<Choice, String> choices;

    /**
     * Ctor.
     *
     * @param text The question's text.
     * @param choices The choices that will be displayed to the user.
     */
    public MultiChoiceQuestionText(
        final String text, final Map<Choice, String> choices
    ) {
        this.text = text;
        this.choices = choices;
    }

    @Override
    public String toDisplayableString() {
        final StringBuilder builder = new StringBuilder(this.text);
        builder.append(String.format("%n"));
        final List<Map.Entry<Choice, String>> sorted =
            new ArrayList<>(this.choices.entrySet());
        sorted.sort(Comparator.comparing(obj -> obj.getKey().name()));
        sorted.forEach(
            entry -> builder.append(
                String.format("%n%s. %s", entry.getKey(), entry.getValue())
            )
        );
        return builder.toString();
    }

    @Override
    public JSONObject toJson() {
        final Map<String, Object> json = new HashMap<>();
        json.put("text", this.text);
        json.put("choices", this.choices);
        return new JSONObject(json);
    }
}

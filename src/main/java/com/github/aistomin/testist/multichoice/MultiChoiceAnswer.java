/*
 * Copyright (c) 2019-2022, Istomin Andrei
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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.json.simple.JSONObject;

/**
 * The multi-choice test implementation of the {@link Answer}.
 *
 * @since 0.1
 */
public final class MultiChoiceAnswer implements Answer {

    /**
     * The options selected by user.
     */
    private final Set<Choice> selected;

    /**
     * Ctor.
     *
     * @param selection The options selected by user.
     */
    public MultiChoiceAnswer(final Set<Choice> selection) {
        this.selected = selection;
    }

    @Override
    public Boolean validate(final Answer answer) {
        return answer != null
            && this.toDisplayableString().equals(answer.toDisplayableString());
    }

    @Override
    public JSONObject toJson() {
        final Map<String, String> json = new HashMap<>();
        json.put("text", this.toDisplayableString());
        return new JSONObject(json);
    }

    @Override
    public String toDisplayableString() {
        final List<Choice> sorted = new ArrayList<>(this.selected);
        sorted.sort(Comparator.naturalOrder());
        return sorted
            .stream()
            .map(Enum::name)
            .collect(Collectors.joining("; "));
    }
}

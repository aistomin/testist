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

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test for {@link SimpleText}.
 *
 * @since 0.1
 */
final class SimpleTextTest {

    /**
     * Check that we correctly convert the entity to the JSON.
     */
    @Test
    void toJsonString() {
        final String text = "bla-bla text";
        Assertions.assertEquals(
            text, new SimpleText(text).toJson().get("text")
        );
    }

    /**
     * Check that we correctly display the text.
     */
    @Test
    void toDisplayableString() {
        final String text = "some text";
        Assertions.assertEquals(
            text, new SimpleText(text).toDisplayableString()
        );
    }
}

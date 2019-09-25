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
package com.github.aistomin.testist;

/**
 * The enum with magic numbers.
 *
 * @since 0.1
 */
public enum MagicNumbers {

    /**
     * One hundred.
     */
    HUNDRED(100);

    /**
     * Magic number value.
     */
    private final Integer val;

    /**
     * Ctor.
     *
     * @param value Magic number value.
     */
    MagicNumbers(final Integer value) {
        this.val = value;
    }

    /**
     * Magic number value.
     *
     * @return Value.
     */
    public Integer number() {
        return this.val;
    }
}

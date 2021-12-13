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
package com.github.aistomin.testist.simple;

import com.github.aistomin.testist.Test;
import com.github.aistomin.testist.TestSuite;
import java.util.List;

/**
 * The simple implementation of {@link TestSuite}.
 *
 * @since 0.1
 */
public final class SimpleTestSuite implements TestSuite {

    /**
     * Topic's name.
     */
    private final String name;

    /**
     * Tests' suite.
     */
    private final List<Test> tests;

    /**
     * Ctor.
     *
     * @param topic Topic's name.
     * @param suite Tests' suite.
     */
    public SimpleTestSuite(final String topic, final List<Test> suite) {
        this.name = topic;
        this.tests = suite;
    }

    @Override
    public String topic() {
        return this.name;
    }

    @Override
    public List<Test> suite() {
        return this.tests;
    }
}

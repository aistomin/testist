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
package com.github.aistomin.testist.demo;

import com.github.aistomin.testist.simple.SimpleTest;
import com.github.aistomin.testist.simple.TestQuestionsProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The example of the simple test.
 *
 * @since 0.1
 */
public final class SimpleTestDemo {

    /**
     * Logger.
     */
    private static final Logger LOG =
        LoggerFactory.getLogger(SimpleTestDemo.class);

    /**
     * Ctor.
     */
    private SimpleTestDemo() {
    }

    /**
     * Runnable method.
     *
     * @param args Arguments.
     */
    public static void main(final String... args) {
        new SimpleTestConsole(
            new SimpleTest(
                new TestQuestionsProvider()
            ), LOG::info
        ).runTest();
    }
}

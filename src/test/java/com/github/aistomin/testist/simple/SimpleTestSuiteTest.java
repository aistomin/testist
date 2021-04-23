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

import com.github.aistomin.testist.TestSuite;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * The test for {@link SimpleTestSuite}.
 *
 * @since 0.1
 */
final class SimpleTestSuiteTest {

    /**
     * Check that we can properly construct and use test suites.
     */
    @Test
    void testTestSuite() {
        final String name = UUID.randomUUID().toString();
        final int size = new Random().nextInt(10);
        final List<com.github.aistomin.testist.Test> tests =
            new ArrayList<>(size);
        IntStream.range(0, size).forEach(
            value ->
                tests.add(new SimpleTest(new TestQuestionsProvider()))
        );
        final TestSuite suite = new SimpleTestSuite(name, tests);
        Assertions.assertEquals(name, suite.topic());
        Assertions.assertEquals(size, suite.suite().size());
    }
}

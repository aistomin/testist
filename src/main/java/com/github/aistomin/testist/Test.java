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
package com.github.aistomin.testist;

/**
 * Abstract interface of a test.
 *
 * @since 0.1
 */
public interface Test {

    /**
     * Does the test have more questions?
     *
     * @return True - yes; False - no.
     */
    Boolean hasMoreQuestions();

    /**
     * Next test's question.
     *
     * @return The question.
     */
    Question nextQuestion();

    /**
     * Current test's result. It may be final or intermediate result.
     *
     * @return The result.
     */
    Result currentTestResult();
}

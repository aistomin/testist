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

import com.github.aistomin.testist.Question;
import com.github.aistomin.testist.Test;
import com.github.aistomin.testist.simple.SimpleAnswer;
import java.util.Scanner;

/**
 * Encapsulates the logic which interacts with user.
 *
 * @since 0.1
 */
public final class SimpleTestConsole {

    /**
     * The test which is going to be launched in console.
     */
    private final Test test;

    /**
     * Output.
     */
    private final SimpleTestOutput out;

    /**
     * Ctor.
     *
     * @param item The test which is going to be launched in console.
     * @param output Output.
     */
    public SimpleTestConsole(final Test item, final SimpleTestOutput output) {
        this.test = item;
        this.out = output;
    }

    /**
     * Run the test in the console.
     */
    public void runTest() {
        final Scanner scanner = new Scanner(System.in, "UTF-8");
        while (this.test.hasMoreQuestions()) {
            final Question question = this.test.nextQuestion();
            this.out.write(question.toDisplayableString());
            question.answer(new SimpleAnswer(scanner.next()));
            this.out.write(question.toDisplayableString());
        }
        this.out.write(
            this.test.currentTestResult().toDisplayableString()
        );
    }
}

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
package com.github.aistomin.testist.simple;

import com.github.aistomin.testist.Result;
import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

/**
 * The result which allows to pass the test if certain percentage of the answers
 * is correct.
 *
 * @since 0.1
 * @checkstyle ParameterNumberCheck (200 lines)
 * @checkstyle MagicNumberCheck (200 lines)
 * @checkstyle CyclomaticComplexityCheck (200 lines)
 * @checkstyle NPathComplexityCheck (200 lines)
 * @checkstyle BooleanExpressionComplexityCheck (200 lines)
 * @checkstyle ExecutableStatementCountCheck (200 lines)
 * @checkstyle AnnotationUseStyleCheck (200 lines)
 */
@SuppressWarnings(
    {
        "PMD.ConstructorOnlyInitializesOrCallOtherConstructors",
        "PMD.NPathComplexity",
        "PMD.AppendCharacterWithChar",
        "PMD.ConsecutiveAppendsShouldReuse",
        "PMD.ConsecutiveLiteralAppends",
        "PMD.AvoidDuplicateLiterals",
        "PMD.InsufficientStringBufferDeclaration",
        "PMD.CyclomaticComplexity"
    }
)
public final class SimpleResult implements Result {

    /**
     * The total amount of questions in the test.
     */
    private final Integer total;

    /**
     * The amount of answered questions in the test.
     */
    private final Integer answered;

    /**
     * The amount of correctly answered questions in the test.
     */
    private final Integer correct;

    /**
     * The amount of wrongly answered questions in the test.
     */
    private final Integer wrong;

    /**
     * The percentage of the correct answers which must be reached to pass the
     * test.
     */
    private final Integer percentage;

    /**
     * Ctor. The percentage is 100% in this case.
     *
     * @param total The total amount of questions in the test.
     * @param answered The amount of answered questions in the test.
     * @param correct The amount of correctly answered questions in the test.
     * @param wrong The amount of wrongly answered questions in the test.
     */
    public SimpleResult(
        final Integer total,
        final Integer answered,
        final Integer correct,
        final Integer wrong
    ) {
        this(total, answered, correct, wrong, 100);
    }

    /**
     * Ctor.
     *
     * @param total The total amount of questions in the test.
     * @param answered The amount of answered questions in the test.
     * @param correct The amount of correctly answered questions in the test.
     * @param wrong The amount of wrongly answered questions in the test.
     * @param percentage The percentage of the correct answers which must be
     *  reached to pass the test.
     */
    public SimpleResult(
        final Integer total,
        final Integer answered,
        final Integer correct,
        final Integer wrong,
        final Integer percentage
    ) {
        if (
            total == null || answered == null
                || correct == null || wrong == null || percentage == null
        ) {
            throw new IllegalArgumentException(
                "All the constructor parameters must be provided."
            );
        }
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException(
                "'percentage' parameter must be between 0 and 100."
            );
        }
        if (
            total < 0 || answered < 0
                || correct < 0 || wrong < 0
        ) {
            throw new IllegalArgumentException(
                "All the constructor parameters must be positive."
            );
        }
        if (
            total < answered || answered != (correct + wrong)
        ) {
            throw new IllegalArgumentException(
                "Constructor parameters must not contradict the common sense."
            );
        }
        this.total = total;
        this.answered = answered;
        this.correct = correct;
        this.wrong = wrong;
        this.percentage = percentage;
    }

    @Override
    public Boolean isFinished() {
        return this.total.equals(this.answered);
    }

    @Override
    public Boolean isPassed() {
        return this.isFinished()
            && (this.correct * 100) / this.total >= this.percentage;
    }

    @Override
    public JSONObject toJson() {
        final Map<String, String> json = new HashMap<>();
        json.put("total", this.total.toString());
        json.put("answered", this.answered.toString());
        json.put("correct", this.correct.toString());
        json.put("wrong", this.wrong.toString());
        json.put("percentage", this.percentage.toString());
        return new JSONObject(json);
    }

    @Override
    public String toDisplayableString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("%n");
        builder.append("**********************************");
        builder.append("%n");
        if (this.isFinished()) {
            builder.append("YOUR TEST IS FINISHED.");
            builder.append("%n");
        } else {
            builder.append(
                String.format(
                    "YOU TEST IS NOT FINISHED. %nTOTAL: %d, %nANSWERED: %d",
                    this.total, this.answered
                )
            );
            builder.append("%n");
        }
        builder.append(String.format("CORRECT: %d", this.correct));
        builder.append("%n");
        builder.append(String.format("WRONG: %d", this.wrong));
        builder.append("%n");
        builder.append(String.format("PASSING PERCENTAGE: %d", this.percentage));
        builder.append("%n");
        if (this.isPassed()) {
            builder.append(":) CONGRATULATIONS!!! :)");
            builder.append("%n");
        } else if (this.isFinished()) {
            builder.append(":( PREPARE AND TRY AGAIN LATER :(");
            builder.append("%n");
        } else {
            builder.append("PLEASE CONTINUE.");
            builder.append("%n");
        }
        builder.append("**********************************");
        return builder.toString();
    }
}

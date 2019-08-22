package com.github.aistomin.testist;

import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 22.08.19.
 * <p>
 * Test for {@link Dummy}.
 */
final class DummyTest {

    @Test
    void testValue() {
        final String expected = UUID.randomUUID().toString();
        Assertions.assertEquals(expected, new Dummy(expected).value());
    }
}

package com.github.aistomin.testist;

/**
 * Created by aistomin on 22.08.19.
 * <p>
 * Dummy class.
 */
public final class Dummy {

    /**
     * Dummy field.
     */
    private final String field;

    /**
     * Ctor.
     *
     * @param param Dummy field.
     */
    public Dummy(final String param) {
        this.field = param;
    }

    /**
     * Dummy value.
     *
     * @return Dummy value.
     */
    public String value() {
        return this.field;
    }
}

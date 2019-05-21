package org.codewarrior.common.domain;


import org.codewarrior.common.Assert;

import java.io.Serializable;
import java.util.Objects;

public class Id<IdType> implements Value, Serializable {
    private static final long serialVersionUID = 1155123811814305919L;
    private IdType value;

    protected Id(IdType anId) {
        this();
        value = Assert.notNull(anId, "IdType cannot be null");
    }

    private IdType id() {
        return value;
    }

    private Id() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Id<?> id = (Id<?>) o;
        return value.equals(id.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" + id() + ")";
    }


}

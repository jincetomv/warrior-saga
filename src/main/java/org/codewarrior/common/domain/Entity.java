package org.codewarrior.common.domain;

import java.io.Serializable;

public interface Entity<IdType extends Id> extends Serializable {
    IdType id();
}

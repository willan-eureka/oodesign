package com.design.manager.impl;

import com.design.manager.SchoolManager;
import com.design.model.School;

import java.io.Serializable;

public class SchoolManagerImpl<T, PK extends Serializable> extends GenericManagerImpl<School, Long> implements SchoolManager {
    public SchoolManagerImpl(Class<School> persistenceClass) {
        super(persistenceClass);
    }
}

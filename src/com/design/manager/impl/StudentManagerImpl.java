package com.design.manager.impl;

import com.design.manager.StudentManager;
import com.design.model.Student;

import java.io.Serializable;

public class StudentManagerImpl<T, PK extends Serializable> extends GenericManagerImpl<Student, Long> implements StudentManager {
    public StudentManagerImpl(Class<Student> persistenceClass) {
        super(persistenceClass);
    }
}

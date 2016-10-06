package com.design.manager;

import com.design.manager.impl.SchoolManagerImpl;
import com.design.manager.impl.StudentManagerImpl;
import com.design.model.School;
import com.design.model.Student;

public class ManagerFactory {
    private static StudentManager studentManager = new StudentManagerImpl(Student.class);
    private static SchoolManager schoolManager = new SchoolManagerImpl(School.class);

    public static StudentManager getStudentManager() {
        return studentManager;
    }

    public static SchoolManager getSchoolManager() {
        return schoolManager;
    }
}

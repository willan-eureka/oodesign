package com.design.manager;

import com.design.model.Student;

/**
 * Specific interface for access to Student entity
 * we also have define own methods for this specific interface
 */

public interface StudentManager extends GenericManager<Student, Long> {

}

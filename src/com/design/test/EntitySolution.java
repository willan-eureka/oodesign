package com.design.test;

import com.design.manager.ManagerFactory;
import com.design.manager.SchoolManager;
import com.design.manager.StudentManager;
import com.design.model.School;
import com.design.model.Student;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntitySolution {
    public static void main(String[] args) throws InterruptedException {
        StudentManagerTest.loadData();
        SchoolManagerTest.loadData();
        List<String> options = Arrays.asList(args);
        if (options.isEmpty()) {
            System.out.println("You need set entity name and parameters.");
            return;
        }

        long milliseconds = System.currentTimeMillis();
        Map<String, String> properties = new HashMap<>();
        switch (options.get(0)) {
            case "student":
                StudentManager studentManager = ManagerFactory.getStudentManager();
                try {
                    for (String option : options.subList(1, options.size())) {
                        String[] condition = option.split("=");
                        properties.put(condition[0], (condition[0].equals("gender") || condition[0].equals("type")) ? condition[1].toUpperCase() : condition[1]);
                    }

                    List<Student> students = studentManager.search(properties, properties.containsKey("name") ? "name" : "date");
                    for (Student student : students) {
                        System.out.println(student);
                    }
                    milliseconds = System.currentTimeMillis() - milliseconds;
                    System.out.println(String.format("%1$d result(s) in %2$d milliseconds", students.size(), milliseconds));
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("You need comple a condition");
                }
                break;
            case "school":
                SchoolManager schoolManager = ManagerFactory.getSchoolManager();
                try {
                    for (String option : options.subList(1, options.size())) {
                        String[] condition = option.split("=");
                        properties.put(condition[0], condition[1]);
                    }

                    List<School> schools = schoolManager.search(properties, properties.containsKey("name") ? "name" : "date");
                    for (School school : schools) {
                        System.out.println(school);
                    }
                    milliseconds = System.currentTimeMillis() - milliseconds;
                    System.out.println(String.format("%1$d result(s) in %2$d milliseconds", schools.size(), milliseconds));
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("You need comple a condition");
                }
                break;
        }
    }
}

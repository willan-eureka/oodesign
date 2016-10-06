package com.design.test;

import com.design.manager.ManagerFactory;
import com.design.manager.StudentManager;
import com.design.manager.impl.StudentManagerImpl;
import com.design.model.Student;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentManagerTest {
    private static StudentManager studentManager = ManagerFactory.getStudentManager();

    public static void main(String[] args) throws InterruptedException {
        assert studentManager.size().longValue() == 0:"Student Entity contain values";
        loadData();
        assert studentManager.size().longValue() == 20:"Student Entity has not 20 elements";

        List<Student> students = studentManager.list(1L, 5L);
        assert students.size() == 5:"Student List has not 5 elements";
        assert students.get(0).getName().equals("Bennett Lamphere"):"First result is not equals to 'Bennett Lamphere'";
        assert students.get(1).getName().equals("Cierra Lebow"):"Second result is not equals to 'Cierra Lebow'";

        Student student = students.get(0);
        Date date = student.getDate();
        Thread.sleep(150);
        student.setName("Ben Lamp");
        studentManager.update(student);
        Student storeStudent = studentManager.get(student.getId());
        assert student.getName().equals(storeStudent.getName()):"Name was not updated successfully";
        assert date.before(storeStudent.getDate()):"Date was not updated successfully";

        studentManager.delete(2L);
        Long size = studentManager.size();
        assert size == 19:"Student Entity has not 19 elements";

        Map<String, String> properties = new HashMap<String, String>();
        students = studentManager.search(properties, "name");
        assert students.get(0).getName().equals("Ashlea Goddard"):"First result is not equals to 'Ashlea Goddard'";
        assert students.get(1).getName().equals("Ayesha Fulmore"):"Second result is not equals to 'Ayesha Fulmore'";

        properties = new HashMap<String, String>();
        properties.put("gender", "MALE");
        students = studentManager.search(properties, "date");
        assert students.get(0).getDate().after(students.get(students.size() - 1).getDate()):"Result was not sorted by date";
        System.out.println("All Student test were executed successfully");
    }

    public static void loadData() throws InterruptedException {
        makeStudent("Bennett Lamphere", Student.Gender.FEMALE, Student.Type.KINDER);
        makeStudent("Cierra Lebow", Student.Gender.FEMALE, Student.Type.ELEMENTARY);
        makeStudent("Ayesha Fulmore", Student.Gender.MALE, Student.Type.HIGH);
        makeStudent("Jeanmarie Asencio", Student.Gender.MALE, Student.Type.UNIVERSITY);
        makeStudent("Leida Lemay", Student.Gender.FEMALE, Student.Type.KINDER);
        makeStudent("Christi Ormiston", Student.Gender.FEMALE, Student.Type.ELEMENTARY);
        makeStudent("Dillon Peters", Student.Gender.MALE, Student.Type.HIGH);
        makeStudent("Wally Belford", Student.Gender.MALE, Student.Type.UNIVERSITY);
        makeStudent("Emmett Cosme", Student.Gender.FEMALE, Student.Type.KINDER);
        makeStudent("Ilene Fulk", Student.Gender.FEMALE, Student.Type.ELEMENTARY);
        makeStudent("Cherelle Alameda", Student.Gender.MALE, Student.Type.HIGH);
        makeStudent("Tracy Efaw", Student.Gender.MALE, Student.Type.UNIVERSITY);
        makeStudent("Louisa Hedgpeth", Student.Gender.FEMALE, Student.Type.KINDER);
        makeStudent("Moses Creviston", Student.Gender.FEMALE, Student.Type.ELEMENTARY);
        makeStudent("Maya Crapston", Student.Gender.MALE, Student.Type.HIGH);
        makeStudent("Gear Chism", Student.Gender.MALE, Student.Type.UNIVERSITY);
        makeStudent("Vita Loya", Student.Gender.FEMALE, Student.Type.KINDER);
        makeStudent("Ashlea Goddard", Student.Gender.FEMALE, Student.Type.ELEMENTARY);
        makeStudent("Corin Charron", Student.Gender.MALE, Student.Type.HIGH);
        makeStudent("Deandrian Lariviere", Student.Gender.MALE, Student.Type.UNIVERSITY);
    }

    public static void makeStudent(String name, Student.Gender gender, Student.Type type) throws InterruptedException {
        Thread.sleep(150);
        Student student = new Student(name, gender, type);
        studentManager.save(student);
    }
}

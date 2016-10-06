package com.design.test;

import com.design.manager.ManagerFactory;
import com.design.manager.SchoolManager;
import com.design.model.School;

public class SchoolManagerTest {
    public static SchoolManager schoolManager = ManagerFactory.getSchoolManager();
    public static void main(String[] args) throws InterruptedException {
        assert schoolManager.size().longValue() == 0:"School Entity contain values";
        loadData();
        assert schoolManager.size().longValue() == 20:"School Entity has not 20 elements";

        System.out.println("All School test were executed successfully");
    }

    public static void loadData() throws InterruptedException {
        makeSchool("Argentine School", "La Paz", "Sopocachi #25");
        makeSchool("Argentine School", "La Paz", "Sopocachi #25");
        makeSchool("Colombia School", "Cochabamba", "Cala Cala #105");
        makeSchool("Bolivie School", "La Paz", "La loma #1025");
        makeSchool("Brazil School", "Santa Cruz", "La Ramada #555");
    }

    public static void makeSchool(String name, String city, String direction) throws InterruptedException {
        Thread.sleep(150);
        School school = new School(name, city, direction);
        schoolManager.save(school);
    }
}

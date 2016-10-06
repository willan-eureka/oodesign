package com.design.model;

import java.util.Date;

/**
 * A Student model for store a instance.
 * It define attributes and methods setter/getter according to
 * JavaBeans specification
 */

public class Student extends BaseModel implements Entity {

    private Long id;
    private String name;
    private Gender gender;
    private Type type;
    private Date date;

    public Student() {
    }

    public Student(String name, Gender gender, Type type) {
        this.name = name;
        this.gender = gender;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Student{");
        sb.append("date=").append(date);
        sb.append(", type=").append(type);
        sb.append(", gender=").append(gender);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (name != null ? !name.equals(student.name) : student.name != null) return false;
        if (gender != student.gender) return false;
        return type == student.type;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    public enum Gender {
        FEMALE("Female"),
        MALE("Male"),
        UNDEFINED("Undefined");

        private String label;

        Gender(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    public enum Type {
        KINDER("Kinder"),
        ELEMENTARY("Elementary"),
        HIGH("High"),
        UNIVERSITY("University");

        private String label;

        Type(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }
}

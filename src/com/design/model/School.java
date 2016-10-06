package com.design.model;

import java.util.Date;

/**
 * A School model for store a instance.
 * It define attributes and methods setter/getter according to
 * JavaBeans specification
 */

public class School extends BaseModel implements Entity {
    private Long id;
    private String name;
    private String city;
    private String direction;
    private Date date;

    public School() {
    }

    public School(String name, String city, String direction) {
        this.name = name;
        this.city = city;
        this.direction = direction;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }


    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("School{");
        sb.append("name='").append(name).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        School school = (School) o;

        if (name != null ? !name.equals(school.name) : school.name != null) return false;
        return city != null ? city.equals(school.city) : school.city == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }
}

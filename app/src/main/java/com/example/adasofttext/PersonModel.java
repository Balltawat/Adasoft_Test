package com.example.adasofttext;

public class PersonModel {
    private String idnumber;
    private String name;
    private String surname;
    private String age;

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public PersonModel(String idnumber, String name, String surname, String age) {
        this.idnumber = idnumber;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
}

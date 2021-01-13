package com.bakeryblueprint.modernjava.week05_jaehyun;

class BirthDay //클래스 BirthDay
{
    String name;
    String birthDay;

    public BirthDay(String name, String birthDay) {
        this.name = name;
        this.birthDay = birthDay;
    }

    public String getName() {
        return name;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String toString() {
        return "Name : " + this.name + ", Birthday : " + this.birthDay;
    }

}
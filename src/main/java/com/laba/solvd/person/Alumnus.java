package com.laba.solvd.person;

import com.laba.solvd.exceptions.NoDegreeException;
import com.laba.solvd.enums.Degree;

import java.time.Year;

public class Alumnus extends Person {
    private Year graduationYear;
    private Degree degree;

    public Alumnus(String name, int age, Year graduationYear, Degree degree) {
        super(name, age);
        this.graduationYear = graduationYear;
        this.degree = degree;
    }

    public Year getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear() {
        this.graduationYear = graduationYear;
    }

    @Override
    public int getAge() {
        return age;
    }

    public Degree getDegree() throws NoDegreeException {
        if (degree == null) {
            throw new NoDegreeException("This person never completed a degree.");
        } else {
            return degree;
        }
    }

    public String getDegreeName() throws NoDegreeException {
        if (degree == null) {
            throw new NoDegreeException("This person never completed a degree.");
        } else {
            return degree.getDegreeLevel();
        }
    }

    @Override
    public void printInfo() {
        try {
            logger.info(getName() + " is " + getAge() + " years old and graduated in " + getGraduationYear() + " with the " + getDegreeName() + ".");
        } catch (NoDegreeException e) {
            logger.info(getName() + " is " + getAge() + " years old and never completed a degree.");
        }
    }

    @Override
    public String toString() {
        return name;
    }


    }


package com.laba.solvd.person;

import com.laba.solvd.enums.EmploymentStatus;
import com.laba.solvd.faculty.Faculty;

import java.util.Set;

public class Professor extends Person {
    private Set<String> classesTaught;
    private EmploymentStatus employmentStatus;
    private static final int MAX_CLASSES;

    static {
        MAX_CLASSES = 2;
    }

    public Professor(String name, int age, Set<String> classesTaught, EmploymentStatus employmentStatus) {
        super(name, age);
        this.classesTaught = classesTaught;
        this.employmentStatus = employmentStatus;
    }

    public Set<String> getClassesTaught() {
        return classesTaught;
    }

    public void addClass(String classTaught) {
        classesTaught.add(classTaught);
        logger.info("New class added. Total number of classes: " + classesTaught.size());
    }

    public void removeClass(String classTaught) {
        classesTaught.remove(classTaught);
        logger.info("Class removed. Total number of alumni: " + classesTaught.size());
    }

    public EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }

    public String getEmploymentStatusName() {
        return employmentStatus.getProfessorStatus();
    }

    public static int getMaxClasses() {
        return MAX_CLASSES;
    }

    public int getNumOfClasses() {
        if (classesTaught.size() >= getMaxClasses()) {
            throw new RuntimeException("This professor is trying to teach more than two classes. This is not allowed unless he's Julius Caesar.");
        } else {
            return classesTaught.size();
        }
    }

    public void teachClass(String schoolClass) {
        try {
            classesTaught.add(schoolClass);
            logger.warn(getName() + " teaches " + getNumOfClasses() + " class(es).");
        } catch (RuntimeException e) {
            logger.warn("No professor can teach more than 2 classes.");
        }
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void printInfo() {
        logger.info(getName() + " is " + getAge() + " years old " + getEmploymentStatusName() + " professor who teaches the following classes: " + getClassesTaught() + ".");
    }

    @Override
    public String toString() {
        return name;
    }

}
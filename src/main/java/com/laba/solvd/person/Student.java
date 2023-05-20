package com.laba.solvd.person;

import com.laba.solvd.exceptions.UnderageStudentsException;
import com.laba.solvd.enums.AcademicYear;
import com.laba.solvd.enums.Gender;

public class Student extends Person {
    private Gender gender;
    private AcademicYear academicYear;


    public Student(String name, int age, Gender gender, AcademicYear academicYear) {
        super(name, age);
        this.gender = gender;
        this.academicYear = academicYear;
    }


    @Override
    public int getAge() throws UnderageStudentsException {
        if (age < 18) {
            throw new UnderageStudentsException("The student is a minor. There is not an age requirement for applying to Harvard, though applicants are expected to have some secondary school experience.");
        } else {
            return age;
        }
    }

    public Gender getGender() {
        return gender;
    }

    public void printPronoun(Student student) {
        System.out.println(student.getGender().getPronoun());
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public String getAcademicYearName() {
        return academicYear.getYearName();
    }

    @Override
    public void printInfo() throws UnderageStudentsException {
        try {
            logger.info(getName() + " is a " + getAge() + " years old " + getAcademicYearName() + ".");
        } catch (UnderageStudentsException e) {
            logger.info(getName() + " is a " + age + " years old " + getAcademicYearName() + ".");
        }
    }

    @Override
    public String toString() {
        return name;
    }
}

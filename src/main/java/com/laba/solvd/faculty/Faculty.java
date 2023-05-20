package com.laba.solvd.faculty;

import com.laba.solvd.exceptions.UnderageStudentsException;
import com.laba.solvd.interfaces.*;
import com.laba.solvd.exceptions.NoAlumniException;
import com.laba.solvd.exceptions.NoProfessorsException;
import com.laba.solvd.exceptions.NoStudentsException;
import com.laba.solvd.person.*;
import com.laba.solvd.enums.Campus;
import org.apache.log4j.Logger;

import java.time.Year;
import java.util.List;
import java.util.Set;

public abstract class Faculty implements IPrintInfo, IGetName, IGetLocationAndYear, IGetNumOfStudents {
    protected static final Logger logger = Logger.getLogger(Faculty.class);

    protected String name;
    protected String location;
    protected Year foundingYear;
    protected Campus campus;
    protected PersonList professors;
    protected List<Student> students;
    protected List<Alumnus> alumni;
    private static int totalFaculties;

    static {
        totalFaculties = 0;
    }

    public Faculty(String name, String location, Year foundingYear, Campus campus, PersonList professors, List<Student> students, List<Alumnus> alumni) {
        this.name = name;
        this.foundingYear = foundingYear;
        this.location = location;
        this.campus = campus;
        this.professors = professors;
        this.students = students;
        this.alumni = alumni;
        totalFaculties++;
    }

    // getters and setters
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public Year getFoundingYear() {
        return foundingYear;
    }

    public void setFoundingYear(Year foundingYear) {
        this.foundingYear = foundingYear;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public PersonList getProfessors() {
        return professors;
    }

    public void setProfessors(PersonList professors) {
        this.professors = professors;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Alumnus> getAlumni() {
        return alumni;
    }

    public void setAlumni(List<Alumnus> alumni) {
        this.alumni = alumni;
    }

    public String getCampusName() {
        return campus.getCampusName();
    }




    // add and remove students and staff

    public void hireProfessor(Professor professor) {
        professors.add(professor);
        logger.info("New professor hired. Total number of professors: " + professors.size());
    }

    public void layoffProfessor(Professor professor) {
        professors.remove(professor);
        logger.info("Professor laid off. Total number of professors: " + professors.size());
    }

    public void enrollStudent(Student student) throws UnderageStudentsException {
        try {
            int age = student.getAge();
            students.add(student);
            logger.info("New student enrolled. Total number of students: " + students.size());
        } catch (UnderageStudentsException e) {
            students.add(student);
            logger.info("New underage student enrolled. Total number of students: " + students.size());
        }
    }

    public void expelStudent(Student student) {
        students.remove(student);
        logger.info("Student expelled. Total number of students: " + students.size());
    }

    public void graduateStudent(Student student, Alumnus alumnus) {
        students.remove(student);
        alumni.add(alumnus);
        logger.info("Student graduated. Total number of students: " + students.size() + ". Total number of alumni: " + alumni.size());
    }

    public void addAlumnus(Alumnus alumnus) {
        alumni.add(alumnus);
        logger.info("New alumnus added. Total number of alumni: " + alumni.size());
    }

    public void removeAlumnus(Alumnus alumnus) {
        alumni.remove(alumnus);
        logger.info("Alumnus removed. Total number of alumni: " + alumni.size());
    }

    // get number

    public int getNumOfProfessors() throws NoProfessorsException {
        if (professors.size() == 0) {
            throw new NoProfessorsException(getName() + " has no professors. This is frightfully odd.");
        }
        return professors.size();
    }

    @Override
    public int getNumOfStudents() throws NoStudentsException {
        if (students.size() == 0) {
            throw new NoStudentsException(getName() + " has no students. This is awfully strange.");
        }
        return students.size();
    }

    public int getNumberOfAlumni() throws NoAlumniException {
        if (alumni.size() == 0) {
            throw new NoAlumniException(getName() + " has no alumni. This is somewhat unconventional.");
        }
        return alumni.size();
    }

    public int getTotalPeople() {
        return students.size() + alumni.size() + professors.size();
    }

    // overridden methods
    @Override
    public abstract void printInfo() throws RuntimeException, NoProfessorsException, NoAlumniException, NoStudentsException;

    public abstract void printCampusInfo();

    public abstract void printProfessorsFieldOfStudy();

    @Override
    public String toString() {
        return name;
    }

    // PersonList methods

    public void getProfessorName() {
        logger.info(professors.getName());
    }

    public static int getTotalFaculties() {
        return totalFaculties;
    }

}

package com.laba.solvd.faculty;


import com.laba.solvd.customlinkedlist.CustomLinkedList;
import com.laba.solvd.exceptions.NoAlumniException;
import com.laba.solvd.exceptions.NoProfessorsException;
import com.laba.solvd.exceptions.NoStudentsException;
import com.laba.solvd.person.Alumnus;
import com.laba.solvd.person.PersonList;
import com.laba.solvd.person.Student;
import com.laba.solvd.enums.Campus;

import java.time.Year;
import java.util.List;


public class FacultyOfArtsAndSciences extends Faculty {
    private int PhDStudents;

    public FacultyOfArtsAndSciences(int numOfPhDStudents, String name, String location, Year foundingYear, Campus campus, PersonList professors, CustomLinkedList<Student> students, List<Alumnus> alumni) {
        super(name, location, foundingYear, campus, professors, students, alumni);
        this.PhDStudents = numOfPhDStudents;
    }


    public int getCountOfStudentsWithoutPhD() {
        return students.size() - PhDStudents;
    }
    public int getNumOfMBAStudents() {
        return PhDStudents;
    }

    public void setNumOfMBAStudents(int numOfMBAStudents) {
        this.PhDStudents = PhDStudents;
    }

    public void graduatePhDStudent(Alumnus alumnus) {
        PhDStudents--;
        alumni.add(alumnus);
        logger.info("A PhD student has graduated. Total number of PhD students: " + PhDStudents + ". Total number of alumni: " + alumni.size());
    }

    @Override
    public void printInfo() throws NoStudentsException, NoProfessorsException, NoAlumniException {
        String info = name + " located in " + location + " (" + getLocation() + ")" + " was founded in " + foundingYear + ". It has ";

        try {
            info += getNumOfStudents() + " student(s), ";
        } catch (NoStudentsException nse) {
            info += "no students, ";
            logger.warn(getName() + " has no students.");
        }

        try {
            info += getNumOfProfessors() + " professor(s), ";
        } catch (NoProfessorsException npe) {
            info += "no professors, ";
            logger.warn(getName() + " has no professors.");
        }

        try {
            info += getNumberOfAlumni() + " alumnus(-i), and ";
        } catch (NoAlumniException nae) {
            info += "no alumni, and ";
            logger.warn(getName() + " has no alumni.");
        }

        info += PhDStudents + " PhD student(s).";
        logger.info(info);
    }

    @Override
    public void printCampusInfo() {
        logger.info("The campus of " + getName() + " is located in " + campus.getCampusName() + ".");
    }
    @Override
    public void printProfessorsFieldOfStudy() {
        logger.info("The " + professors.size() + " professor(s) teaching at " + name + " can have one or more of the following titles: Professor of Biology, Professor of Chemistry, Professor of Physics, Professor of Mathematics, Professor of Psychology, Professor of English Literature, Professor of History, Professor of Political Science, Professor of Economics, Professor of Sociology, etc." );
    }

}

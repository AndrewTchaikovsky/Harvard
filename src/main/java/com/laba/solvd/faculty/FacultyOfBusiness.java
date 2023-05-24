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

public class FacultyOfBusiness extends Faculty {
    private int MBAStudents;

    public FacultyOfBusiness(int mbaStudents, String name, String location, Year foundingYear, Campus campus, PersonList professors, CustomLinkedList<Student> students, List<Alumnus> alumni) {
        super(name, location, foundingYear, campus, professors, students, alumni);
        this.MBAStudents = mbaStudents;
    }

    public int getCountOfStudentsWithoutMBA() {
        return students.size() - MBAStudents;
    }

    public void graduateMBAStudent(Alumnus alumnus) {
        MBAStudents--;
        alumni.add(alumnus);
        logger.info("An MBA student has graduated. Total number of MBA students: " + MBAStudents + ". Total number of alumni: " + alumni.size());
    }



    @Override
    public void printInfo() throws NoStudentsException, NoProfessorsException, NoAlumniException {
        String info = name + " located in " + getCampusName() + " (" + location + ")" + " was founded in " + foundingYear + ". It has ";

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
            info += getNumberOfAlumni() + " alumnus(-i), ";
        } catch (NoAlumniException nae) {
            info += "no alumni, and ";
            logger.warn(getName() + " has no alumni.");
        }

        info += MBAStudents + " MBA students.";
        logger.info(info);
    }

    @Override
    public void printCampusInfo() {
        logger.info("The campus of the Harvard Business School is located in " + campus.getCampusName() + ".");
    }
    @Override
    public void printProfessorsFieldOfStudy() {
        logger.info("The " + professors.size() + " professor(s) teaching at " + name + " can have one or more of the following titles: Professor of Business Administration, Professor of Finance, Professor of Marketing, Professor of Leadership, Professor of Entrepreneurship, Professor of Business Ethics, etc." );
    }

}

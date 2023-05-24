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

public class FacultyOfLaw extends Faculty {
    private String legalClinic;

    public FacultyOfLaw(String legalClinic, String name, String location, Year foundingYear, Campus campus, PersonList professors, CustomLinkedList<Student> students, List<Alumnus> alumni) {
        super(name, location, foundingYear, campus, professors, students, alumni);
        this.legalClinic = legalClinic;
    }


    public String getLegalClinic() {
        return legalClinic;
    }

    public void setLegalClinic(String legalClinic) {
        this.legalClinic = legalClinic;
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

        info += getLegalClinic() + " is one of its legal clinics.";
        logger.info(info);
    }

    @Override
    public void printCampusInfo() {
        logger.info("The campus of the Harvard Law School is located in " + campus.getCampusName() + ".");
    }
    @Override
    public void printProfessorsFieldOfStudy() {
        logger.info("The " + professors.size() + " professor(s) teaching at " + name + " can have one or more of the following titles: Professor of Constitutional Law, Professor of Criminal Law, Professor of International Law, Professor of Corporate Law, Professor of Civil Procedure, Professor of Environmental Law, Professor of Human Rights Law, Professor of Intellectual Property Law, Professor of Family Law, Professor of Legal Ethics, etc." );
    }

}

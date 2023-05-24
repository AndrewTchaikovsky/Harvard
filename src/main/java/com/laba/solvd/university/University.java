package com.laba.solvd.university;

import com.laba.solvd.exceptions.NoFacultiesException;
import com.laba.solvd.exceptions.NoStudentsException;
import com.laba.solvd.faculty.Faculty;
import com.laba.solvd.interfaces.IGetNumOfStudents;
import com.laba.solvd.interfaces.IGetLocationAndYear;
import com.laba.solvd.interfaces.IGetName;
import com.laba.solvd.interfaces.IPrintInfo;
import com.laba.solvd.person.Student;
import com.laba.solvd.enums.AcademicYear;
import com.laba.solvd.enums.Gender;
import org.apache.log4j.Logger;

import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.*;

public final class University implements IPrintInfo, IGetName, IGetLocationAndYear, IGetNumOfStudents {
    private static final Logger logger = Logger.getLogger(University.class);
    private final String name;
    private final String location;
    private final Year foundingYear;
    private final List<Faculty> faculties;


    public University(String name, String location, Year foundingYear, List<Faculty> faculties) {
        this.name = name;
        this.location = location;
        this.foundingYear = foundingYear;
        this.faculties = faculties;
    }


    @Override
    public final String getName() {
        return name;
    }

    @Override
    public final String getLocation() {
        return location;
    }

    @Override
    public final Year getFoundingYear() {
        return foundingYear;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public void addFaculty(Faculty faculty) {
        faculties.add(faculty);
        logger.info("New faculty added. Total number of faculties: " + faculties.size());
    }

    public void removeFaculty(Faculty faculty) {
        faculties.remove(faculty);
        logger.info("Faculty removed. Total number of alumni: " + faculties.size());
    }

    public int getNumFaculties() throws NoFacultiesException {
        if (faculties.size() == 0) {
            throw new NoFacultiesException(getName() + " has no faculties. This is exceedingly peculiar.");
        }
        return faculties.size();
    }

    public void printNumFaculties() {
        try {
            logger.info(getName() + " has " + getNumFaculties() + " faculty(-ies).");
        } catch (NoFacultiesException nfe) {
            logger.warn(getName() + " has no faculties.");
        }
    }

    @Override
    public int getNumOfStudents() throws NoStudentsException {
        int countOfStudents = 0;
        boolean hasStudents = false;
        for (Faculty faculty : faculties) {
            try {
                countOfStudents += faculty.getNumOfStudents();
                if (faculty.getNumOfStudents() > 0) {
                    hasStudents = true;
                }
            } catch (NoStudentsException ignored) {
            }
        }
        if (!hasStudents) {
            throw new NoStudentsException(getName() + " has no students. This is jolly queer, old chap!");
        }
        return countOfStudents;
    }

    public void printNumOfStudents() {
        try {
            logger.info(getName() + " has " + getNumOfStudents() + " student(s).");
        } catch (NoStudentsException nse) {
            logger.warn(getName() + " has no student(s).");
        }
    }

    @Override
    public final void printInfo() {
        try {
            logger.info(getName() + " located in " + getLocation() + " was founded in " + getFoundingYear() + ". " + getName() + " has " + getNumFaculties() + " faculty(-ies).");
        } catch (NoFacultiesException nfe) {
            logger.warn(getName() + " located in " + getLocation() + " was founded in " + getFoundingYear() + ". " + getName() + " has no faculties.");
        }
    }

    // equals, hashCode, toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        University that = (University) o;

        if (!getName().equals(that.getName())) return false;
        if (!getLocation().equals(that.getLocation())) return false;
        return getFoundingYear().equals(that.getFoundingYear());
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + location.hashCode();
        result = 31 * result + foundingYear.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return name + " " + location + " " + foundingYear + " " + faculties;
    }


    // more methods

    public void addFirstFaculty(Faculty faculty) {
        faculties.add(0, faculty);
        logger.info("The first faculty has been added. " + getName() + " has the following faculties: " + faculties + ".");
    }

    public void addFaculties(List<Faculty> newFaculties) {
        faculties.addAll(newFaculties);
        logger.info("New faculties have been added. " + getName() + " has the following faculties: " + faculties + ".");
    }

    public void deleteFaculties() {
        faculties.clear();
        logger.info("All faculties have been deleted. " + getName() + " has no faculties: " + faculties + ".");
    }

    public void containsFaculty(Faculty faculty) {
        if (faculties.contains(faculty)) {
            logger.info(faculty + " is a part of " + getName() + ".");
        } else {
            logger.info(faculty + " is not a part of " + getName() + ".");
        }
    }

    public void containsFaculties(List<Faculty> faculties) {
        if (faculties.containsAll(faculties)) {
            logger.info(faculties + " are part of " + getName() + ".");
        } else {
            logger.info(faculties + " are not part of " + getName() + ".");
        }
    }

    public void getFirstFaculty() {
        logger.info("The first faculty in " + getName() + " is " + faculties.get(0) + ".");
    }

    public void removeFirstFaculty() {
        faculties.remove(0);
    }

    public void printFacultyIndex(Faculty faculty) {
        if (faculties.indexOf(faculty) == -1) {
            logger.info(faculty.getName() + " is not on the list of faculties at " + getName() + ".");
        } else {
            logger.info(faculty.getName() + " has index # " + faculties.indexOf(faculty) + " on the list of faculties at " + getName() + ".");
        }
    }

    public void isEmpty(List<Faculty> faculties) {
        if (faculties.isEmpty()) {
            logger.info(getName() + " has no faculties.");
        } else {
            logger.info(getName() + " has faculties.");
        }
    }

    public int countSchoolFaculties() {
        return (int) faculties.stream()
                .filter(faculty -> faculty.getName().toLowerCase().contains("school"))
                .count();
        }

    public void printSchoolFaculties() {
        if (countSchoolFaculties() == 0) {
            logger.info(getName() + " has no faculties containing the word school in their name.");
        } else {
            logger.info(getName() + " has " + countSchoolFaculties() + " faculty(-ies) containing the word school in their name.");
        }

    }
}

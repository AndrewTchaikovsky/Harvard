package com.laba.solvd;

import java.io.*;
import java.time.Year;

import com.laba.solvd.enums.*;
import com.laba.solvd.faculty.Faculty;
import com.laba.solvd.faculty.FacultyOfArtsAndSciences;
import com.laba.solvd.faculty.FacultyOfBusiness;
import com.laba.solvd.faculty.FacultyOfLaw;
import com.laba.solvd.person.*;

import org.apache.log4j.Logger;
import com.laba.solvd.university.University;

import java.util.*;


public class Main {
    public static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        // read from file
            File sampleFile = new File("src/main/resources/sample.txt");

        try (Scanner scanner = new Scanner(sampleFile);
             FileWriter writer = new FileWriter("src/main/resources/sample2.txt")) {

            String sampleText = scanner.nextLine();
            String[] sampleWords = sampleText.split(" ");
            Set<String> uniqueWords = new HashSet<>(Arrays.asList(sampleWords));
            int count = uniqueWords.size();
            writer.write(Integer.toString(count));
        } catch(IOException e) {
            logger.error("An error occurred while processing the file.", e);
        }


        // professors
        Set<String> classesKaelin = new HashSet<>();
        classesKaelin.add("Molecular and Cellular Basis of Cancer");
        classesKaelin.add("Hematology/Oncology Journal Club");
        Professor kaelin = new Professor("William Kaelin", 65, classesKaelin, EmploymentStatus.FULL_TIME);
        kaelin.printInfo();
        kaelin.teachClass("Math");
        Person.printPersonName(() -> kaelin.name);
        logger.info(Professor.getMaxClasses());


        Set<String> classesMelton = new HashSet<>();
        classesMelton.add("Molecular and Cellular Biology of Development");
        classesMelton.add("Stem Cell and Regenerative Biology");
        Professor melton = new Professor("Douglas A. Melton", 69, classesMelton, EmploymentStatus.FULL_TIME);
        melton.printInfo();

        // students
        Student doe = new Student("John Doe", 17, Gender.MALE, AcademicYear.FRESHMAN);
        doe.printInfo();
        Student won = new Student("Noah Won", 18, Gender.NON_BINARY, AcademicYear.JUNIOR);
        won.printInfo();
        Student jane = new Student("Jane Doe", 18, Gender.FEMALE, AcademicYear.SOPHOMORE);
        jane.printInfo();
        jane.printPronoun(jane);
        Person.printPersonName(() -> doe.name);


        // alumni
        Alumnus kennedy = new Alumnus("John F. Kennedy", 46, Year.of(1940), Degree.BACHELORS);
        kennedy.printInfo();
        Alumnus gates = new Alumnus("Bill Gates", 67, Year.of(1977), null);
        gates.printInfo();
        Person.printPersonName(() -> gates.name);

        // faculties

        PersonList engineeringProfessor = new PersonList();
//        engineeringProfessor.add(kaelin);
        List<Student> engineeringStudents = new ArrayList<>();
//        engineeringStudents.add(doe);
        List<Alumnus> engineeringAlumni = new ArrayList<>();
//        engineeringAlumni.add(kennedy);

        FacultyOfArtsAndSciences schoolOfEngineering = new FacultyOfArtsAndSciences(4599, "Harvard John A. Paulson School of Engineering and Applied Sciences",
                "Cambridge, Massachusetts", Year.of(1847), Campus.CAMBRIDGE, engineeringProfessor, engineeringStudents, engineeringAlumni);

        schoolOfEngineering.printInfo();
        schoolOfEngineering.hireProfessor(melton);
        schoolOfEngineering.enrollStudent(won);
        schoolOfEngineering.addAlumnus(gates);
        schoolOfEngineering.printInfo();

        PersonList collegeProfessor = new PersonList();
        collegeProfessor.add(kaelin);
        List<Student> collegeStudent = new ArrayList<>();
        collegeStudent.add(doe);
        List<Alumnus> collegeAlumnus = new ArrayList<>();
        collegeAlumnus.add(kennedy);

        FacultyOfArtsAndSciences harvardCollege = new FacultyOfArtsAndSciences(0, "Harvard College",
                "Cambridge, Massachusetts", Year.of(1636), Campus.CAMBRIDGE, collegeProfessor, collegeStudent, collegeAlumnus);

        harvardCollege.printInfo();
        harvardCollege.hireProfessor(melton);
        harvardCollege.enrollStudent(won);
        harvardCollege.addAlumnus(gates);
        harvardCollege.printInfo();


        PersonList lawProfessor = new PersonList();
//        lawProfessor.add(kaelin);
        List<Student> lawStudent = new ArrayList<>();
//        lawStudent.add(doe);
        List<Alumnus> lawAlumnus = new ArrayList<>();
//        lawAlumnus.add(kennedy);

        FacultyOfLaw harvardLawSchool = new FacultyOfLaw("Criminal Justice Institute",
                "Harvard Law School", "Cambridge, Massachusetts", Year.of(1817), Campus.CAMBRIDGE, lawProfessor, lawStudent, lawAlumnus);

        harvardLawSchool.printInfo();
        harvardLawSchool.hireProfessor(melton);
        harvardLawSchool.enrollStudent(won);
        harvardLawSchool.addAlumnus(gates);
        harvardLawSchool.printInfo();

//
        PersonList businessProfessor = new PersonList();
//        businessProfessor.add(kaelin);
        List<Student> businessStudent = new ArrayList<>();
//        businessStudent.add(doe);
        List<Alumnus> businessAlumnus = new ArrayList<>();
//        businessAlumnus.add(kennedy);

        FacultyOfBusiness harvardBusinessSchool = new FacultyOfBusiness(732, "Harvard Business School",
                "Allston, Massachusetts", Year.of(1908), Campus.ALLSTON, businessProfessor, businessStudent, businessAlumnus);

        harvardBusinessSchool.printInfo();
        harvardBusinessSchool.hireProfessor(melton);
        harvardBusinessSchool.addAlumnus(gates);
        harvardBusinessSchool.printInfo();
        harvardBusinessSchool.enrollStudent(doe);
        harvardBusinessSchool.printInfo();


        // university
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(harvardBusinessSchool);
        faculties.add(harvardCollege);
        faculties.add(harvardLawSchool);


        University harvardUniversity = new University("Harvard University", "Cambridge, Massachusetts", Year.of(1636), faculties);

        System.out.println(harvardUniversity);

        // lambdas
        logger.info(harvardUniversity.numOfFacultiesFunction.apply(harvardUniversity));
        harvardUniversity.printInfoConsumer.accept(harvardUniversity);
        logger.info(harvardUniversity.studentSupplier.get());
        logger.info(harvardUniversity.hasStudentsPredicate.test(harvardUniversity));


        // equals, hashcode
        System.out.println(harvardUniversity.equals(harvardCollege));
        System.out.println(harvardUniversity.equals(harvardUniversity));
        System.out.println(harvardCollege.hashCode());

        logger.info(Person.getTotalPeople());
        harvardCollege.printInfo();
        harvardCollege.graduateStudent(doe, gates);
        harvardBusinessSchool.graduateMBAStudent(gates);
        harvardLawSchool.printCampusInfo();
        harvardBusinessSchool.printCampusInfo();;
        schoolOfEngineering.printCampusInfo();
        harvardBusinessSchool.printProfessorsFieldOfStudy();
        harvardLawSchool.printProfessorsFieldOfStudy();
        schoolOfEngineering.printProfessorsFieldOfStudy();

    }
}

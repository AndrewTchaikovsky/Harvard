package com.laba.solvd;

import java.io.*;
import java.time.Year;

import com.laba.solvd.customlinkedlist.CustomLinkedList;
import com.laba.solvd.enums.*;
import com.laba.solvd.exceptions.NoFacultiesException;
import com.laba.solvd.exceptions.NoStudentsException;
import com.laba.solvd.faculty.Faculty;
import com.laba.solvd.faculty.FacultyOfArtsAndSciences;
import com.laba.solvd.faculty.FacultyOfBusiness;
import com.laba.solvd.faculty.FacultyOfLaw;
import com.laba.solvd.person.*;

import org.apache.log4j.Logger;
import com.laba.solvd.university.University;

import java.util.*;
import java.util.function.*;


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
        } catch (IOException e) {
            logger.error("An error occurred while processing the file.", e);
        }


        // professors
        Set<String> classesKaelin = new HashSet<>();
        classesKaelin.add("Molecular and Cellular Basis of Cancer");
        classesKaelin.add("Hematology/Oncology Journal Club");
        Professor kaelin = new Professor("William Kaelin", 65, classesKaelin, EmploymentStatus.FULL_TIME);
//        Person.printPersonName(() -> kaelin.name);



        Set<String> classesMelton = new HashSet<>();
        classesMelton.add("Molecular and Cellular Biology of Development");
        classesMelton.add("Stem Cell and Regenerative Biology");
        Professor melton = new Professor("Douglas A. Melton", 69, classesMelton, EmploymentStatus.FULL_TIME);

        // students
        Student doe = new Student("John Doe", 19, Gender.MALE, AcademicYear.FRESHMAN);
        Student won = new Student("Noah Won", 19, Gender.NON_BINARY, AcademicYear.JUNIOR);
        Student jane = new Student("Jane Doe", 18, Gender.FEMALE, AcademicYear.SOPHOMORE);
//        Person.printPersonName(() -> doe.name);


        // alumni
        Alumnus kennedy = new Alumnus("John F. Kennedy", 46, Year.of(1940), Degree.BACHELORS);
        Alumnus gates = new Alumnus("Bill Gates", 67, Year.of(1977), null);
//        Person.printPersonName(() -> gates.name);

        // faculties

        PersonList engineeringProfessor = new PersonList();
        engineeringProfessor.add(kaelin);
        CustomLinkedList<Student> engineeringStudents = new CustomLinkedList<>();
        engineeringStudents.add(doe);
        List<Alumnus> engineeringAlumni = new ArrayList<>();
        engineeringAlumni.add(kennedy);

        FacultyOfArtsAndSciences schoolOfEngineering = new FacultyOfArtsAndSciences(4599, "Harvard John A. Paulson School of Engineering and Applied Sciences",
                "Cambridge, Massachusetts", Year.of(1847), Campus.CAMBRIDGE, engineeringProfessor, engineeringStudents, engineeringAlumni);

        schoolOfEngineering.hireProfessor(melton, engineeringProfessor);
        schoolOfEngineering.enrollStudent(won, engineeringStudents);
        schoolOfEngineering.addAlumnus(gates,engineeringAlumni);
        schoolOfEngineering.printInfo();



        Iterator<Student> iterator = engineeringStudents.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            student.printInfo();
        }

        logger.info(engineeringStudents.add(doe));
        logger.info(engineeringStudents.get(0));
        logger.info(engineeringStudents.set(0,won));
        logger.info(engineeringStudents.get(0));
        engineeringStudents.add(1,doe);
        logger.info(engineeringStudents.get(1));
        logger.info(engineeringStudents.remove(1));
        logger.info(engineeringStudents.remove(doe));
        logger.info(engineeringStudents);



        PersonList collegeProfessor = new PersonList();
        collegeProfessor.add(kaelin);
        CustomLinkedList<Student> collegeStudent = new CustomLinkedList<>();
        collegeStudent.add(doe);
        List<Alumnus> collegeAlumnus = new ArrayList<>();
        collegeAlumnus.add(kennedy);

        FacultyOfArtsAndSciences harvardCollege = new FacultyOfArtsAndSciences(0, "Harvard College",
                "Cambridge, Massachusetts", Year.of(1636), Campus.CAMBRIDGE, collegeProfessor, collegeStudent, collegeAlumnus);

        harvardCollege.hireProfessor(melton, collegeProfessor);
        harvardCollege.enrollStudent(won, collegeStudent);
        harvardCollege.addAlumnus(gates, collegeAlumnus);
        harvardCollege.printInfo();


        PersonList lawProfessor = new PersonList();
        lawProfessor.add(kaelin);
        CustomLinkedList<Student> lawStudent = new CustomLinkedList<>();
        lawStudent.add(doe);
        List<Alumnus> lawAlumnus = new ArrayList<>();
        lawAlumnus.add(kennedy);

        FacultyOfLaw harvardLawSchool = new FacultyOfLaw("Criminal Justice Institute",
                "Harvard Law School", "Cambridge, Massachusetts", Year.of(1817), Campus.CAMBRIDGE, lawProfessor, lawStudent, lawAlumnus);

        harvardLawSchool.hireProfessor(melton, lawProfessor);
        harvardLawSchool.enrollStudent(won, lawStudent);
        harvardLawSchool.addAlumnus(gates, lawAlumnus);
        harvardLawSchool.printInfo();

        PersonList businessProfessor = new PersonList();
        businessProfessor.add(kaelin);
        CustomLinkedList<Student> businessStudent = new CustomLinkedList<>();
        businessStudent.add(doe);
        List<Alumnus> businessAlumnus = new ArrayList<>();
        businessAlumnus.add(kennedy);

        FacultyOfBusiness harvardBusinessSchool = new FacultyOfBusiness(732, "Harvard Business School",
                "Allston, Massachusetts", Year.of(1908), Campus.ALLSTON, businessProfessor, businessStudent, businessAlumnus);

        harvardBusinessSchool.hireProfessor(melton, businessProfessor);
        harvardBusinessSchool.addAlumnus(gates, businessAlumnus);
        harvardBusinessSchool.enrollStudent(doe, businessStudent);
        harvardBusinessSchool.printInfo();


        // university
        List<Faculty> faculties = new ArrayList<>();
        faculties.add(harvardBusinessSchool);
        faculties.add(harvardCollege);
        faculties.add(harvardLawSchool);

        University harvardUniversity = new University("Harvard University", "Cambridge, Massachusetts", Year.of(1636), faculties);


        // lambdas
        Function<University, Integer> numOfFacultiesFunction = university -> {
            try {
                return university.getNumFaculties();
            } catch (NoFacultiesException e) {
                return 0;
            }
        };

        Consumer<University> printInfoConsumer = university -> {
            university.printInfo();
        };

        Supplier<Student> studentSupplier = () -> {
            return new Student("John Doe", 17, Gender.MALE, AcademicYear.FRESHMAN);
        };

        Predicate<University> hasStudentsPredicate = university -> {
            try {
                int numStudents = university.getNumOfStudents();
                return numStudents > 0;
            } catch (NoStudentsException nse) {
                return true;
            }
        };

        UnaryOperator<University> capitalizeName = university -> {
            String capitalizedName = university.getName().toUpperCase();
            return new University(capitalizedName, university.getLocation(), university.getFoundingYear(), university.getFaculties());
        };

        schoolOfEngineering.printProfessorsInfo();
        schoolOfEngineering.printAdultStudentsGender();
        schoolOfEngineering.printAllAlumniAge();
        schoolOfEngineering.getAverageMaleStudentAge();

    }
}

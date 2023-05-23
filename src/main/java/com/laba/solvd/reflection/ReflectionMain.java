package com.laba.solvd.reflection;

import com.laba.solvd.Main;
import com.laba.solvd.customlinkedlist.CustomLinkedList;
import com.laba.solvd.enums.Campus;
import com.laba.solvd.exceptions.NoAlumniException;
import com.laba.solvd.exceptions.NoProfessorsException;
import com.laba.solvd.exceptions.NoStudentsException;
import com.laba.solvd.faculty.Faculty;
import com.laba.solvd.faculty.FacultyOfArtsAndSciences;
import com.laba.solvd.faculty.FacultyOfLaw;
import com.laba.solvd.person.Alumnus;
import com.laba.solvd.person.PersonList;
import com.laba.solvd.person.Student;
import com.laba.solvd.university.University;
import org.apache.log4j.Logger;
import java.lang.reflect.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReflectionMain {
    public static Logger logger = Logger.getLogger(ReflectionMain.class);

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> universityClass = University.class;

        // fields
        Field[] fields = universityClass.getDeclaredFields();
        for (Field field : fields) {
            Class<?> fieldType = field.getType();
            String fieldName = field.getName();
            logger.info(fieldName);
        }

        // constructor
        List<String> constructorName = Stream.of(University.class.getConstructor(String.class, String.class, Year.class, List.class))
                .map(Constructor::getName)
                .collect(Collectors.toList());
        logger.info(constructorName);

        List<String> constructorModifier = Stream.of(University.class.getConstructor(String.class, String.class, Year.class, List.class))
                .map(Constructor::getModifiers)
                .map(Modifier::toString)
                .collect(Collectors.toList());
        logger.info(constructorModifier);


        // methods
        List<String> methodName = Stream.of(universityClass.getDeclaredMethods())
                .map(Method::getName)
                .collect(Collectors.toList());
        logger.info(methodName);

        List<String> methodModifiers = Stream.of(universityClass.getDeclaredMethods())
                .map(Method::getModifiers)
                .map(Modifier::toString)
                .collect(Collectors.toList());
        logger.info(methodModifiers);


        List<Faculty> faculties = new ArrayList<>();
        University yaleUniversity = new University("Yale University", "New Haven, Connecticut", Year.of(1701), faculties);

        PersonList medicalProfessor = new PersonList();
        CustomLinkedList<Student> medicalStudents = new CustomLinkedList<>();
        List<Alumnus> medicalAlumni = new ArrayList<>();

        FacultyOfLaw yaleLawSchool = new FacultyOfLaw("Supreme Court Advocacy Clinic",
                "Yale Law School", "New Haven, Connecticut", Year.of(1810), Campus.NEW_HAVEN, medicalProfessor, medicalStudents, medicalAlumni);

        Method addFirstFacultyMethod = universityClass.getMethod("addFirstFaculty", Faculty.class);
        addFirstFacultyMethod.invoke(yaleUniversity, yaleLawSchool);
        yaleUniversity.printInfo();

    }
}

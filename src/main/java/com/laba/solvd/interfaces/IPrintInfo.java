package com.laba.solvd.interfaces;

import com.laba.solvd.exceptions.*;

@FunctionalInterface

public interface IPrintInfo {
    void printInfo() throws NoFacultiesException, NoStudentsException, NoProfessorsException, NoAlumniException, UnderageStudentsException;
}

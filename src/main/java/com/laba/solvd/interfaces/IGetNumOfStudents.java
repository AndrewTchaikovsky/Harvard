package com.laba.solvd.interfaces;

import com.laba.solvd.exceptions.NoStudentsException;
@FunctionalInterface
public interface IGetNumOfStudents {
    int getNumOfStudents() throws NoStudentsException;
}

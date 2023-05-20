package com.laba.solvd.person;

import com.laba.solvd.exceptions.UnderageStudentsException;
import com.laba.solvd.interfaces.IPrintInfo;
import com.laba.solvd.interfaces.IGetName;
import org.apache.log4j.Logger;


public abstract class Person implements IPrintInfo, IGetName {
    protected static final Logger logger = Logger.getLogger(Person.class);
    public String name;
    protected int age;
    private static int totalPeople;

    static {
        totalPeople = 0;
    }


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        totalPeople++;
    }

    public static void printPersonName(IGetName person) {
        logger.info(person.getName());
    }

    @Override
    public String getName() {
        return name;
    }

    public abstract int getAge() throws UnderageStudentsException;

    public abstract void printInfo() throws UnderageStudentsException;

    @Override
    public abstract String toString();

    public static int getTotalPeople() {
        return totalPeople;
    }


}

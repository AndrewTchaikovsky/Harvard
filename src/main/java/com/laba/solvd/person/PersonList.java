package com.laba.solvd.person;

import com.laba.solvd.interfaces.IGetName;
import com.laba.solvd.interfaces.IPersonCollection;
import com.laba.solvd.interfaces.IPrintInfo;
import java.util.*;

import static com.laba.solvd.person.Person.logger;

public class PersonList extends ArrayList<Person> implements IPrintInfo, IGetName, IPersonCollection {

    public boolean addPerson(Person person) {
        return add(person);
    }
    public boolean removePerson(Person person) {
        return remove(person);
    }

    @Override
    public String getName() {
        return get(0).getName();
    }

    @Override
    public void printInfo() {
        logger.info("This is " + getName());
    }
}

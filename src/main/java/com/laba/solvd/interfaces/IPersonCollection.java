package com.laba.solvd.interfaces;

import com.laba.solvd.person.Person;
import java.util.List;

public interface IPersonCollection extends List<Person> {
boolean addPerson(Person person);
boolean removePerson(Person person);

}

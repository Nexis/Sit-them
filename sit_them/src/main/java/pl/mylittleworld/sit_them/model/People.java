package pl.mylittleworld.sit_them.model;

import java.util.ArrayList;
import java.util.List;

import pl.mylittleworld.sit_them.SittingPlanProxy;


public class People {

    private List<Person> people;
    private String name;
    private static int personId=-1;

    public People() {
        this.people=new ArrayList<>();
    }

    public People(List<Person> people) {
        this.people = people;
    }

    public boolean addPerson(Person person){
        return people.add(person);
    }

    public boolean removePerson(Person person){

        return people.remove(person);
    }

    public Person getPersonAt(int index) {
        return people.get(index);
    }

    public String getName() {
        return name;
    }

    public boolean areAllPeopleSitted(SittingPlanProxy sittingPlanProxy){
        for(Person person : people){
            if(sittingPlanProxy.whereSits(person.getPersonID())==null) {
                return false;
            }
        }
        return true;
    }

    public static int nextPersonId() {
        ++personId;
        return personId;
    }

    public List<Person> getPeople() {
        return people;
    }
}

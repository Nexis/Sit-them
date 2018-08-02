package pl.mylittleworld.usadz_ich;

import java.util.ArrayList;
import java.util.List;


public class People {

    private List<Person> people;
    private String name;

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
}

package az.iktlab.step_projects.step_project1;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class HumanUtil {
    private HumanUtil() {
    }

    public static List<Person> loadPeople() {
        List<Person> personList = new LinkedList<>();

        Person person1 = new Person("John", "Doe", 25, "male");
        Person person2 = new Person("Jane", "Smith", 30, "female");
        Person person3 = new Person("Michael", "Johnson", 22, "male");
        Person person4 = new Person("Emily", "Williams", 82, "null");
        Person person5 = new Person("William", "Brown", 78, "male");
        Person person6 = new Person("Olivia", "Jones", 21, "female");
        Person person7 = new Person("James", "Miller", 27, "male");
        Person person8 = new Person("Sophia", "Davis", 24, "female");
        Person person9 = new Person("Robert", "Garcia", 29, "male");
        Person person10 = new Person("Ava", "Martinez", 29, "female");
        Person person11 = new Person("David", "Lopez", 23, "male");
        Person person12 = new Person("Emma", "Rodriguez", 31, "female");
        Person person13 = new Person("Daniel", "Wilson", 32, "male");
        Person person14 = new Person("Mia", "Anderson", 34, "female");
        Person person15 = new Person("Joseph", "Harris", 35, "male");

        person1.setFather(person4);
        person1.setMother(person5);
        person2.setFather(person8);
        person2.setMother(person9);
        person3.setFather(person6);
        person3.setMother(person7);
        person4.setFather(person10);
        person4.setMother(person11);
        person5.setFather(person12);
        person5.setMother(person13);
        person6.setFather(person14);
        person6.setMother(person15);

        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);
        personList.add(person5);
        personList.add(person6);
        personList.add(person7);
        personList.add(person8);
        personList.add(person9);
        personList.add(person10);
        personList.add(person11);
        personList.add(person12);
        personList.add(person13);
        personList.add(person14);
        personList.add(person15);

        return personList;
    }

    public static void showPeopleByNameSortedDesc(List<Person> people) {
        people.stream()
                .sorted(Comparator.comparing(Person::getName, Comparator.reverseOrder()))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void showPeopleByNameSortedAsc(List<Person> people) {
        people.stream()
                .sorted(Comparator.comparing(Person::getName))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void showPeopleBySurnameSortedDesc(List<Person> people) {
        people.stream()
                .sorted(Comparator.comparing(Person::getSurname, Comparator.reverseOrder()))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void showPeopleBySurnameSortedAsc(List<Person> people) {
        people.stream()
                .sorted(Comparator.comparing(Person::getSurname))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void showPeopleByAgeSortedDesc(List<Person> people) {
        people.stream()
                .sorted(Comparator.comparing(Person::getAge, Comparator.reverseOrder()))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void showPeopleByAgeSortedAsc(List<Person> people) {
        people.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static int findAvgAge(List<Person> people) {
        return (int) people.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0);
    }

    public static Person findOlderPerson(List<Person> people) {

        return people.stream()
                .max(Comparator.comparing(Person::getAge))
                .orElse(null);
    }

    public static Person findYoungerPerson(List<Person> people) {

        return people.stream()
                .min(Comparator.comparing(Person::getAge))
                .orElse(null);
    }
}
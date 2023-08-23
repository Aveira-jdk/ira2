package az.iktlab.step_projects.step_project1;


import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Person> people = HumanUtil.loadPeople();

//        HumanUtil.showPeopleByNameSortedDesc(people);

//        String data = FileUtil.parseData(people);
//        FileUtil.writeToFile(data,
//                "/Applications/idea/java-group-sip/docs/human.json");

        FileUtil.readFromFile("C:\\Java-Projects\\ira2\\docs\\human.json")
                .forEach(System.out::println);

    }

}
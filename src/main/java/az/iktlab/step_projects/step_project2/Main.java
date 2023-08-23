package az.iktlab.step_projects.step_project2;

import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Main {

    public static void main(String[] args) throws SQLException {
        // connecting to the database using connection lib
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "toor");

        // creating scanner and giving menu choices using strange if condition offered by ide
        try (Scanner sc = new Scanner(System.in)) {
            for (int i = 0; true;) {
                System.out.print("Enter a command (sp/ru/shp/del/exit): ");
                String command = sc.nextLine();

                if (!command.equals("sp")) {
                    if (!command.equals("ru")) {
                        if (!command.equals("shp")) {
                            if (command.equals("del")){
                                deletePerson(connection, sc);
                            }
                            else if (command.equals("exit")) {
                                return;
                            }
                            else {
                                System.out.println("Again");
                            }
                        } else {
                            showPeople(connection);
                        }
                    } else {
                        registerUser(connection, sc);
                    }
                } else {
                    savePerson(connection, sc);
                }

            }
        }
    }

    // savePerson method for adding information about people to the table, I would name it addPerson
    private static void savePerson(Connection connection, Scanner sc) throws SQLException {
        System.out.print("Enter name: "); // asks person's name
        String name = sc.nextLine();

        System.out.print("Enter surname: "); // asks person's surname
        String surname = sc.nextLine();

        System.out.print("Enter age: "); // asks person's age
        int age = Integer.parseInt(sc.nextLine());

        System.out.print("Enter gender: "); // asks person's gender
        String gender = sc.nextLine();

        System.out.print("Enter mother: "); // asks person's mother
        String mother = sc.nextLine();

        System.out.print("Enter father: "); // asks person's father
        String father = sc.nextLine();

        // prepareStatement adds values entered before to the specified table in database
        PreparedStatement preparedStatement = connection.prepareStatement("insert into person (name, surname, age, gender, mother, father) values (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setInt(3, age);
        preparedStatement.setString(4, gender);
        preparedStatement.setString(5, mother);
        preparedStatement.setString(6, father);
        preparedStatement.executeUpdate();
        System.out.println("Person saved successfully");
    }

    // userRegister method for adding existing people to user_account table
    private static void registerUser(Connection connection, Scanner sc) throws SQLException {
        System.out.print("Enter person id: "); // asks person's id
        int personId = Integer.parseInt(sc.nextLine());

        System.out.print("Enter username: "); // asks the username that person will have in the user_account table
        String username = sc.nextLine();

        System.out.print("Enter password: "); // asks person's password
        String password = sc.nextLine();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into user_account (person_id, username, password) values (?, ?, ?)");
        preparedStatement.setInt(1, personId);
        preparedStatement.setString(2, username);
        preparedStatement.setString(3, password);
        preparedStatement.executeUpdate();
        System.out.println("User registered");
    }

// ShowPeople method, it prints all information about people from table including id
    private static void showPeople(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM person"); // sets info you entered to rows

        boolean hasRecords = false; // for checking the table for people's existing, from the beginning it's 0

        while (resultSet.next()) {
            hasRecords = true;

            // setting people's parameters
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");

            int age = resultSet.getInt("age");
            String gender = resultSet.getString("gender");
            String mother = resultSet.getString("mother");
            String father = resultSet.getString("father");

            // printing parameters
            System.out.println("ID: " + id + ", NAME: " + name + ", SURNAME: " + surname +
                    ", AGE: " + age + ", GENDER: " + gender + ", MOTHER: " + mother + ", FATHER: " + father);
        }

        if (!hasRecords) { // if there is no one it prints message
            System.out.println("You are alone :)");
        }
    }

// Useless headache causing deletePerson method by bard that took two days to complete and fix bugs
// PS I hate it
    public static void deletePerson(Connection connection, Scanner sc) throws SQLException {
        System.out.print("Enter person's id: ");
        int personId = Integer.parseInt(sc.nextLine());

        Statement countStatement = connection.createStatement();
        ResultSet countResult = countStatement.executeQuery("select count(*) from person");
        countResult.next();
        int rowCount = countResult.getInt(1);

        if (rowCount == 0) {
            System.out.println("There are no one");
            return;
        }

        PreparedStatement deleteUserAccount = connection.prepareStatement("delete from user_account where person_id = ?");
        deleteUserAccount.setInt(1, personId);
        deleteUserAccount.executeUpdate();

        PreparedStatement deletePerson = connection.prepareStatement("delete from person where id = ?");
        deletePerson.setInt(1, personId);
        deletePerson.executeUpdate();

        Statement resetSequence = connection.createStatement();
        resetSequence.execute("alter sequence person_id_seq restart with 1");

        System.out.println("You just killed someone and destroyed all the data about him or her");
    }
}

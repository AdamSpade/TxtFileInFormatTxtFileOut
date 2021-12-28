import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/* Utility class with methods for formatting the imported data, creating Person objects, adding Person objects to a list,
and sorting the list of Person objects into the desired format */
public class Utility {

    /* Variables */
    private static List<Person> people = new ArrayList<Person>();
    private static String line = null;
    private static Map<String, List<Person>> groupByLastName;
    private static Comparator<Person> compareByName;
    private static int count;

    /* A while-loop used to read through each line of the imported text file. Each line is added to an array,
    * and then split into a new Person object. */
    public static List<Person> createPersonObjFromData(BufferedReader bufferedReader) {

        /* each line is formatted so it can be split by the comma between each section of data.
        Then each segment is added to an index of an array */
        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] objects = line
                        .replace(".,", ".")
                        .replace("\"", "")
                        .toUpperCase()
                        .split(",");

                /* each index from the array represents a parameter of a Person object (eg. firstname, address, age)
                and is used to build a new Person object */
                String fName = objects[0];
                String lName = objects[1];
                String address = objects[2];
                String city = objects[3];
                String state = objects[4];
                int age = Integer.parseInt(objects[5]);

                /* each new Person object gets added to 'people' ArrayList */
                people.add(new Person(fName, lName, address, city, state, age));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* ArrayList populated with all Person objects is returned */
        return people;
    }

    /* A map with String as the key and List<Person> as the value for the parameters used.
    This map sorts the 'people' ArrayList of Person objects by first then last name using the
    'compareByName' comparator. It then filters out any persons under age 18 and groups
    remaining persons by same last name */
    public static Map<String, List<Person>> groupByHousehold(List<Person> persons){
        groupByLastName = people.stream()
                        .sorted(firstNameLastNameComparator())
                        .filter(p -> p.getAge() >= 18)
                        .collect(Collectors.groupingBy(Person::getLastName));
        return groupByLastName;
    }

    /* A comparator to sort the Person objects by first name and then by last name
    (it won't correctly sort the last names in ascending order, not sure why) */
    public static Comparator<Person> firstNameLastNameComparator() {
        compareByName = Comparator.comparing(Person::getFirstName)
                        .thenComparing(Person::getLastName);
        return compareByName;
    }

    /* A method to loop through the 'groupByLastName' Map and print the households
    and their family members to the PrintWriter. */
    public static PrintWriter addHouseholdOutputToPrintWriter(Map<String, List<Person>> groupByLastName, PrintWriter printWriter) {
        groupByLastName.forEach((family, familyMember) -> {
            int count = (int) familyMember.stream().count();
            printWriter.println(family + " HOUSEHOLD --- " + "FAMILY MEMBERS: " + count);
            familyMember.forEach(printWriter::println);
            printWriter.println();
        });
        printWriter.close();
        return printWriter;
    }
}
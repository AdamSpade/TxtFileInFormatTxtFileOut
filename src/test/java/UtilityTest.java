import org.junit.jupiter.api.Test;

class UtilityTest {

    @Test
    void createPersonObjFromData() {
        /* Write a test to check that this method can read each line from a bufferedreader variable,
         * format the line, add line to an array, add array to person object, then add person object
         * to a list */
    }

    @Test
    void groupByHousehold() {
        /* Write test to check that this method can sort a list of Person objects by the
        * custom comparator (firstNameLastNameComparator), filtering out any ages below 18,
        * and then grouping the Persons into groups by last name */
    }

    @Test
    void firstNameLastNameComparator() {
        /* Verify that this comparator sorts persons by first name, then by last name */
    }

    @Test
    void addHouseholdOutputToPrintWriter() {
        /* Write test to check that this method will add each group of names to a
        * PrintWriter file and show the number of family members in each houshold */
    }
}
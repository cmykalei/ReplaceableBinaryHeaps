import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import java.io.*;
import java.time.LocalTime;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Test;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestRide{

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    private static final LocalTime TIME = LocalTime.of(12,00);
    private static final String[] NAME = {"Ada Lovelace", "Edith Clarke", "Grace Hopper", "Katherine Johnson", "Marie Curie", "Temple Grandin"};
    private static final int ID = 100;
    private static final int START = 200;
    private static final int END = 300;

    @Order(1)
    @Nested
    @DisplayName("Ride Initialisation Tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RideInitialisation{

        /**
         * Asserts field 'ID' is set correctly.
         * 
         * @param   id      Specifies the Stream of int ID numbers.
         */
        @Order(1)
        @MethodSource("Parameters#ids")
        @ParameterizedTest(name = "Set ID Test {index} -> ID = {0}")
        @DisplayName("Ride's ID is set correctly when initialised.")
        public void testField_ID(int id){

            Ride ride = new Ride(id, TIME, START, END);

            assertEquals(id, ride.ID);
        }

        /**
         * Asserts field 'time' is set correctly.
         * 
         * @param   time    Specifies the Stream of LocalTime values.
         */
        @Order(2)
        @MethodSource("Parameters#times")
        @ParameterizedTest(name = "Set Time Test {index} -> time = {0}")
        @DisplayName("Ride's time of request is set correctly when initialised.")
        public void testField_time(LocalTime time){

            Ride ride = new Ride(ID, time, START, END);

            assertEquals(time, ride.time, "LocalTime");
        }

        /**
         * Asserts fields 'start' and 'end' locations are set correctly.
         * Note: Class Ride assumes that the values which are passed to its
         *       constructor are valid location ID's.
         * 
         * @param   start   Specifies the Stream of int start locations.
         * @param   end     Specifies the Stream of int end locations.
         */
        @Order(3)
        @MethodSource("Parameters#locationArgs")
        @ParameterizedTest(name = "Set Locations Test {index} -> start = {0}, end = {0}")
        @DisplayName("Ride's start and end locations are set correctly when initialised.")
        public void testField_start_end(int start, int end){

            Ride ride = new Ride(ID, TIME, start, end);

            assertEquals(ride.start, start);
            assertEquals(ride.end, end);
        }

        /**
         * Asserts variable 'route' field is calculated correctly.
         * 
         * @param   start   Specifies the Stream of int start locations.
         * @param   end     Specifies the Stream of int end locations.
         * @param   route   Specifies the Stream of the calculated int routes. 
         */
        @Order(4)
        @MethodSource("Parameters#routeArgs")
        @ParameterizedTest(name = "Calculate Route Test ({0},{1}) -> route = {2}")
        @DisplayName("Ride's route is calculated correctly when initialised.")
        public void testField_route(int start, int end, int route){

            Ride ride = new Ride(ID, TIME, start, end); 

            assertEquals(route, ride.route);
        }

         /**
         * Asserts variable 'route' is set to 0 if start and end are equal.
         * Note: Ride constructor calls a private method to check the 
         *       condition of each value before it calculates the number 
         *       to set into its field.
         * 
         * @param   start   Specifies the Stream of int start locations.
         * @param   end     Specifies the Stream of int end locations.
         */
        @Order(5)
        @MethodSource("Parameters#locationArgs")
        @ParameterizedTest(name = "Invalid Route Test ({0},{1}) -> route = 0")
        @DisplayName("Ride's route is set to 0 if start and end are equal.")
        public void testField_routeNull(int start, int end){

            Ride ride = new Ride(ID, TIME, start, start);

            assertEquals(0, ride.route);
        }

        /**
         * Asserts that array 'passengers' is empty when initialised.
         * 
         * @param   ride    Specifies the Stream of new Ride instances.
         */
        @Order(6)
        @ParameterizedTest(name = "New Ride {index} -> passengers[] = null")
        @MethodSource("Parameters#rides")
        @DisplayName("Ride is initialised with no existing passengers.")
        public void testField_passengers(Ride ride){

            assertNull(ride.passengers[0]);
            assertNull(ride.passengers[1]);
            assertNull(ride.passengers[2]);
            assertNull(ride.passengers[3]);
            assertNull(ride.passengers[4]);
            assertNull(ride.passengers[5]);
        }

        /**
         * Test asserts that variable 'count' is 0 when initialised.
         * 
         * @param   ride    Specifies the Stream of new Ride instances
         */
        @Order(7)
        @MethodSource("Parameters#rides")
        @ParameterizedTest(name = "New Ride {index} -> count = 0")
        @DisplayName("Ride is initialised with count of 0.")
        public void testField_count(Ride ride){

            assertEquals(0, ride.count);
        }
    }

    @Order(2)
    @Nested
    @DisplayName("Ride Display Tests")
    class RideDisplay{

        /**
         * Asserts that passengersToString() returns the correct passenger
         * names as a single String in the correct format.
         * 
         * Note: Each name should have a newline character at the END so that the
         *       String output looks like a list.
         * 
         * @see             Ride#passengersToString()
         */
        @Test
        @DisplayName("The passengersToString method returns each name on a new line.")
        public void testMethod_passengersToString(){

            Ride ride = new Ride(ID, TIME, START, END);
            ride.addArray(NAME);
            String a = NAME[0] + "\n";
            String b = NAME[1] + "\n";
            String c = NAME[2] + "\n";
            String d = NAME[3] + "\n";
            String e = NAME[4] + "\n";
            String f = NAME[5] + "\n";
            String expected = a+b+c+d+e+f;
       
            assertEquals(expected, ride.passengersToString());
        }

        /**
         * Test asserts that toString() overrides to return its Ride's information
         * as a single String in the correct format.
         * 
         * Note: The Ride class uses constants 'TYPE' which defines the vehicle,
         *       and 'ITEM' which defines the cargo. 
         * 
         * @see             Ride#toString()
         * @param   ride    Specifies the Stream of Ride objects with various attributes.
         */
        @ParameterizedTest
        @DisplayName("The toString method returns the correct String format of a Ride")
        @MethodSource("Parameters#rides")
        public void testMethod_toString(Ride ride){

            String a = (String.format("%n-----%s %03d-----%n", ride.TYPE, ride.ID));
            String b = (String.format("Time:  %tT%n", ride.time));
            String c = (String.format("Start: %03d%n", ride.start));
            String d = (String.format("End: %03d%n", ride.end));
            String e = (String.format("Route ID: %03d-%03d%n", ride.start, ride.end));
            String f = (String.format("%ss: %d%n", ride.ITEM, ride.count));
            String g = (ride.passengersToString());
            String h = ("--------------------\n");
            String expected = a+b+c+d+e+f+g+h;

            assertEquals(expected, ride.toString());
        }      
    }

    @Order(3)
    @Nested
    @DisplayName("Ride State Tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RideState{

        /**
         * Test asserts that variable count is incremented when a passenger is
         * added to its array.
         * Note: This test is dependent on method addPassenger.
         * @see             Ride#count  
         */
        @Order(1)
        @Test
        @DisplayName("Ride increments count when a passenger is added.")
        public void testVariable_countIncrement(){

            Ride ride = new Ride(ID, TIME, START, END);

            ride.addPassenger("Passenger 1");
            ride.addPassenger("Passenger 2");

            assertEquals(2, ride.count);
        }

        /**
         * Asserts that isEmpty returns true.
         * Note: Method isEmpty checks the field 'count' is 0.
         * 
         * @see         Ride#isEmpty()
         * @see         Ride#count
         */
        @Order(2)
        @Test
        @DisplayName("The isEmpty method returns true if a Ride has no passengers.")
        public void testMethod_isEmptyTrue(){
            
            Ride ride = new Ride(ID, TIME, START, END);

            assertTrue(ride.isEmpty());
        }

        /**
         * Asserts that isEmpty returns false.
         * Note: Test is dependent on method addPassenger.
         * 
         * @see         Ride#isEmpty()
         * @see         Ride#addPassenger(String name)
         */
        @Order(3)
        @Test
        @DisplayName("The isEmpty method returns false if a Ride has one passenger.")
        public void testMethod_isEmptyFalse(){
            
            Ride ride = new Ride(ID, TIME, START, END);
            ride.addPassenger("Passenger");

            assertFalse(ride.isEmpty());
        }

        /**
         * Test asserts that isFull returns true.
         * Note: This test assumes that constant 'MAXSEATS' is set to 6.
         * 
         * @see         Ride#isFull()
         * @see         Ride#MAXSEATS
         */
        @Order(4)
        @Test
        @DisplayName("The isFull method returns true if a Ride has 6 passengers.")
        public void testMethod_isFullTrue(){
            
            Ride ride = new Ride(ID, TIME, START, END);

            ride.addPassenger("Passenger 1");
            ride.addPassenger("Passenger 2");
            ride.addPassenger("Passenger 3");
            ride.addPassenger("Passenger 4");
            ride.addPassenger("Passenger 5");
            ride.addPassenger("Passenger 6");

            assertTrue(ride.isFull());
        }

        /**
         * Test asserts that isFull returns false.
         * Note: This test assumes that constant 'MAXSEATS' is set to 6.
         * 
         * @see         Ride#isFull()
         * @see         Ride#MAXSEATS
         */
        @Order(5)
        @Test
        @DisplayName("The isFull method returns false if a Ride does not have 6 passengers.")
        public void testMethod_isFullFalse(){
            
            Ride ride = new Ride(ID, TIME, START, END);

            ride.addPassenger("Passenger 1");

            assertFalse(ride.isFull());
        }
    }

    @Order(4)
    @Nested
    @DisplayName("Ride Comparisons Tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RideComparisons{

        /**
         * Asserts that isIdentical returns true.
         * 
         * @see         Ride#isIdentical(Ride other)
         */
        @Order(1)
        @Test
        @DisplayName("The isIdentical method returns true: If the Ride has the same ID.")
        public void testMethod_isIdenticalTrue(){
            
            Ride ride = new Ride(1, TIME, START, END);
            Ride other = new Ride(1, TIME, START, END);

            assertTrue(ride.isIdentical(other));
        }

        /**
         * Asserts that isIdentical returns false.
         * Note: The isIdentical method checks only the field 'ID' for equality, 
         *       since MinHeap does not allow duplicate ID numbers for Rides.
         * 
         * @see         Ride#isIdentical(Ride other)
         * @see         Ride#ID
         */
        @Order(2)
        @Test
        @DisplayName("The isIdentical method returns false: If the Ride has a different ID.")
        public void testMethod_isIdenticalFalse(){
            
            Ride ride = new Ride(0, TIME, START, END);
            Ride other = new Ride(1, TIME, START, END);

            assertFalse(ride.isIdentical(other));
        }

        /**
         * Test asserts that method isDuration returns false.
         * Where the other Ride is assigned a TIME of 11 minutes later.
         * 
         * Note: This test class assumes that the interval limit is 10 minutes.
         *     
         * @see             Ride#inDuration(Ride other, int limit)
         */
        @Order(3)
        @DisplayName("The inDuration method returns false: If the Rides are more than 10 minutes apart.")
        public void testMethod_inDurationFalse(){
            
            Ride ride = new Ride(ID, TIME, START, END);
            Ride other = new Ride(ID, TIME.plusMinutes(11), START, END);

            assertFalse(ride.inDuration(other, 10));
        }

        /**
         * Test asserts that method isDuration returns true.
         * Note: This test class assumes that the interval limit is 10 minutes.
         *     
         * @see             Ride#inDuration(Ride other, int limit)
         * @param   mins    Specifies the Stream of int values from 0-10.
         */
        @Order(4)
        @ParameterizedTest(name = "{0} minutes later.")
        @MethodSource("Parameters#mins")
        @DisplayName("The inDuration method returns true if the other Ride is:")
        public void testMethod_inDurationTrue(int mins){
            
            Ride ride = new Ride(ID, TIME, START, END);
            Ride other = new Ride(ID, TIME.plusMinutes(mins), START, END);

            assertTrue(ride.inDuration(other, 10));
        }

        /**
         * Test asserts that method compareTo returns a negative int.
         * The other Ride is assigned a later time of plus 1 minute.
         *     
         * @see             Ride#compareTo(Ride other)
         * @param   mins    Specifies the Stream of int values from 0-10.
         */
        @Order(5)
        @ParameterizedTest(name = "{0} minutes later")
        @MethodSource("Parameters#mins")
        @DisplayName("The compareTo method returns a negative int if the other Ride is:")
        public void testMethod_compareToLater(int mins){

            Ride sooner = new Ride(ID, TIME, START, END);
            Ride later = new Ride(ID, TIME.plusMinutes(mins), START, END);

            int actual = sooner.compareTo(later);

            assertTrue(actual < 0);
        }

        /**
         * Test asserts that compareTo(Ride other) returns a positive int.
         * Where the this Ride is assigned a later TIME of plus 1 minute.
         *     
         * @see             Ride#compareTo(Ride other)
         * @param   mins    Specifies the Stream of int values from 0-10.
         */
        @Order(6)
        @ParameterizedTest(name = "{0} minutes sooner")
        @MethodSource("Parameters#mins")
        @DisplayName("The compareTo method returns a positive int if the other Ride is:")
        public void testMethod_compareToSooner(int mins){

            Ride sooner = new Ride(ID, TIME, START, END);
            Ride later = new Ride(ID, TIME.plusMinutes(mins), START, END);

            int actual = later.compareTo(sooner);

            assertTrue(actual > 0);
        }

        /**
         * Test asserts that compareTo(Ride other) returns 0.
         * Where both Rides have the same TIME.
         *     
         * @see             Ride#compareTo(Ride other)
         * @param   time    Specifies the Stream of LocalTime values.
         */
        @Order(7)
        @ParameterizedTest(name = "If both Rides have time {0}")
        @MethodSource("Parameters#times")
        @DisplayName("The compareTo method returns 0:")
        public void testMethod_compareToEqual(LocalTime time){
            
            Ride ride = new Ride(ID, time, START, END);
            Ride other = new Ride(ID, time, START, END);

            assertEquals(0, ride.compareTo(other));
        }

        /**
         * Test asserts that method isBefore returns false.
         *     
         * @see             Ride#isBefore(Ride other)
         * @param   mins    Specifies the Stream of int values from 0-10.
         */
        @Order(8)
        @ParameterizedTest(name = "{0} minutes sooner.")
        @MethodSource("Parameters#mins")
        @DisplayName("The isBefore method returns false if the other Ride is:")
        public void testMethod_isBeforeFalse(int mins){
            
            Ride sooner = new Ride(ID, TIME, START, END);
            Ride later = new Ride(ID, TIME.plusMinutes(mins), START, END);

            assertFalse(later.isBefore(sooner));
        }

        /**
         * Test asserts that method isBefore returns true.
         *     
         * @see             Ride#isBefore(Ride other)
         * @param   mins    Specifies the Stream of long values from 0-10.
         */
        @Order(9)
        @ParameterizedTest(name = "{0} minutes later.")
        @MethodSource("Parameters#mins")
        @DisplayName("The isBefore method returns true if the other Ride is:")
        public void testMethod_isBeforeTrue(int mins){
            
            Ride sooner = new Ride(ID, TIME, START, END);
            Ride later = new Ride(ID, TIME.plusMinutes(mins), START, END);

            assertTrue(sooner.isBefore(later));
        }

        /**
         * Asserts that method hasCapacity returns true.
         * Both Ride's are assigned an array of 1-3 passengers, so that
         * all possible pairs sum up to no more than 6 passengers.
         * Note: This test assumes that Ride class constant 'MAXSEATS' is set to 6. 
         * 
         * @see             Ride#hasCapacity(Ride other)
         * @see             Ride#MAXSEATS
         * @param   array1  Specifies the array of 1-3 names in the first argument.
         * @param   array2  Specifies the array of 1-3 names in the second argument.
         */
        @Order(10)
        @ParameterizedTest(name = "Test valid combination {index}")
        @DisplayName("The hasCapacity method returns true:")
        @MethodSource("Parameters#passengerArgs()")
        public void testMethod_hasCapacityTrue(String[] array1, String[] array2){
            
            Ride ride = new Ride(ID, TIME, START, END);
            Ride other = new Ride(ID, TIME, START, END);

            ride.addArray(array1);
            other.addArray(array2);

            assertTrue(ride.hasCapacity(other));
        }

        /** 
         * Test asserts that method hasCapacity returns false.
         * Ride is assigned 6 passengers, and the other is assigned an array of
         * 1-6 passengers, so that neither has room for the other.
         * Note: This test assumes that Ride class constant 'MAXSEATS' is set to 6. 
         * 
         * @see             Ride#MAX
         * @see             Ride#hasCapacity(Ride other)
         * @param   array1  Specifies the Stream of arrays of 4-6 names.
         * @param   array2  Specifies the Stream of arrays of 4-6 names.
         */
        @Order(11)
        @ParameterizedTest(name = "Test invalid combination {index}")
        @MethodSource("Parameters#passengers()")
        @DisplayName("The hasCapacity method returns false:")
        public void testMethod_hasCapacityFalse(String[] array1, String[] array2){
            
            Ride ride = new Ride(ID, TIME, START, END);
            Ride other = new Ride(ID, TIME, START, END);

            ride.addArray(array1);
            other.addArray(array2);
            
            assertFalse(ride.hasCapacity(other));
        }
    }

    @Order(5)
    @Nested
    @DisplayName("Ride Operations Tests")
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    class RideOperations{

        /**
         * Test asserts that method addPassenger does not add a name to a 
         * Ride's passenger array if Ride is full.
         * Note: This test assumes that the maximum amount of passengers is 6.
         * 
         * @see             Ride#addPassenger(String name) 
         * @see             Ride#MAXSEATS
         */
        @Order(1)
        @Test
        @DisplayName("The addPassenger method does not add a passenger: If the Ride is full.")
        public void testBoundary_addPassenger(){

            Ride ride = new Ride(ID, TIME, START, END);
            ride.addArray(NAME);

            ride.addPassenger("Passenger 7");

            assertNotEquals(ride.passengers[0], "Passenger 7");
            assertNotEquals(ride.passengers[1], "Passenger 7");
            assertNotEquals(ride.passengers[2], "Passenger 7");
            assertNotEquals(ride.passengers[3], "Passenger 7");
            assertNotEquals(ride.passengers[4], "Passenger 7");
            assertNotEquals(ride.passengers[5], "Passenger 7");
        }

        /**
         * Test asserts that method addPassenger does not increment count
         * if Ride is full.
         * Note: This test assumes that the maximum amount of passengers is 6.
         * 
         * @see             Ride#addPassenger(String name) 
         * @see             Ride#MAXSEATS
         */
        @Order(2)
        @Test
        @DisplayName("The addPassenger method does increment count: If the Ride is full.")
        public void testBoundary_addPassengerCount(){

            Ride ride = new Ride(ID, TIME, START, END);
            ride.addArray(NAME);

            ride.addPassenger("Passenger 7");

            assertEquals(6, ride.count);
        }

        /**
         * Test asserts that method addArray adds all the names from the array its 
         * passed, assuming the length doesn't exceed the maximum amount of seats. 
         * 
         * @see             Ride#addArray(String[] array) 
         * @see             Ride#MAXSEATS
         */
        @Order(3)
        @Test
        @DisplayName("The addArray method adds the correct names to a Ride.")
        public void testMethod_addArray(){

            Ride ride = new Ride(ID, TIME, START, END);

            ride.addArray(NAME);

            assertEquals(ride.passengers[0], NAME[0]);
            assertEquals(ride.passengers[1], NAME[1]);
            assertEquals(ride.passengers[2], NAME[2]);
            assertEquals(ride.passengers[3], NAME[3]);
            assertEquals(ride.passengers[4], NAME[4]);
            assertEquals(ride.passengers[5], NAME[5]);
        }

        /**
         * Test asserts that method addPassenger adds names as expected.
         * 
         * @see             Ride#addPassenger(String name) 
         * @param   name    Specifies the Stream of String names to add to the Ride.
         */ 
        @Order(4)  
        @ParameterizedTest(name = "Test name {0} is onboard")
        @DisplayName("The addPassenger method adds names to its passenger array.")
        @MethodSource("Parameters#names")
        public void testMethod_addPassenger(String name){

            Ride ride = new Ride(ID, TIME, START, END);

            ride.addPassenger(name);

            assertEquals(name, ride.passengers[0]);
        }

        /**
         * Test asserts that method combine correctly combines the Rides' 
         * arrays, and updates the state of the other Ride which should now 
         * have no passengers.
         * 
         * Note: The combine method of Ride class calls a private method to reset 
         *       the other Ride's passenger array and its count to 0.
         * 
         * @see             Ride#combine(Ride other)
         * @see             Ride#count
         * @param   array1  Specifies the argument of arrays of 1-3 passenger names.
         * @param   array2  Specifies the argument of arrays of 1-3 passenger names.
         */
        @Order(5)
        @ParameterizedTest(name = "Other Ride {index} has count of 0")
        @DisplayName("The combine method takes passengers from the other Ride:")
        @MethodSource("Parameters#passengerArgs")
        public void testMethod_combineClear(String[] array1, String[] array2){

            Ride ride = new Ride(ID, TIME, START, END);
            Ride other = new Ride(ID, TIME, START, END);
            ride.addArray(array1);
            other.addArray(array2);

            ride.combine(other);

            assertEquals(0, other.count);
        }

        /**
         * Test asserts that method combine correctly combines the Rides' 
         * arrays, and updates the count of the this Ride which now contains the 
         * other Ride's passengers.
         * 
         * @see             Ride#combine(Ride other)
         * @see             Ride#count
         * @param   array1  Specifies the argument of arrays of 1-3 passenger names.
         * @param   array2  Specifies the argument of arrays of 1-3 passenger names.
         */
        @Order(6)
        @ParameterizedTest(name = "Ride {index} counts correctly")
        @DisplayName("The combine method correctly counts the new passengers:")
        @MethodSource("Parameters#passengerArgs")
        public void testMethod_combineCount(String[] array1, String[] array2){

            Ride ride = new Ride(ID, TIME, START, END);
            Ride other = new Ride(ID, TIME, START, END);
            ride.addArray(array1);
            other.addArray(array2);
            int expected = ride.count + other.count;
            
            ride.combine(other);

            assertEquals(expected, ride.count);
        }
    }
}
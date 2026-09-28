import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import java.io.*;
import java.time.LocalTime;
import java.util.stream.Stream;
import java.util.stream.IntStream;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestMinHeap{

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

    private static final int CARS = 20;
    private static final String[] PASSENGERS = {"Ada Lovelace", "Edith Clarke", "Grace Hopper", "Katherine Johnson", "Marie Curie", "Temple Grandin"};
    private static final LocalTime TIME = LocalTime.of(12,00);

    private MinHeap heap;

    /**
     * Tears down after each test:
     * Sets the instances of MinHeap and Rides to null.
     */
    @AfterEach
    public void tearDown(){

        System.setOut(standardOut);
        heap = null;
    }

    @Order(1)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("MinHeap Initialisation Tests")
    class MinHeapInitialisation{

        /**
         * Test asserts that
         * 
         * @see         MinHeap#next
         */
        public void testVariable_next(){

            heap = new MinHeap(20);
            assertEquals(0, heap.next);
        }

        /**
         * Test asserts that parameter size sets queue.
         * 
         * @see         MinHeap#queue
         */
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 20})
        @DisplayName("Parameter size sets the queue size.")
        public void testInstance_queue(int size){
            
            heap = new MinHeap(size);
            assertEquals(size, heap.queue.length);

        }

        /**
         * Test asserts that queue size is set to the maximum if parameter
         * size exceeds it.
         * 
         * @see         MinHeap#queue
         */
        @Test
        @DisplayName("Queue size is set to max if parameter size exceeds it.")
        public void testInstance_queue(){
            
            heap = new MinHeap(21);
            assertEquals(20, heap.queue.length);

        }
    }

    @Order(2)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("MinHeap Operations Tests")
    class MinHeapOperations{

        @Order(1)
        @ParameterizedTest
        @MethodSource("Parameters#heapQueue()")
        @DisplayName("The optimiseRides method combines the Rides if able to.")
        public void testMethod_OptimiseRides(Ride[] queue){
        }


        @Order(2)
        @ParameterizedTest
        @MethodSource("Parameters#heapQueue()")
        @DisplayName("The optimiseRides method does not include old Ride in the queue it returns.")
        public void testBoundary_OptimiseRides(Ride[] queue){

        }

        @Order(3)
        @ParameterizedTest
        @MethodSource("Parameters#heapQueue()")
        @DisplayName("The insert method does not add a Ride if its ID exists in the current heap.")
        public void testBoundary1_insert(Ride[] queue){
        }

        @Order(4)
        @ParameterizedTest
        @MethodSource("Parameters#heapQueue()")
        @DisplayName("The insert method does not add a Ride if next is greater than or equal to maximum.")
        public void testBoundary2_insert(Ride[] queue){
        }


        @Order(5)
        @ParameterizedTest
        @MethodSource("Parameters#heapQueue()")
        @DisplayName("The insert method increments 'next' after successfully inserting.")
        public void testMethod_insert(Ride[] queue){
        }


        @Order(6)
        @ParameterizedTest
        @MethodSource("Parameters#heapQueue()")
        @DisplayName("The insert method decrements 'next' after successfully removing.")
        public void testMethod_remove(Ride[] queue){
        }

        /*
        
        Test Order Restored
            -> After insertion
            -> After removal
        Test variable 'next'
            -> Reflects count
        */
    }


    @Order(3)
    @Nested
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    @DisplayName("MinHeap Display Tests")
    class MinHeapDisplay{

        /*
        
        Test Dump (OutStream.toString())
        Test Sort
            -> Checking min value
            -> Checking order
        */
    }

}

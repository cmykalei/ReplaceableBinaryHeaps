import java.time.LocalTime;
import java.util.stream.Stream;
import java.util.stream.IntStream;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

public class Parameters{

    private static final LocalTime[] TIME = {LocalTime.of(0,00), LocalTime.of(6,00), LocalTime.of(12,00), LocalTime.of(18,00)};  
    private static final String[] PASSENGER = {"Ada Lovelace", "Edith Clarke", "Grace Hopper", "Katherine Johnson", "Marie Curie", "Temple Grandin"};
    private static final int[] ID = {100, 200, 300, 400};
    private static final int[] START = {1, 13, 89, 144};
    private static final int[] END = {512, 256, 128, 64};

    /**
     * Creates four instances of Ride with unique attributes from the static
     * arrays provided in this class and returns each in a Stream.
     * 
     * @see             Ride#Ride(int id, LocalTime time, int start, int end) 
     * @return          The stream of Ride objects. 
     */
    static Ride[] heapQueue(){
    
        Ride[] heapQueue = new Ride[4];
        Ride a = new Ride(ID[0], TIME[0], START[0], END[0]);
        a.addPassenger(PASSENGER[0]);
        Ride b = new Ride(ID[1], TIME[1], START[1], END[1]);
        b.addPassenger(PASSENGER[2]);
        Ride c = new Ride(ID[2], TIME[2], START[2], END[2]);  
        c.addPassenger(PASSENGER[3]);  
        Ride d = new Ride(ID[3], TIME[3], START[3], END[3]);  
        d.addPassenger(PASSENGER[4]); 
         
        return heapQueue;
    }
    /**
     * Creates four instances of Ride with unique attributes from the static
     * arrays provided in this class and returns each in a Stream.
     * 
     * @see             Ride#Ride(int id, LocalTime time, int start, int end) 
     * @return          The stream of Ride objects. 
     */
    static Stream<Ride> rides(){
   
        Ride a = new Ride(ID[0], TIME[0], START[0], END[0]);
        Ride b = new Ride(ID[1], TIME[1], START[1], END[1]);
        Ride c = new Ride(ID[2], TIME[2], START[2], END[2]);    
        Ride d = new Ride(ID[3], TIME[3], START[3], END[3]);    
         
        return Stream.of(a, b, c, d);
    }

    /**
     * Gets the static array of four unqiue ints provided by this class and 
     * returns each number in a Stream.
     * 
     * @see             Parameters#ID
     * @return          The Stream of int ID number from the static array ID.
     */
    static IntStream ids(){

         return IntStream.of(ID[0], ID[1], ID[2], ID[3]);
    }

    /**
     * Gets the static array containing LocalTime values provided by this class
     * and returns each time in a Stream.
     * 
     * @see             Parameters#TIME
     * @return          The Stream of LocalTimes where each is an hour apart.
     */
    static Stream<LocalTime> times(){

        return Stream.of(TIME[0], TIME[1], TIME[2], TIME[3]);
    }

    /**
     * Creates a Stream of ints from 1-10 to use as minutes.
     * 
     * @return          The stream ints from 1-10
     */
    static IntStream mins(){

        return IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    }

    /**
     * Gets the static array of six names provided by this class and returns 
     * each String in a Stream.
     * 
     * @see             Parameters#PASSENGER
     * @return          The Stream of six Strings containing passenger names.
     */
    static Stream<String> names(){

         return Stream.of(PASSENGER[0], PASSENGER[1], PASSENGER[2], PASSENGER[3], PASSENGER[4], PASSENGER[5]);
    }

    /**
     * Gets the static array containing String names provided by this class
     * and splits it into three seperate arrays of different lengths, then
     * returns a each array in a Stream.
     * 
     * @see             Parameters#PASSENGER 
     * @return          The Stream of array 
     */
    static Stream<Arguments> passengers(){

        String[] a = {PASSENGER[0], PASSENGER[1], PASSENGER[2], PASSENGER[3]};
        String[] b = {PASSENGER[0], PASSENGER[1], PASSENGER[2], PASSENGER[3], PASSENGER[4]};
        String[] c = {PASSENGER[0], PASSENGER[1], PASSENGER[2], PASSENGER[3], PASSENGER[4], PASSENGER[5]};
     
        return Stream.of(
            Arguments.of(a, a), Arguments.of(a, b), Arguments.of(a, c),
            Arguments.of(b, a), Arguments.of(b, b), Arguments.of(b, c),
            Arguments.of(c, a), Arguments.of(c, b), Arguments.of(c, c));
    }

    /**
     * Creates four instances of Ride with unique attributes from the static
     * arrays provided in this class, and returns a Stream of arguments 
     * containing pairs of each Ride. 
     * 
     * @see             Ride#Ride(int id, LocalTime time, int start, int end)
     * @return          The stream of arguments containing pairs of Ride objects.
     */
    static Stream<Arguments> rideArgs(){
   
        Ride a = new Ride(ID[0], TIME[0], START[0], END[0]);
        Ride b = new Ride(ID[1], TIME[1], START[1], END[1]);
        Ride c = new Ride(ID[2], TIME[2], START[2], END[2]);       
        Ride d = new Ride(ID[3], TIME[3], START[3], END[3]);    

        return Stream.of(
            Arguments.of(a, a), Arguments.of(a, b), Arguments.of(a, c), Arguments.of(a, d), 
            Arguments.of(b, a), Arguments.of(b, b), Arguments.of(b, c), Arguments.of(b, d),
            Arguments.of(c, a), Arguments.of(c, b), Arguments.of(c, c), Arguments.of(c, d));
    }
 
    /**
     * Gets the static arrays of int start and end locations provided by this
     * class and returns a Stream of arguments where each pair is unique.
     * 
     * @see             Parameters#START
     * @see             Parameters#END
     * @return          The Stream of int location arguments where start is different to end.
     */
    static Stream<Arguments> locationArgs(){
   
        return Stream.of(
            Arguments.of(START[0], END[0]),
            Arguments.of(START[1], END[1]),
            Arguments.of(START[2], END[2]),
            Arguments.of(START[3], END[3]));
    }

    /**
     * Gets the static arrays of int start and end locations provided by this
     * class, then calculates the expected route to return a Stream of arguments 
     * where each pair also has a route.
     * 
     * @see             Parameters#START
     * @see             Parameters#END
     * @return          The Stream of int location arguments where start is different to end.
     */
    static Stream<Arguments> routeArgs(){
   
        return Stream.of(
            Arguments.of(START[0], END[0], (START[0] * 1000 + END[0])),
            Arguments.of(START[1], END[1], (START[1] * 1000 + END[1])),
            Arguments.of(START[2], END[2], (START[2] * 1000 + END[2])),
            Arguments.of(START[3], END[3], (START[3] * 1000 + END[3])));
    }

    /**
     * Gets the static array containing String names provided by this class
     * and splits it into three seperate arrays of different lengths, then
     * returns a Stream of arguments containing pairs of each array.
     * 
     * @see             Parameters#PASSENGER 
     * @return          The Stream of array pairings each containning 1-3 names.
     */
    static Stream<Arguments> passengerArgs(){
       
        String[] a = new String[]{PASSENGER[0]};
        String[] b = new String[]{PASSENGER[1], PASSENGER[2]};
        String[] c = new String[]{PASSENGER[3], PASSENGER[4], PASSENGER[5]};
        
        return Stream.of(
            Arguments.of(a, a),
            Arguments.of(a, b),
            Arguments.of(a, c),
            Arguments.of(c, c),
            Arguments.of(b, c),
            Arguments.of(b, b));
    }
}
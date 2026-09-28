import java.time.LocalTime;
import java.time.Duration;
/**
 * Ride class.
 * 
 * The Ride class implements Comparable and overrides compareTo method to return
 * the comparison of Ride timestamps. Ride uses LocalTime to represent its
 * time of request, and is written with the purpose of being instantiated in
 * the MinHeap data-structure.
 * 
 * Ride is an object which holds the information about a trip in a simluated
 * OnDemand ride-sharing service.
 */
public class Ride implements Comparable<Ride>{

    /**
     * The unique ID of this Ride is of type int.
     */
    public final int ID;
    /**
     * The time of when this Ride was requested is of type LocalTime.
     */
    public LocalTime time;
    /**
     * The start location ID for this Ride is of type int.
     */
    public int start;
    /**
     * The end location ID for this Ride is of type int.
     */
    public int end;
    /**
     * This Ride's route ID is calculated by this Ride's start and end ID's.
     */
    public int route;
    /**
     * This Ride's amount of passengers, the count of names in its array.
     * Note: The count of this Ride should only be incremented when a passenger
     *       is added, and should only be modified when all passengers are
     *       removed and it's set to 0.
     */
    public int count;
    /**
     * This Ride's array of passenger names.
     * Note: The length of this array will always be MAXSEATS,
     *       but the field 'count' should reflect the amount of
     *       passengers onboard.  
     */
    public String[] passengers; 


    /**
     * Context-specific constants for display messages.
     * Just because I thought it was amusing to see "15 Dogs on Boeing 201"
     */
    public final int MAXSEATS = 6;
    public final String TYPE = "Boeing";
    public final String ITEM = "Passenger";

    /**
     * Ride constructor.
     * 
     * Creates a new Ride object with the attribtues passed in. Sets its public
     * member variables to the specified values, calculates the route ID using
     * its start and end locations, then creates a new String array of empty
     * seats for its passengers.
     * 
     * @see             #setRoute(int start, int end)
     * @param   id      Specifies the int unique ID number of this Ride.
     * @param   time    Specifies the LocalTime of when this Ride was requested.
     * @param   start   Specifies the int 3-digit pickup location ID.
     * @param   end     Specifies the int 3-digit dropoff location ID.
     * */
    public Ride(int id, LocalTime time, int start, int end){

        this.ID = id;
        this.time = time;
        this.start = start;
        this.end = end;

        setRoute(start, end);
        passengers = new String[MAXSEATS];
        count = 0;
    }


    /**
     * The toString method overrides to format this Ride's information into
     * a single String, with each item on a new line.
     * 
     * @return          The String containing all information about this Ride. 
     */
    @Override
    public String toString(){

        StringBuilder info = new StringBuilder();
        info.append(String.format("%n--------%s %03d--------%n", TYPE, ID));
        info.append(String.format("Time:  %18tT%n", time));
        info.append(String.format("Start: %18d%n", start));
        info.append(String.format("End: %20d%n", end));
        info.append(String.format("Route ID: %7s%03d-%03d>%n", "<", start, end));
        info.append(String.format("Taken Seats: %10d/%d%n%n", count, MAXSEATS));
        info.append(this.passengersToString());
        info.append("--------------------------\n");
        return info.toString();
    }

    /**
     * The isIdentical method takes another Ride object and checks if its ID
     * is the same as this Ride's ID.
     * 
     * @param   other   Specifies the other Ride to compare to this Ride.
     * @return          True if this Ride has the same ID as the other.
     */
    public boolean isIdentical(Ride other){

        return this.ID == other.ID;
    }

    /**
     * The isFull method checks if this Ride's capacity is full.
     * 
     * @return      True if this Ride's count is at MAXSEATS, false otherwise.
     */
    public boolean isFull(){

        return this.count >= MAXSEATS;
    }

    /**
     * The isEmpty method checks if this Ride has no passengers.
     * 
     * @return          True if this Ride's count is 0, false otherwise.
     */
    public boolean isEmpty(){

        return this.count == 0;
    }

    /**
     * The compareTo method overrides to compare this Ride's time with the
     * other, and return an int result of whether its sooner or earlier.
     * 
     * @param   other   Specifies the other Ride to compare to this Ride.
     * @return          The int value of whether is ride is sooner or later.
     */
    @Override
    public int compareTo(Ride other){

        return this.time.compareTo(other.time);
    }

    /**
     * The isBefore method takes another Ride object and checks if this
     * Ride's time is sooner than the other's.
     * 
     * @param   other   Specifies the other Ride to compare to this Ride.
     * @return          True if this Ride is earlier, false otherwise.
     */
    public boolean isBefore(Ride other){

        if(other == null){
            return false;
        }
        return this.time.isBefore(other.time);
    }

    /**
     * The inDuration method takes another Ride object and an int limit,
     * then checks if the duration between this Ride's timestamps and other
     * is within the limit.
     * 
     * @param   other   Specifies the other Ride to compare to this Ride.
     * @param   limit   Specifies the int limit of minutes between two Rides.
     * @return          True if the duration is within the limit, false otherwise.
     */
    public boolean inDuration(Ride other, int limit){

        return Math.abs(Duration.between(this.time, other.time).toMinutes()) <= limit;
    }

    /**
     * The hasCapacity method takes another Ride object and checks if this Ride's
     * array has capacity for all of the Strings in the other Ride's array.
     * 
     * @param   other   Specifies the other Ride to compare to this Ride.
     * @return          True if this Ride can combine the other, false otherwise.
     */
    public boolean hasCapacity(Ride other){

        return this.count + other.count <= MAXSEATS;
    }

    /**
     * The combine method takes another Ride object and merges it's array with 
     * this Ride's array. Resets the other's array by calling the clear method.
     * 
     * @see             #addArray(String[] array)
     * @see             #clear()
     * @param   other   Specifies the other Ride to combine with this Ride.
     */
    public void combine(Ride other){

        this.addArray(other.passengers);
        other.clear();
    }

    /**
     * The addArray method takes a String array and adds each item to this Ride's 
     * array if it still has capacity, and if the array passed in is not null. 
     * 
     * Loops through the new array, while checking for null Strings, and calls 
     * the add method to handle the addition of each individual item.
     * 
     * @see             #addPassenger(String name)
     * @param   array   Specifies the array of String names to add to this Ride.
     */
    public void addArray(String[] array){

        if(array == null){
            System.out.printf("%nCouldn't merge with %s %d: Array was empty.%n", TYPE, ID);           
            return;
        }
        for(int i = 0; i < array.length; i++){

            if(array[i] == null){
                return;
            }
            addPassenger(array[i]);
        }
    }

    /**
     * The addPassenger method takes a String name and adds it to this Ride's 
     * array if the Ride has not reached MAXSEATS and if the value is not null. 
     * Then increments the value of of count include the new passenger.
     * 
     * @param   name    Specifies the String name to add to this Ride's array.
     */
    public void addPassenger(String name){
        
        if(count >= MAXSEATS){
            System.out.printf("%nCouldn't add %s to %s %d: Array is full.%n", ITEM, TYPE, ID);
            return;
        }

        if(name.strip().equals("")){
            System.out.printf("%nCouldn't add %s to %s %d: String was empty%n", ITEM, TYPE, ID);
            return;
        }
        else{
            passengers[count] = name;
            count++;           
        }
    }

    /**
     * The clear method sets this Ride's String array to null and int count to 0.
     */
    private void clear(){

        passengers = new String[MAXSEATS];
        count = 0;
    }
  
    /**
     * The arrayToString method builds a single String containing all the 
     * String names of this Ride's array of passengers, with a new line after 
     * each passenger's name.
     * 
     * @return          Each String name of this Ride's array as a single String.
     */
    public String passengersToString(){

        if(count == 0){
            return String.format("%n%s %d is empty.%n", TYPE, ID);
        }
        else{
            StringBuilder strings = new StringBuilder();
            for(int i = 0; i < count; i++){
                String s = String.format("(%d) %s%n", i+1, passengers[i]);
                strings.append(s);
            }
            for(int j = count; j < MAXSEATS; j++){
                String s = String.format("(%d) %s%n", j+1, "_");
                strings.append(s);
            }
            return strings.toString();
        }
    } 

    /**
     * The setRoute method calculates the route ID for this Ride and sets it
     * into its 'route' field. If the start and end's are the same, then sets
     * the route to 0, prints a message, then returns. 
     * 
     * Note: This class assumes a location id will be an int of up to 3-digits.
     *       And so the route ID is calculated by start * 1000 + end, to give
     *       a value which has the first 3-digits reserved for the start, and
     *       the last 3-digits for the end.
     * 
     * @param   start   Specifies the int 3-digit pickup location ID.
     * @param   end     Specifies the int 3-digit dropoff location ID.
     */
    private void setRoute(int start, int end){

        if(start == end){
            System.out.printf("%nCouldn't set route for %s %d: Start location was the same as end location.%n", TYPE, ID);   
            this.route = 0;        
            return;
        }
        else{
            this.route = start * 1000 + end;
        }
    }  
}

import java.time.LocalTime;
import java.util.Map;
import java.util.HashMap;
/**
 * MinHeap class.
 * 
 * The MinHeap class implements a dynamic replaceable minimum heap as an rides.
 * 
 * This class simulates an OnDemand ride-scheduling service, where each Ride
 * is sorted by its time of request, with priority to the soonest.
 * 
 * @see     Ride
 */
public class MinHeap{

    /**
     * The TOTAL amount of CARS available for this simulation.
     */
    public final int CARS = 20;
    /**
     * The first item is at index 0, the MIN of this MinHeap.
     */
    public final int MIN = 0;

    /**
     * This Heap's Ride ID history with timestamps.
     */
    private Map<Integer, LocalTime> cache;

    Ride[] queue;
    int next;

    /**
     * MinHeap constructor.
     * 
     * Checks if the specified size is larger than the maximum amount of CARS
     * available before creating the rides, and sets it back if so. 
     * 
     * 
     * Then creates this Heap's rides for the queue of n Ride items, and sets 
     * the next pointer to MIN for when the first item is inserted. 
     * 
     * @param   size    Specifies the amount of values, the size of this Heap.
     */
    public MinHeap(int size){

        if(size > CARS){
            size = CARS;
            System.out.printf("%nNote: size was set back to maximum of: %d%n", 20);
        }
        cache = new HashMap<>();
        queue = new Ride[size];
        next = MIN;
    }

    /**
     * The optimiseRides method finds Rides with the same location ID, then
     * checks capacity of any combinable Rides to offload passengers to.
     * 
     * Uses a HashMap to get the location ID of each Ride in the queue, where
     * the function will return null if no Ride exists with that location, or
     * returns the Ride that does. 
     * 
     * After offloading, adds that Ride to a list to the checklist to delete
     * from the main queue when the optimisation is complete.
     * 
     * @param   mins    Specifies the int frame minutes to combine Rides within.
     */
    public void optimiseRides(int mins){

        Map<Integer, Integer> routes = new HashMap<>();
        Ride[] temp = new Ride[next];
        int count = MIN;

        for(int i = MIN; i < this.next; i++){

            if(queue[i] == null){
                continue;
            }
            if(routes.containsKey(queue[i].route)){

                int v = (int)routes.get(queue[i].route);

                if(temp[v].inDuration(queue[i], mins) && temp[v].hasCapacity(queue[i])){
                    temp[v].combine(queue[i]);
                    continue;
                }
                else{
                    routes.put(queue[i].route, i);
                    temp[count] = queue[i];
                    count++;
                    continue;
                }
            } 
            else{
                routes.put(queue[i].route, i);
                temp[count] = queue[i];
                count++;
                continue;
            } 
                     
        }
        this.queue = new Ride[count];
        this.next = count;
        //heapify(temp, count);
        System.arraycopy(temp, MIN, queue, MIN, count);
    }

    /**
     * The insert method takes a Ride object and inserts it in the next index
     * of this Heap's queue rides.
     * 
     * Does not insert the Ride if its empty, if it already exists in this Heap, 
     * or if the pointer next exceeds CARS. After inserting, upheap is called to 
     * maintain heap order, and the pointer to next is incremented.
     * 
     * @param   ride    Specifies the Ride object to insert in this Heap.
     * @return          True if the Ride was inserted, or false otherwise.
     */
    public boolean insert(Ride ride){

        if(ride.isEmpty()){
            System.out.printf("%nCould not insert: Ride %d has no passengers.%n", ride.ID);
            return false;
        } 
        else if(next >= CARS){
            System.out.printf("%nCould not insert: Maximum capacity %d reached for this heap.%n", CARS);
            return false;
        } 
        else if(cache.containsKey(ride.ID)){
            System.out.printf("%nCould not insert: Ride %d is a duplicate.%n", ride.ID);
            return false;
        } 
        else{
            cache.put(ride.ID, ride.time);
            queue[next] = ride;
            upheap(next); 
            next++;   
            return true;            
        } 
    }
    /**
     * The remove method takes a Ride object then checks if it exists in this
     * Heap's queue rides and removes it if so. 
     * <p>
     * Gets the index, if it exists then decrements next so that it points back 
     * to the last item, swaps the last item with the Ride to remove, then calls 
     * downheap which sifts down from the swapped index to restore heap order,
     * before finally popping off the Ride at the index of next.
     * 
     * @param   ride    Specifies the Ride object to insert in this Heap.
     * @return          True if the Ride was removed, or false otherwise.
     */
    public boolean remove(Ride ride){

        if(isEmpty()){
            System.out.printf("Could not remove Ride %d: This heap is empty.", ride.ID);
            return false;
        } 
        else if(!cache.containsKey(ride.ID)){
            System.out.printf("Could not remove: Ride %d doesn't exist.", ride.ID);
            return false;
        }   
        else{

            for(int i = MIN; i < next; i++){
                
                if(queue[i].isIdentical(ride)){
                    next--;
                    swap(i, next);
                    downheap(i);
                    queue[next] = null;
                    cache.remove(ride.ID);
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * The isEmpty method checks if this Heap's queue is null at MIN.
     * 
     * @return  True if this Heap is empty, false otherwise.
     */
    public boolean isEmpty(){

        if(queue[MIN] == null){
            return true;
        }
        else{
            return false;
        }      
    }

    /**
     * The peek method looks at the next Ride, the minimum, in this Heap.
     * 
     * @return  The Ride object at the priority index of this Heap.
     */
    public Ride peek(){

        if(isEmpty()){
            return null;
        }
        else{
            return queue[MIN];
        }
    }

    /**
     * The peekMore method looks at Ride at the specified index, in this Heap's
     * queue, and returns the Ride if it exists.
     * 
     * @param   index   The int index to retrieve the Ride from in this Heap.
     * @return          The Ride object at the specified index.
     */
    private Ride peekMore(int index){

        if(isEmpty() || index >= next || index < MIN){
            System.out.printf("%nNothing could be found at index %d%n", index);
            return null;
        }
        else{
            return queue[index];
        }
    }

    /**
     * The heapify method takes an rides with a specified size, sorts it into
     * heap order using downheap, then replaces this existing Heap.
     * 
     * Note:    The index of the lowest parent 
     *          in this 0-indexed minimum heap 
     *          is (n - 1) / 2
     * 
     * @param   rides   Specifies the rides to heapify and replace this Heap with.
     * @param   size    Specifies the amount of values for the new heap.
     * @see             #downheap(int index)
     */
    public void heapify(Ride[] rides, int size){

        if(rides == null){
            System.out.println("Could not heapify: The new rides was null.");
            return;
        }
        else{
            if(size > CARS){
                size = CARS;
            } 
            queue = new Ride[size];
            this.next = size;

            for(int j = 0; j < size; j++){
                queue[j] = rides[j];
            }

           int parent = (size - 1) / 2;
            for(int i = parent; i >= MIN; i--){
                this.downheap(i);
            }
            this.next = size;
        }      
    }

    /**
     * The dump method prints every value in this Heap, from next to last.
     * 
     * @see         #heapify(Ride[] rides, int size)
     */
    public void dump(){

        if(isEmpty()){
            System.out.println("Could not print: This Heap is empty.");
            return;
        } 
        else{
            Ride[] schedule = sort();
            int i = 0;
            while(i < next){
                System.out.println(schedule[i].toString());
                i++;
            }
        }
    }


    /**
     * The sort method loops implements a heapsort algorithm to return the
     * heap as a sorted consecutive rides. 
     * 
     * Creates a new queue to copy each next priority value from the existing 
     * queue, where the queue's MIN is extracted upon each iteration of the
     * swap and downheap process.
     * 
     * @return          The rides of ordered Rides from soonest to latest.
     * @see             #swap(int a, int b)
     * @see             #downheap(int index)
     */
    public Ride[] sort(){

        Ride[] array = new Ride[next];

        heapify(queue, next);

        int i = 0;
        while(next > MIN){
            array[i] = queue[MIN];
            swap(MIN, --next);        
            downheap(MIN);
            i++;
        }
        this.next = i;
        return array;
    }

    /**
     * The downheap method takes an int index to set as the index of child,
     * then loops until the end of the heap to check each child's parent, 
     * left, and right values, and swaps if necessary. 
     * 
     * This is a private method that supports the construction of Heaps, the
     * sorting of Heap arrays, and the removal of Heap values.
     * 
     * @param   index   Specifies the index of the item to start downheap from.
     */
    private void downheap(int index){

                int child = index;

        while(child < next){

            int left = (2 * child) + 1;
            int right = (2 * child) + 2;
            int smallest = child;

            if(left < next && queue[left] != null){
                if(queue[left].isBefore(queue[smallest])){
                    smallest = left;
                }
            }
           if(right < next && queue[right] != null){
                if(queue[right].isBefore(queue[smallest])){
                    smallest = right;
                }
           }
            if(child != smallest){
                swap(smallest, child);
                child = smallest;            
            } 
            else{
                break;
            }        
        }

        /*int parent = index;
        int smallest = parent;

        while(parent < next){

            int left = (2 * parent) + 1;
            int right = (2 * parent) + 2;

            if(left < next && queue[left] != null){
                if(queue[left].isBefore(queue[parent])){
                    parent = left;
                }
            }
           if(right < next && queue[right] != null){
                if(queue[right].isBefore(queue[parent])){
                    parent = right;
                }
           }
            if(parent != smallest){
                swap(parent, smallest);
                smallest = parent;      
            } 
            else{
                  break;   
            }        
        }*/
    }

    /**
     * The upheap method takes an int index and moves up from each child to
     * parent in this Heap and swaps the values if necessary. 
     * 
     * This is a private method that supports the insertion of values into
     * this MinHeap by maintaining heap order.
     * 
     * @param   index   Specifies the index of the child to upheap from.
     * @see             #swap(int a, int b)
     */
    private void upheap(int index){

        int child = index;

        while(child > MIN){

            int parent = (child - 1) / 2;

            if(queue[child].isBefore(queue[parent])){
                swap(child, parent);
                child = parent;
            } 
            else{
                break;
            }       
        }
    }

    /**
     * The swap method takes two int indices then swaps values in each index
     * of this Heap's rides. 
     * 
     * @param   a       Specifies the index of item 'a' to swap with 'b'
     * @param   b       Specifies the index of item 'b' to swap with 'a'
     * @return          True if the items were swapped, or false otherwise.
     */
    private boolean swap(int a, int b) {
        
        Ride temp = queue[a];
        queue[a] = queue[b];
        queue[b] = temp;
        return true;
    }

}

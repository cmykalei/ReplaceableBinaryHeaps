import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {


        MinHeap airport = new MinHeap(20);

        Ride boeing1 = new Ride(1, LocalTime.of(01,00), 1, 2);
        Ride boeing2 = new Ride(2, LocalTime.of(01,04), 1, 2);
        Ride boeing3 = new Ride(3, LocalTime.of(01,11), 1, 2);
        Ride boeing4 = new Ride(4, LocalTime.of(01,12), 1, 2);
        Ride boeing5 = new Ride(5, LocalTime.of(01,15), 1, 2);
        Ride boeing6 = new Ride(6, LocalTime.of(02,15), 1, 2);
        Ride boeing7 = new Ride(7, LocalTime.of(02,16), 1, 2);
        Ride boeing8 = new Ride(8, LocalTime.of(02,10), 1, 2);
        Ride boeing9 = new Ride(9, LocalTime.of(06,11), 1, 2);
        Ride boeingA = new Ride(10, LocalTime.of(9,00), 1, 2);

        String[] all = {"A", "B", "C", "D", "E", "F"};
        String[] halfA = {"A", "B", "C"};
        String[] halfB = {"D", "E", "F"};


        String[] robots = {"Siri","Alexa", "Cortana", "Bixby"};
        String[] dogs = {"Stormi","Billie-Jo", "Maci", "Jenni"};
        String[] tellytubbies = {"Tinky-Winky","Lala", "Dispy", "Po"};
        String[] olsens = {"Mary-Kate", "Ashley"};
        String[] hadids = {"Gigi", "Bella"};


        boeing1.addArray(robots);
        boeing2.addArray(olsens);
        boeing3.addArray(hadids);
        boeing4.addArray(tellytubbies);
        boeing5.addArray(dogs);
        boeing6.addArray(halfB);
        boeing7.addArray(halfA);
        boeing8.addArray(all);
        boeing9.addPassenger("Kalei");
        boeingA.addPassenger("Scarlett");
      
        Ride[] flights = {boeing5, boeing4, boeing1, boeing3, boeing2, boeing8, boeing6, boeingA, boeing7, boeing9};

        // Heapify
        airport.heapify(flights, 10);
        System.out.println();
        System.out.println("***After Heapify 1***");

        System.out.println("(peek root)");
        System.out.println(airport.peek());

        System.out.println("(dump)");
        airport.dump();

        System.out.println("***END TASK***");
        System.out.println();

        // Optimise
        airport.optimiseRides(10);
        System.out.println();
        System.out.println("***After Optimize***");

        System.out.println("(peek root)");
        System.out.println(airport.peek());

        System.out.println("(dump)");
        airport.dump();

        System.out.println("***END TASK***");
        System.out.println();


       /* MinHeap test = new MinHeap(20);

        // Parameters
        LocalTime one = LocalTime.now().plusSeconds(1);
        LocalTime two = LocalTime.now().plusSeconds(2);
        LocalTime three = LocalTime.now().plusSeconds(3);
        LocalTime four = LocalTime.now().plusSeconds(4);
        LocalTime five = LocalTime.now().plusSeconds(5);
        LocalTime six = LocalTime.now().plusSeconds(6);
        LocalTime seven = LocalTime.now().plusSeconds(7);
        LocalTime eight = LocalTime.now().plusSeconds(8);

        String[] p1 = {"alpha"};
        String[] p2 = {"romeo", "juliet"};
        String[] p3 = {"alexis", "david", "moira"};
        String[] p4 = {"jenni", "maci", "billie-jo", "stormi", "jack"};
        String[] p5 = {"hotel", "india", "joker"};
        String[] p6 = {"beta", "charlie", "delta"};
        String[] p7 = {"a", "b"};
        String[] p8 = {"zebra", "helen", "goose"};
        String[] p9 = {"meow"};
        String[] p10 = {"fiji", "fuji"};



        // Ride objects
        Ride ride1 = new Ride(1, one, 111, 220);
        Ride ride2 = new Ride(2, two, 110, 220);
        Ride ride3 = new Ride(3, three, 109, 290);
        Ride ride4 = new Ride(4, four, 140, 204);
        Ride ride5 = new Ride(5, five, 100, 200);
        Ride ride6 = new Ride(6, six, 100, 200);
        Ride ride7 = new Ride(7, seven, 100, 200);
        Ride ride8 = new Ride(8, eight, 100, 200);

        // Add passenger array
        ride1.addArray(p1);
        ride2.addArray(p2);
        ride3.addArray(p3);
        ride4.addArray(p4);
        ride5.addArray(p5);
        ride6.addArray(p6);
        ride7.addArray(p7);
        ride8.addArray(p8);

        Ride[] a = new Ride[8];
        a[0] = ride1;
        a[1] = ride2;
        a[2] = ride3;
        a[3] = ride4;
        a[4] = ride5;
        a[5] = ride6;
        a[6] = ride7;
        a[7] = ride8;

        test.heapify(a,8);
        //test.insert(ride1);
        test.dump();*/


    }
}

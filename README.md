# Replaceable Binary Heap
A replaceable min-heap data structure with JUnit tests in Java.

## Project 🌳
```
.
├── HeapPrinter.java
├── Main.java
├── MinHeap.java
├── Parameters.java
├── README.md
├── Ride.java
├── TestMinHeap.java
├── TestRide.java
├── TestSuite.java
└── junit-platform-console-standalone-1.8.2.jar
```

### Usage
Run the JUnit tests using the standalone console launcher:
```bash
java -jar junit-platform-console-standalone-1.8.2.jar -cp "." -c TestRide
```

#### Test Results
```
Kaleis-MacBook-Pro:Round-8 kaleiesteves$ java -jar junit-platform-console-standalone-1.8.2.jar -cp "." -c TestRide

Thanks for using JUnit! Support its development at https://junit.org/sponsoring

╷
├─ JUnit Jupiter ✔
│  └─ TestRide ✔
│     ├─ The canCombine method returns true if the two Rides' share capacity. ✔
│     │  ├─ [1] [Ada Lovelace], [Ada Lovelace] ✔
│     │  ├─ [2] [Ada Lovelace], [Edith Clarke, Grace Hopper] ✔
│     │  ├─ [3] [Ada Lovelace], [Katherine Johnson, Marie Curie, Temple Grandin] ✔
│     │  ├─ [4] [Katherine Johnson, Marie Curie, Temple Grandin], [Katherine Johnson, Marie ... ✔
│     │  ├─ [5] [Edith Clarke, Grace Hopper], [Katherine Johnson, Marie Curie, Temple Grandi... ✔
│     │  └─ [6] [Edith Clarke, Grace Hopper], [Edith Clarke, Grace Hopper] ✔
     ├─ A Ride's route is set to 0 if start and end are the same. ✔
│     ├─ A new Ride is initialised with null passenger names. ✔
│     ├─ The isFull method returns false if a Ride isn't full. ✔
│     ├─ The passengersToString method returns each name on a new line. ✔
│     ├─ The isEmpty method returns true for a Ride without passengers. ✔
│     ├─ The addPassenger method does not add a passenger if the Ride is full. ✔
│     ├─ The isEmpty method returns false for a Ride with a passenger. ✔
│     ├─ The combine method takes passengers from the other Ride. ✔
│     │  ├─ [1] [Ada Lovelace], [Ada Lovelace] ✔
│     │  ├─ [2] [Ada Lovelace], [Edith Clarke, Grace Hopper] ✔
│     │  ├─ [3] [Ada Lovelace], [Katherine Johnson, Marie Curie, Temple Grandin] ✔
│     │  ├─ [4] [Katherine Johnson, Marie Curie, Temple Grandin], [Katherine Johnson, Marie ... ✔
│     │  ├─ [5] [Edith Clarke, Grace Hopper], [Katherine Johnson, Marie Curie, Temple Grandi... ✔
│     │  └─ [6] [Edith Clarke, Grace Hopper], [Edith Clarke, Grace Hopper] ✔
│     ├─ The combine method correctly counts the new passengers. ✔
│     │  ├─ [1] [Ada Lovelace], [Ada Lovelace] ✔
│     │  ├─ [2] [Ada Lovelace], [Edith Clarke, Grace Hopper] ✔
│     │  ├─ [3] [Ada Lovelace], [Katherine Johnson, Marie Curie, Temple Grandin] ✔
│     │  ├─ [4] [Katherine Johnson, Marie Curie, Temple Grandin], [Katherine Johnson, Marie ... ✔
│     │  ├─ [5] [Edith Clarke, Grace Hopper], [Katherine Johnson, Marie Curie, Temple Grandi... ✔
│     │  └─ [6] [Edith Clarke, Grace Hopper], [Edith Clarke, Grace Hopper] ✔
│     ├─ A Ride's ID is set correctly when initialised. ✔
│     │  ├─ [1] 1 ✔
│     │  ├─ [2] 2 ✔
│     │  ├─ [3] 3 ✔
│     │  └─ [4] 4 ✔
│     ├─ A Ride's time of request is set correctly when initialised. ✔
│     │  ├─ [1] 00:30 ✔
│     │  ├─ [2] 06:30 ✔
│     │  ├─ [3] 12:30 ✔
│     │  └─ [4] 18:30 ✔
│     ├─ The inDuration method returns true if the Rides' times are within the duration l... ✔
│     │  ├─ [1] 0 ✔
│     │  ├─ [2] 1 ✔
│     │  ├─ [3] 9 ✔
│     │  └─ [4] 10 ✔
│     ├─ A new Ride is initialised with count of 0 passengers. ✔
│     ├─ A Ride's route is calculated correctly when initialised. ✔
│     │  ├─ [1] 1, 512 ✔
│     │  ├─ [2] 13, 256 ✔
│     │  ├─ [3] 89, 128 ✔
│     │  └─ [4] 144, 64 ✔
│     ├─ A Ride's start and end locations are set correctly when initialised. ✔
│     │  ├─ [1] 1, 512 ✔
│     │  ├─ [2] 13, 256 ✔
│     │  ├─ [3] 89, 128 ✔
│     │  └─ [4] 144, 64 ✔
│     ├─ The isFull method returns true if a Ride has 6 passengers. ✔
│     ├─ The compareTo method returns a positive int if the other Ride is sooner. ✔
│     │  ├─ [1] 00:30 ✔
│     │  ├─ [2] 06:30 ✔
│     │  ├─ [3] 12:30 ✔
│     │  └─ [4] 18:30 ✔
│     ├─ The toString method returns the correct String format of its Ride information. ✔
│     │  ├─ [1]  -----Boeing 001----- Time:  00:30:00 Start: 001 End: 512 Route ID: 001-512 ... ✔
│     │  ├─ [2]  -----Boeing 002----- Time:  06:30:00 Start: 013 End: 256 Route ID: 013-256 ... ✔
│     │  └─ [3]  -----Boeing 003----- Time:  12:30:00 Start: 089 End: 128 Route ID: 089-128 ... ✔
│     ├─ The compareTo method returns 0 if the Rides have the same time. ✔
│     │  ├─ [1] 00:30 ✔
│     │  ├─ [2] 06:30 ✔
│     │  ├─ [3] 12:30 ✔
│     │  └─ [4] 18:30 ✔
│     ├─ The compareTo method returns a negative int if the other Ride is later. ✔
│     │  ├─ [1] 00:30 ✔
│     │  ├─ [2] 06:30 ✔
│     │  ├─ [3] 12:30 ✔
│     │  └─ [4] 18:30 ✔
│     ├─ The isIdentical method returns true for a Ride with the same ID. ✔
│     ├─ The isBefore method returns true if this Ride is sooner. ✔
│     │  ├─ [1] 00:30 ✔
│     │  ├─ [2] 06:30 ✔
│     │  ├─ [3] 12:30 ✔
│     │  └─ [4] 18:30 ✔
│     ├─ The addArray method adds the correct names to a Ride. ✔
│     ├─ The isIdentical method returns false for a Ride with a different ID. ✔
│     ├─ A Ride counts the correct amount of passengers ✔
│     ├─ The addPassenger method takes a name as a String and adds it to the Ride. ✔
│     │  ├─ [1] Ada Lovelace ✔
│     │  ├─ [2] Edith Clarke ✔
│     │  ├─ [3] Grace Hopper ✔
│     │  ├─ [4] Katherine Johnson ✔
│     │  ├─ [5] Marie Curie ✔
│     │  └─ [6] Temple Grandin ✔
│     ├─ The isBefore method returns false if this Ride is later. ✔
│     │  ├─ [1] 00:30 ✔
│     │  ├─ [2] 06:30 ✔
│     │  ├─ [3] 12:30 ✔
│     │  └─ [4] 18:30 ✔
│     └─ The canCombine method returns false if the two Rides' do not share capacity. ✔
└─ JUnit Vintage ✔

Test run finished after 285 ms
[        18 containers found      ]
[         0 containers skipped    ]
[        18 containers started    ]
[         0 containers aborted    ]
[        18 containers successful ]
[         0 containers failed     ]
[        81 tests found           ]
[         0 tests skipped         ]
[        81 tests started         ]
[         0 tests aborted         ]
[        81 tests successful      ]
[         0 tests failed          ]

Kaleis-MacBook-Pro:Round-8 kaleiesteves$ 
```

## Commands
1. To run the Ride tests use `java -jar junit-platform-console-standalone-1.8.2.jar -cp "." -c TestRide`.
2. To run the MinHeap tests use `java -jar junit-platform-console-standalone-1.8.2.jar -cp "." -c TestMinHeap`.

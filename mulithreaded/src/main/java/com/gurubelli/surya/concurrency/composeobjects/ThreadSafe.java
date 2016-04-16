package com.gurubelli.surya.concurrency.composeobjects;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by esrigur on 4/13/2016.
 */
public class ThreadSafe {


    /**
     * Identify the variables that form the object's state
     * Identify the invariants that constrain the state variables
     * Establish a policy for managing concurrent access to the object's state
     * @param args
     */
    public static void main(String [] args) {

    }

}

/**
 * Using Java Monitor Pattern
 */
class Counter {

    private long value = 0; //Object state .. only invariant --

    public synchronized long getValue() {
        return value;
    }

    public synchronized long incrementValue() {
        if (value == Long.MAX_VALUE) {
            throw new IllegalStateException("Counter overflow");
        }
        return value++;
    }
 /* 4.1 Gathering Synchronization requirements
    Making Thread Safe class means ensuring that it's invariants hold under concurrent access

   4.1.2 State dependent operations
    State based pre-conditions --> Removing an element from empty queue

    For efficiently waiting for a condition to become true - wait and notify
    Built in mechanisms --> blocking queues or semaphores

   4.1.3  State ownership  --> Collection Wrappers



  */
}

/***
 * Instance Confinement
 */
class PersonSet {

    private  Set<Person>  mySet = new HashSet<>(); //Not thread safe. However, my set is private and not allowed to escape.
    //The only code path that can access mySet are the methods guarded by lock



    public synchronized void addPerson(Person p) {
        mySet =  Collections.synchronizedSet(mySet);
        synchronized (mySet) {
            //Iterate
        }
        mySet.add(p);
    }

    public synchronized  boolean containsPerson(Person p) {
        return mySet.contains(p);
    }
    private class Person {

    }


}

/**
 * Delegating thread safety
 */
class DelegatingVehicleTracker {
    private final ConcurrentMap<String, Point> locations;
    private final Map<String, Point> unmodifiableMap;

    public DelegatingVehicleTracker(Map<String, Point> points) {
        locations = new ConcurrentHashMap<String, Point>(points);
        unmodifiableMap = Collections.unmodifiableMap(locations);
    }

    public Map<String, Point> getLocations() {
        return unmodifiableMap;// Live view
    }

    public Point getLocation(String id) {
        return locations.get(id);
    }

    public void setLocation(String id, int x, int y) {
        if (locations.replace(id, new Point(x, y)) == null)
            throw new IllegalArgumentException(
                    "invalid vehicle name: " + id);
    }
}
class Point {

    private final int x, y;
       public Point(int x , int y) {
           this.x = x;
           this.y = y;
       }

}

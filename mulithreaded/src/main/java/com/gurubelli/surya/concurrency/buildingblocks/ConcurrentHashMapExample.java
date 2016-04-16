package com.gurubelli.surya.concurrency.buildingblocks;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by esrigur on 4/14/2016.
 * Instead of synchronizing every method on a common lock, restricting access to
 * a single thread at a time, it uses finer-grained locking mechanism called lock striping
 * to allow a greater degree of shared access
 *
 */
public class ConcurrentHashMapExample {

    /**
     * Providing iterators are fail safe (Weakly consistent iterator)
     * Can tolerate concurrent modifications, traverses elements as they existed when the iterator was constructed
     * and may reflect modifications to the collections after the construction of the iterator
     *
     * size and isEmpty are approximate
     */
    private final Map myConcurrentMap = new ConcurrentHashMap();

    private final Map myMap = new HashMap();
}

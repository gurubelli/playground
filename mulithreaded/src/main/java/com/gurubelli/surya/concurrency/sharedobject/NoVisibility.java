package com.gurubelli.surya.concurrency.sharedobject;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by vagrant on 4/10/16.
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {


        public void run() {

            while(!ready) {
                Thread.yield();
            }
            System.out.println(number);
        }

    }
    //3.1.3 Locking and Visibility - To ensure that all threads see the most up-to-date values of shared
    //mutable variables , the reading and writing threads must synchronize on a common lock

    //3.1.4. Volatile variables
    //  When a field is declared as volatile , the compiler and runtime are put on notice that this variable is
    //  shared and that operation on it should not be reordered with other memory operations.
    //  volatile variables are not cached in registers or caches where they are hidden from other processors,
    //  so a read of a volatile variable always returns the most recent write by any thread
    //  state of the object, completion , interruption
    //  ** Writes to the variable do not depend on its current value or ensure that only a single thread ever updates the value
    //  ** The variable does not participate in invariants with other state variables
    //  ** Locking is not required for any other reason while variable is being accessed
    /// LOCKING CAN GUARANTEE BOTH THE VISIBILITY AND ATOMICITY; VOLATILE CAN ONLY GUARANTEE VISIBILITY

    /**
     *
     * @param args
     */
    public static void main(String [] args) {

        new ReaderThread().start();
        ready = true;
        number = 42;

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });

      //With Java 8 Lambda expressions


        /*Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });*/

        //
    }
}

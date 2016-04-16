package com.gurubelli.surya.concurrency.sharedobject;

/**
 * Created by esrigur on 4/12/2016.
 */
public class Immutable {
    /**
     * An immutable object is the one whose state can not be modified
     * Immutable objects are always thread safe
     */

       String threadSafe = " Immutable objects are thread safe";
        String immutableDef = " Object can not be modified after its construction, " +
                "All fields are final " +
                "It is properly constructed, does not escape during construction";

    /**
     *  Mark the class final , all field are private and final
     *  Force all the callers to construct the object directly i.e. do not use any setter methods
     *  Do not change the state of the objects in any method class
     *  If the instance fields includes references to mutable objects , do not allow those objects to be changed
     *      A) Don't provide the methods that modify the mutable objects
     *      B) Don't share references to the mutable objects.
     *      C) Never store references to external, mutable objects passed to the constructor, if necessary create copies,
     *      store references to the copies. Similarly create copies of your internal mutable objects when necessary to avoid
     *      returning the originals in your methods.
     * Advantages of immutable
     *  A) Immutable objects are thread safe
     *  B) Immutable objects are good Map keys
     *  C)
     * @param args
     */

    public static void main(String args[]) {

    }

/**
 * Thread Confined : A thread-confined object is owned exclusively by and confined to one thread.
 *
 * Shared read-only : Can be accessed concurrently by multiple threads without additional synchronization, but can not
 * be modified by any thread. e.g. Immutable or effectively immutable
 *
 * Shared thread-safe: A thread-safe object performs synchronization internally, so multiple threads can freely access it through
 * its public interface without further synchronization
 *
 * Guarded. A guarded object can be accessed only with specific lock held
 */
}

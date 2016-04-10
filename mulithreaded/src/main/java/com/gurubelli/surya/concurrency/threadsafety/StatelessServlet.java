package com.gurubelli.surya.concurrency.threadsafety;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by vagrant on 4/5/16.
 */
public class StatelessServlet {

    //With no state variables

    public static void main(String[] args) {

        System.out.println("Hello welcome to my programming");
    }

}

class UnSafeServlet {

    //Not threadsafe
    private long count = 0;

    public long getCount() {

        return count;

    }

    public void service() {
       //Race condition --> Read modify writer operations
        ++count;
    }


}
class ThreadSafeCounter {

    AtomicLong count = new AtomicLong(0);

    public void service() {
        count.incrementAndGet(); //Atomic operation.. compareAndSet()
    }
}
/**
 * Race condition : check-then-act- Lazy initialization  and put-if-absent (!contains)
 */
class LazyInitRace {

    private static LazyInitRace race = null;

    public static LazyInitRace getInstance() {
        if (race == null) {
            race = new LazyInitRace();
        }
        return race;
    }
}

//To preserve state consistency, update related state variables in a single atomic operation

//Re-entrant locks : Means that locks are acquired per thread rather than per invocation basis
//Re entrancy implemented by associating each lock an acquisition count and owning thread

//Every shared, mutable variable should be guarded by exactly one lock,
//For every invariant that involves more than one variable, all the variables involved in that invariant must be
//guarded by the same lock

//2.3.1 Intrinsic locks
// synchronized(lock)
//
package com.gurubelli.surya.concurrency.sharedobject;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vagrant on 4/10/16.
 */
public class PublishEscape {

    public static Set<Secret> knownSecrets; //Escaping the Secret Object

    private static class Secret {

    }

    public void initialize() {
        knownSecrets = new HashSet<Secret>();
        knownSecrets.add(new Secret());
    }

    public static void main(String [] args) {
        UnSafeStates unSafeStates = new UnSafeStates();
        String [] currentStates = unSafeStates.getStates();
        currentStates[0] ="CA";
        unSafeStates.getStates(); //

    }
}

/**
 * Allowing internal mutable state to escape
 */
class UnSafeStates {

    private String[] states = new String [] {"AK", "AL"};

    public String[] getStates() {
        System.out.println("Current States" + Arrays.toString(states));
        return states; //What was supposed to be private state has been effectively made public
    }
}
/**
 *Implicitly allowing this reference to escape
 */
class ThisEscape {

    public ThisEscape(Event event) {
        ///
    }
}

/**
 * Safe construction practises - Do not allow this reference to escape during construction.
 * Use factory method to return instance.
 */

package com.gurubelli.surya.concurrency.sharedobject;

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
}

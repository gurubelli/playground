package com.gurubelli.surya.concurrency.sharedobject;

import java.sql.Connection;

/**
 * Created by esrigur on 4/12/2016.
 */
public class ThreadLocalExample {

    /**
     * Creating an object per thread.  Allows to associate a per thread value with a value holding object
     * Thread Local provides get and set accessor methods that maintain a separate copy of the value for each
     * thread that uses it, so a get returns the most value passed to set from the currently executing thread
     *
     * ThreadLocal<T> is holding map<Thread, T>
     *
     * You do not need to pass thread local objects explicitly to the methods down the chain
     *
     */

    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal() {
        public Connection initialValue() {
            //return new Connection() {
            //};
            return null;
        }
    };

   public static Connection getConnection() {
      return connectionThreadLocal.get();
   }

    //For example, JEE container associate a Transaction Context with an executing thread for the duration of EJB call
    /**
     * Memory leak
     * http://javarevisited.blogspot.co.at/2013/01/thread local-memory-leak-in-java-web.html
     *
     * Web server maintains thread pool to serve HTTP requests. Now, if one the Servlet or any other java class from application
     * creates ThreadLocal variable during request processing and does not remove it after that
     * a copy of that object will remain with the worker thread .. It will prevent the object and ClassLoader which uploaded the
     * web app from being garbage collected.
     */
}

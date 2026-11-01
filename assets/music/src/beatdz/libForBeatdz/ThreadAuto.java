package beatdz.libForBeatdz;

import java.util.Hashtable;

public class ThreadAuto {

    public static Hashtable hash = new Hashtable();

    public static void stop(String name) {
        if (hash.containsKey(name)) {
            Thread thread = (Thread) hash.get(name);
            try {
                thread.interrupt();
            } catch (Exception ex) {

            }

            ////System.out.println("ThreadAuto stop ["+name+"] = "+thread.getName());
            hash.remove(name);
        }
    }

    public static void start(String name, Runnable run) {
        if (!hash.containsKey(name) || !((Thread) hash.get(name)).isAlive()) {
            Thread thread = new Thread(run, name);
            thread.start();
            System.out.println("ThreadAuto open [" + name + "] = " + thread.getName());
            hash.put(name, thread);
        }

    }
}

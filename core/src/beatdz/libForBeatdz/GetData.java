package beatdz.libForBeatdz;

import java.util.Arrays;
import java.util.Hashtable;

public class GetData {
    
    public static Hashtable hash = new Hashtable();
    
    public static Data get(String name) {
        if (hash.containsKey(name)) {
            return (Data) hash.get(name);
        }
        Data a = new Data(name);
        hash.put(name, a);
        return a;
    }
    
    public static class Data {
        
        public String name = "";
        public Object[] array = null;
        public String[] text = null;
        public long timeStart = System.currentTimeMillis();
        
        public Data(String str) {
            name = str;
        }
        
        public Object wait = new Object();
        public boolean isWait = false;
        
        public Object[] waitObject(String[] arrazy, long l) {
         System.out.println(name + "waitObject(): " +Arrays.toString(arrazy)+", "+l);
            isWait = true;
            this.text = arrazy;
            synchronized (wait) {
                try {
                    wait.wait(l);
                } catch (Exception var3) {
                }
            }
            isWait = false;
            return array;
        }
        
        public void unlockObject(Object... arrayObject) {
          //  ////System.out.println(name + "unlockObject(): " +Arrays.toString(arrayObject));
            if (isWait) {
                array = arrayObject;
                isWait = false;
                synchronized (wait) {
                    try {
                        wait.notifyAll();
                    } catch (Exception var3) {
                    }
                }
            }
        }
    }
}

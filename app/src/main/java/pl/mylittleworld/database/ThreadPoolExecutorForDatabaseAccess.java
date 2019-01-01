package pl.mylittleworld.database;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorForDatabaseAccess {


        private static ExecutorService threadPoolExecutor= Executors.newSingleThreadExecutor();

        public static ExecutorService getExecutor(){
            return threadPoolExecutor;
        }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

/**
 *
 * @author dickdijk
 */
@Singleton
public class QueueManager {
    
    private final int QUEUE_CAPACITY  = 3;
    private static int rejectedCount;
    private final int NR_OF_THREADS=1;
    
    RejectedExecutionHandler ignoreHandler;
    
    private BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(QUEUE_CAPACITY);
    private ThreadPoolExecutor executor;
   // MonitorableThreadFactory monitorableThreadFactory = new MonitorableThreadFactory();
    
    @PostConstruct
    void setUpQueue() {
        
        ignoreHandler = new RejectedExecutionHandler() {

            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("Job rejected. rejectedCount:" + rejectedCount++);
            }
        };
        
       executor  = new ThreadPoolExecutor(NR_OF_THREADS,NR_OF_THREADS, Integer.MAX_VALUE, TimeUnit.SECONDS, workQueue, Executors.defaultThreadFactory() , ignoreHandler );
    
    }
    public void addJob(Job j) {
        System.out.println("Submitting job " +j);
        executor.submit(j);
        
    }
}

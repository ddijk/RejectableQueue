/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dickdijk
 */
public class Job implements Runnable {
    
    
    private static int index;
    
    private String name;
    
    private int id;

    public Job(String name) {
        this.name = name;
        this.id = index++;
    }

    @Override
    public void run() {
        System.out.println("Running job " +id+", "+ name);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Job.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            synchronized(MyLock.lock) {
            MyLock.lock.wait();
            }
            System.out.println("woken up"+id+", "+ name);
        } catch (InterruptedException ex) {
            Logger.getLogger(Job.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "Job name "+name+"("+id+")";
    }
    
    
    
    
}

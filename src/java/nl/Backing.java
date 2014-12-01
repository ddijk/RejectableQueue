/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author dickdijk
 */
@ManagedBean
@RequestScoped
public class Backing {

    String name;

    @Inject
    QueueManager queueManager;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Creates a new instance of Backing
     */
    public Backing() {
    }

    public void runJob() {
        queueManager.addJob(new Job(name));
    }

    public void unlock() {
        System.out.println("Unlocking");
        synchronized (MyLock.lock) {
            MyLock.lock.notifyAll();
        }

    }

}

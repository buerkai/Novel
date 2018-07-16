package com.happ.happlib.thread;

import java.util.concurrent.ThreadFactory;

/**
 * Created by hyc on 17/2/15.
 */

public class HThreadFactory implements ThreadFactory {

    private ThreadGroup group;

    public HThreadFactory(ThreadGroup group) {
        this.group = group;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread= new Thread(this.group, r);
        thread.setName("ccb-"+System.currentTimeMillis());
        return thread;
    }

}
package com.happ.happlib.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by hyc on 17/2/15.
 */

public class HExecutor implements RejectedExecutionHandler {

    //队列
    private BlockingQueue<Runnable> queue= new LinkedBlockingQueue<Runnable>();

    private ThreadPoolExecutor poolTExecutor;


    private ThreadGroup group;
    private HThreadFactory threadFactory;



    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAX_POOL_SIZE = CPU_COUNT * 2 + 1;



    private  static HExecutor instance=new HExecutor();

    private HExecutor(){
        group=new ThreadGroup("happ");
        threadFactory=new HThreadFactory(group);
        poolTExecutor=new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 60, TimeUnit.SECONDS, queue,threadFactory , this);
    }

    public static HExecutor getInstance(){
        return instance;
    }

    public void stop(){
        poolTExecutor.shutdownNow();
    }

    public void addRun(Runnable task){
        this.poolTExecutor.execute(task);
    }

    public void addTask(HTask task){
        this.poolTExecutor.execute(task);
    }

    public void addTask(HTask task,int timeOutSeconds){
        task.setTimeout(timeOutSeconds);
        this.poolTExecutor.execute(task);
    }

    public void cancelTask(HTask task){
        task.cancle();
    }

    public ThreadPoolExecutor getExecutor(){
        return this.poolTExecutor;
    }



    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        if(r==null){
            return;
        }
        executor.remove(r);
        new Thread(group, r).start();
    }
}

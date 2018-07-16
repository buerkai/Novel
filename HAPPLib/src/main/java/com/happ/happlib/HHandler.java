package com.happ.happlib;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;


public class HHandler implements Handler.Callback {

    private Handler mMainThreadHandler;
    private Handler mOtherThreadHandler;

    private HandlerThread mOtherThread;
    private Lock lock = new ReentrantLock();

    private List<HandlerListener> mListeners;

    private HHandler() {
    }

    /**
     * @return void 返回类型
     * @Title: start
     * @Description: 开启handler
     * @date 2014-10-30 上午11:22:38
     */
    public void start() {
        mMainThreadHandler = new Handler(Looper.getMainLooper(),this);
        mOtherThread = new HandlerThread("other");
        mOtherThread.start();
        mOtherThreadHandler = new Handler(mOtherThread.getLooper(), this);

        mListeners = new ArrayList<HandlerListener>(10);
    }

    /**
     * @param @param otherThread 参数
     * @return void 返回类型
     * @Title: stop
     * @Description: 关闭handler
     * @date 2014-10-30 下午12:37:21
     */
    public void stop() {
        if (mOtherThreadHandler != null) {
            mOtherThreadHandler.getLooper().quit();
        }
        mListeners.clear();
    }

    /**
     * 单例
     ****/
    private static HHandler mInstance = new HHandler();

    /***
     *
     * @Title: getInstance
     * @Description:获取handler
     * @param @return 参数
     * @return HandlerHelper 返回类型
     * @date 2014-10-30 上午11:29:28
     */
    public static HHandler getInstance() {
        return mInstance;
    }

    /***
     *
     * @Title: getMainThreadHandler
     * @Description: 获取主线程handler
     * @param @return 参数
     * @return Handler 返回类型
     * @date 2014-10-30 上午11:29:43
     */
    public Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }

    /**
     * @param @return 参数
     * @return Handler 返回类型
     * @Title: getOtherThreadHandler
     * @Description: 获取非主线程Handler
     * @date 2014-10-30 上午11:30:23
     */
    public Handler getOtherThreadHandler() {
        return mOtherThreadHandler;
    }

    /**
     * @param @param  mainThread
     * @param @return 参数
     * @return Message 返回类型
     * @Title: obtainHandlerMessage
     * @Description: 获取Message
     * @date 2014-10-30 上午11:49:14
     */
    public Message obtainHandlerMessage(boolean mainThread) {
        if (mainThread) {
            return mMainThreadHandler.obtainMessage();
        } else {
            return mOtherThreadHandler.obtainMessage();
        }
    }

    /**
     * @param @param  mainThread
     * @param @param  delay
     * @param @param  what
     * @param @return 参数
     * @return Message 返回类型
     * @Title: sendMessage
     * @Description: 发送消息
     * @date 2014-10-30 上午11:54:34
     */
    public void sendMessage(boolean mainThread, int delay, int what) {
        if (mainThread) {
            mMainThreadHandler.sendMessageDelayed(mMainThreadHandler.obtainMessage(what), delay);
        } else {
            mOtherThreadHandler.sendMessageDelayed(mOtherThreadHandler.obtainMessage(what), delay);
        }
    }

    /**
     * @param @param  mainThread
     * @param @param  delay
     * @param @param  what
     * @param @param  obj
     * @param @return 参数
     * @return Message 返回类型
     * @Title: sendMessage
     * @Description: 发送消息
     * @date 2014-10-30 上午11:54:48
     */
    public void sendMessage(boolean mainThread, int delay, int what, Object obj) {
        if (mainThread) {
            mMainThreadHandler.sendMessageDelayed(mMainThreadHandler.obtainMessage(what, obj), delay);
        } else {
            mOtherThreadHandler.sendMessageDelayed(mOtherThreadHandler.obtainMessage(what, obj), delay);
        }
    }

    /**
     * @param @param  mainThread
     * @param @param  delay
     * @param @param  what
     * @param @param  arg1
     * @param @param  arg2
     * @param @return 参数
     * @return Message 返回类型
     * @Title: sendMessage
     * @Description: 发送消息
     * @date 2014-10-30 上午11:55:00
     */
    public void sendMessage(boolean mainThread, int delay, int what, int arg1, int arg2) {
        if (mainThread) {
            mMainThreadHandler.sendMessageDelayed(mMainThreadHandler.obtainMessage(what, arg1, arg2), delay);
        } else {
            mOtherThreadHandler.sendMessageDelayed(mOtherThreadHandler.obtainMessage(what, arg1, arg2), delay);
        }
    }

    /**
     * @param @param  mainThread
     * @param @param  delay
     * @param @param  what
     * @param @param  arg1
     * @param @param  arg2
     * @param @param  obj
     * @param @return 参数
     * @return Message 返回类型
     * @Title: sendMessage
     * @Description: 发送消息
     * @date 2014-10-30 上午11:55:12
     */
    public void sendMessage(boolean mainThread, int delay, int what, int arg1, int arg2, Object obj) {
        if (mainThread) {
            mMainThreadHandler.sendMessageDelayed(mMainThreadHandler.obtainMessage(what, arg1, arg2, obj), delay);
        } else {
            mOtherThreadHandler.sendMessageDelayed(mOtherThreadHandler.obtainMessage(what, arg1, arg2, obj), delay);
        }
    }

    /**
     * @param @param what 参数
     * @return void 返回类型
     * @Title: removeMessageWhat
     * @Description: 移除Message
     * @date 2014-10-31 下午4:50:22
     */
    public void removeMessageWhat(int what) {
        mMainThreadHandler.removeMessages(what);
        if (mOtherThreadHandler != null) {
            mOtherThreadHandler.removeMessages(what);
        }
    }

    /**
     * @param @param mainThread
     * @param @param what 参数
     * @return void 返回类型
     * @Title: removeMessageWhat
     * @Description: 移除Message
     * @date 2014-10-31 下午4:50:36
     */
    public void removeMessageWhat(boolean mainThread, int what) {
        if (mainThread) {
            mMainThreadHandler.removeMessages(what);
        } else {
            mOtherThreadHandler.removeMessages(what);
        }
    }

    /**
     * @param @param run 参数
     * @return void 返回类型
     * @Title: removeMessageRunnable
     * @Description: 移除runnable
     * @date 2014-10-31 下午4:50:50
     */
    public void removeMessageRunnable(Runnable run) {
        mMainThreadHandler.removeCallbacks(run);
        mOtherThreadHandler.removeCallbacks(run);
    }

    /**
     * @param @param mainThread
     * @param @param run 参数
     * @return void 返回类型
     * @Title: removeMessageRunnable
     * @Description: 移除runnable
     * @date 2014-10-31 下午4:52:36
     */
    public void removeMessageRunnable(boolean mainThread, Runnable run) {
        if (mainThread) {
            mMainThreadHandler.removeCallbacks(run);
        } else {
            mOtherThreadHandler.removeCallbacks(run);
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        try {
            lock.lock();
            for (int i = mListeners.size() - 1; i >= 0; i--) {
                mListeners.get(i).handleMessage(msg.getTarget() == mMainThreadHandler,msg);
            }
        } finally {
            lock.unlock();
            return true;
        }
    }

    /***
     *
     * @Title: addHandleListener
     * @Description: 添加Handler监听器
     * @param @param listener 参数
     * @return void 返回类型
     * @date 2014-10-30 上午11:30:44
     */
    public void addHandleListener(HandlerListener listener) {
        try {
            lock.lock();
            if (listener == null || mListeners.contains(listener)) {
                return;
            }
            mListeners.add(listener);
        } finally {
            lock.unlock();
        }
    }

    /***
     *
     * @Title: removeHandleListener
     * @Description: 移除Handler监听器
     * @param @param listener 参数
     * @return void 返回类型
     * @date 2014-10-30 上午11:31:34
     */
    public void removeHandleListener(HandlerListener listener) {
        try {
            lock.lock();
            if (listener == null || !mListeners.contains(listener)) {
                return;
            }
            mListeners.remove(listener);
        } finally {
            lock.unlock();
        }

    }

    /**
     * @param @param  msg
     * @param @return 参数
     * @return boolean 返回类型
     * @Title: isMainThreadMessage
     * @Description: 是否是主线程Message
     * @author huangyc
     * @date 2014-10-30 上午11:28:27
     */
    public boolean isMainThreadMessage(Message msg) {
        return msg.getTarget() == mMainThreadHandler;
    }

    public interface HandlerListener {

        /**
         * @param @param msg
         * @param @param mainThread 是否是主线程handler
         * @return void 返回类型
         * @Title: handleMessage
         * @Description: 处理消息
         * @date 2014-10-30 上午11:33:24
         */
        public void handleMessage( boolean mainThread,Message msg);
    }
}

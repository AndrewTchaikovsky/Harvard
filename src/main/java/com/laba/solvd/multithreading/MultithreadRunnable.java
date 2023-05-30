package com.laba.solvd.multithreading;

public class MultithreadRunnable implements Runnable {
    private int runnableNumber;

    public MultithreadRunnable(int threadNumber) {
        this.runnableNumber = threadNumber;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(i + " from thread " + runnableNumber);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
        }
    }
}

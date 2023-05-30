package com.laba.solvd.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Multithreading {
    public static void main(String[] args) throws InterruptedException {

        // implementing thread and runnable
        for (int i = 0; i < 2; i++) {
            MultithreadThread thread = new MultithreadThread(i);
            thread.start();
        }

        for (int i = 0; i < 2; i++) {
            MultithreadRunnable thread = new MultithreadRunnable(i);
            Thread runnableThread = new Thread(thread);
            runnableThread.start();
        }

        // creating connection pool
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        ExecutorService threadPool = Executors.newFixedThreadPool(7);

        for (int i = 0; i < 5; i++) {
            threadPool.execute(() -> {
                try {
                    Connection connection = connectionPool.getConnection();
                    System.out.println("Got connection: " + connection);
                    Thread.sleep(1000);
                    connectionPool.releaseConnection(connection);
                    System.out.println("Released connection: " + connection);
                } catch (InterruptedException ignored) {
                }
            });
        }

        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 2; i++) {
            threadPool.execute(() -> {
                try {
                    semaphore.acquire();
                    Connection connection = connectionPool.getConnection();
                    System.out.println("Got connection: " + connection);
                    Thread.sleep(1000);
                    connectionPool.releaseConnection(connection);
                    System.out.println("Released connection: " + connection);
                } catch (InterruptedException ignored) {
                } semaphore.release();
            });
        }
        threadPool.shutdown();
    }
}

package com.laba.solvd.multithreading;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MultithreadMain {
    public static void main(String[] args) {

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
        /* to implement without CompletableFuture, replace the following line with
        threadPool.execute(() -> {
        and remove , threadPool at the end of the block
        */
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    Connection connection = connectionPool.getConnection();
                    System.out.println("Got connection: " + connection);
                    Thread.sleep(1000);
                    connectionPool.releaseConnection(connection);
                    System.out.println("Released connection: " + connection);
                } catch (InterruptedException ignored) {
                }
            }, threadPool);
        }

        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 2; i++) {
        /* to implement without CompletableFuture, replace the following line with
        threadPool.execute(() -> {
        and remove , threadPool at the end of the block
        */
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    semaphore.acquire();
                    Connection connection = connectionPool.getConnection();
                    System.out.println("Got connection: " + connection);
                    Thread.sleep(1000);
                    connectionPool.releaseConnection(connection);
                    System.out.println("Released connection: " + connection);
                } catch (InterruptedException ignored) {
                } semaphore.release();
            }, threadPool);
        }
        threadPool.shutdown();

        connectionPool.printConnectionCount();
    }
}

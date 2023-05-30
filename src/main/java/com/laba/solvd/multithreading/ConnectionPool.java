package com.laba.solvd.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private final BlockingQueue<Connection> pool;
    private volatile int connectionCount;

    ConnectionPool(int poolSize) {
        pool = new LinkedBlockingQueue<>(poolSize);
        initializePool(poolSize);
    }

    private synchronized void initializePool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            pool.offer(createConnection());
        }
        connectionCount = poolSize;
    }

    public Connection getConnection() throws InterruptedException {
        return pool.take();
    }

    public void releaseConnection(Connection connection) {
        pool.offer(connection);
    }

    private Connection createConnection() {
        connectionCount++;
        return new Connection();
    }

    public int getConnectionCount() {
        return connectionCount;
    }

    public void printConnectionCount() {
        System.out.println("The total number of connections is " + connectionCount);
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.getInstance();
    }
}
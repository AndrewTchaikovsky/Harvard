package com.laba.solvd.multithreading;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private BlockingQueue<Connection> pool;

    private ConnectionPool(int poolSize) {
        pool = new LinkedBlockingQueue<>(poolSize);
        initializePool(poolSize);
    }

    private void initializePool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            pool.offer(createConnection());
        }
    }

    public Connection getConnection() throws InterruptedException {
        return pool.take();
    }

    public void releaseConnection(Connection connection) {
        pool.offer(connection);
    }

    private Connection createConnection() {
        return new Connection();
    }

    private static class ConnectionPoolHolder {
        private static final int POOL_SIZE = 5;
        private static final ConnectionPool INSTANCE = new ConnectionPool(POOL_SIZE);
    }

    public static ConnectionPool getInstance() {
        return ConnectionPoolHolder.INSTANCE;
    }
}

class Connection {
    public String toString() {
        return "Connection ID: " + Integer.toHexString(hashCode());
    }
}

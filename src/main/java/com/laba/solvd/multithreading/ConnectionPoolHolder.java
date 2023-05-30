package com.laba.solvd.multithreading;

public class ConnectionPoolHolder {
    private static final int POOL_SIZE = 5;
    private static final ConnectionPool INSTANCE = new ConnectionPool(POOL_SIZE);
    public static ConnectionPool getInstance() {
        return INSTANCE;
    }
}



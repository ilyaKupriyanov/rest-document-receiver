package com.example.threadSafe;

public class Future<V> {

    private V value;

    public V getValue() {
        return value;
    }

    public Future(V value) {
        this.value = value;
    }

}

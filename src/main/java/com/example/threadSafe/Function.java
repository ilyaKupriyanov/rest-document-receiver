package com.example.threadSafe;

public interface Function<K,V> {
    V getResult(K param);
}

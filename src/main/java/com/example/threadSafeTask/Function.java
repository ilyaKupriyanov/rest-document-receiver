package com.example.threadSafeTask;

public interface Function<K,V> {
    V getResult(K param);
}

package com.example.restdocumentreciever.threadSafe;

import java.util.HashMap;
import java.util.Map;

public class ThreadSafeClass<K,V> {

    private volatile Map<K,V> map;

    public ThreadSafeClass() {
        map = new HashMap<>();
    }

    public synchronized Future<V> compute(K k, Function<K, V> f){
        V v;
        if (map.containsKey(k)){
          v = map.get(k);
        } else {
            v = f.getResult(k);
            map.put(k, v);
        }

        return new Future<>(v);
    }

    public Map<K, V> getMap() {
        return map;
    }

}

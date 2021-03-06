package com.example.threadSafeTask;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeClass<K,V> {

    private ConcurrentHashMap<K,V> cache;
    private int cacheAccessCount = 0;

    public ThreadSafeClass() {
        cache = new ConcurrentHashMap<>();
    }

    public synchronized Future<V> compute(K k, Function<K, V> f){
        V v;
        if (cache.containsKey(k)){
          v = cache.get(k);
          cacheAccessCount++;
        } else {
            v = f.getResult(k);
            cache.put(k, v);
        }

        return new Future<>(v);
    }

    public Map<K, V> getCache() {
        return new HashMap<>(cache);
    }

    public int getCacheAccessCount() {
        return cacheAccessCount;
    }

}

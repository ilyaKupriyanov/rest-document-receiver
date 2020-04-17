package com.example.restdocumentreciever.threadSafe;

import java.util.ArrayList;
import java.util.List;

public interface Function<K,V> {
    V getResult(K param);
}

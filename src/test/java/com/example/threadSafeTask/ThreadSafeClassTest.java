package com.example.threadSafeTask;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class ThreadSafeClassTest {

    private Function<Integer, Double> intToDoubleFunc;
    private Function<LocalDate, Month> localDateToMonthFunc;

    @Before
    public void setUp() {
        intToDoubleFunc = new Function<Integer, Double>() {
            @Override
            public Double getResult(Integer param) {
                return param*10.0;
            }
        };
        localDateToMonthFunc = LocalDate::getMonth;
    }

    @Test
    public void testWithIntToDoubleFunc() {
        ThreadSafeClass threadSafeClass = new ThreadSafeClass();

        Future<Double> future1 = threadSafeClass.compute(10, intToDoubleFunc);
        Future<Double> future2 = threadSafeClass.compute(12, intToDoubleFunc);

        assertEquals(future1.getValue(), 100.0);
        assertEquals(future2.getValue(), 120.0);
        assertEquals(threadSafeClass.getCache().size(), 2);
        assertEquals(threadSafeClass.getCache().get(10), 100.0);
        assertEquals(threadSafeClass.getCache().get(12), 120.0);
    }

    @Test
    public void testWithLocalDateToMonthFunc() {
        ThreadSafeClass threadSafeClass = new ThreadSafeClass();

        LocalDate localDateNow = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2010, 2, 22);

        Future<Month> future1 = threadSafeClass.compute(localDateNow, localDateToMonthFunc);
        Future<Month> future2 = threadSafeClass.compute(localDate1, localDateToMonthFunc);

        assertEquals(future1.getValue(), LocalDate.now().getMonth());
        assertEquals(future2.getValue(), Month.FEBRUARY);
        assertEquals(threadSafeClass.getCache().size(), 2);
        assertEquals(threadSafeClass.getCache().get(localDateNow), LocalDate.now().getMonth());
        assertEquals(threadSafeClass.getCache().get(localDate1), Month.FEBRUARY);
    }

    @Test
    public void testBehaviorWithCache() {
        ThreadSafeClass threadSafeClass = new ThreadSafeClass();

        Future<Double> future1 = threadSafeClass.compute(10, intToDoubleFunc);
        Future<Double> future2 = threadSafeClass.compute(12, intToDoubleFunc);
        assertEquals(threadSafeClass.getCacheAccessCount(), 0);
        Future<Double> future3 = threadSafeClass.compute(10, intToDoubleFunc);
        assertEquals(threadSafeClass.getCacheAccessCount(), 1);

        LocalDate localDateNow = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2010, 2, 22);
        Future<Month> future4 = threadSafeClass.compute(localDateNow, localDateToMonthFunc);
        Future<Month> future5 = threadSafeClass.compute(localDate1, localDateToMonthFunc);
        assertEquals(threadSafeClass.getCacheAccessCount(), 1);
        Future<Month> future6 = threadSafeClass.compute(localDate1, localDateToMonthFunc);
        assertEquals(threadSafeClass.getCacheAccessCount(), 2);

        Map copy = threadSafeClass.getCache();
        assertEquals(copy.size(), threadSafeClass.getCache().size());
        copy.put(1, 1);
        assertEquals(copy.size(), threadSafeClass.getCache().size()+1);
    }

}

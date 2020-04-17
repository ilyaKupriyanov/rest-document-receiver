package com.example.restdocumentreciever.threadSafe;

import java.time.LocalDate;
import java.time.Month;

public class TestClass {

    public static void main(String[] args) {

        Function<Integer, Double> function1 = new Function<Integer, Double>() {
            @Override
            public Double getResult(Integer param) {
                return param*10.0;
            }
        };

        Function<LocalDate, Month> function2 = new Function<LocalDate, Month>() {
            @Override
            public Month getResult(LocalDate param) {
                return param.getMonth();
            }
        };

        LocalDate localDateNow = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2010, 2, 22);

        ThreadSafeClass threadSafeClass = new ThreadSafeClass();

        Future<Double> future1 = threadSafeClass.compute(10, function1);
        Future<Double> future2 = threadSafeClass.compute(12, function1);

        Future<Month> future3 = threadSafeClass.compute(localDateNow, function2);
        Future<Month> future4 = threadSafeClass.compute(localDate1, function2);

        Future<Double> future5 = threadSafeClass.compute(12, function1);
        Future<Double> future6 = threadSafeClass.compute(13, function1);

        Future<Month> future7 = threadSafeClass.compute(localDateNow, function2);
        Future<Month> future8 = threadSafeClass.compute(localDate1, function2);

    }

}

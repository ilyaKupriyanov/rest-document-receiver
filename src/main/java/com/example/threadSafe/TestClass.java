package com.example.threadSafe;

import java.time.LocalDate;
import java.time.Month;

public class TestClass {

    public static void main(String[] args) {

        Function<Integer, Double> intToDoubleFunc = new Function<Integer, Double>() {
            @Override
            public Double getResult(Integer param) {
                return param*10.0;
            }
        };

        Function<LocalDate, Month> localDateToMonthFunc = LocalDate::getMonth;

        LocalDate localDateNow = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2010, 2, 22);

        ThreadSafeClass threadSafeClass = new ThreadSafeClass();

        Future<Double> future1 = threadSafeClass.compute(10, intToDoubleFunc);
        Future<Double> future2 = threadSafeClass.compute(12, intToDoubleFunc);

        Future<Month> future3 = threadSafeClass.compute(localDateNow, localDateToMonthFunc);
        Future<Month> future4 = threadSafeClass.compute(localDate1, localDateToMonthFunc);

        Future<Double> future5 = threadSafeClass.compute(12, intToDoubleFunc);
        Future<Double> future6 = threadSafeClass.compute(13, intToDoubleFunc);

        Future<Month> future7 = threadSafeClass.compute(localDateNow, localDateToMonthFunc);
        Future<Month> future8 = threadSafeClass.compute(localDate1, localDateToMonthFunc);

    }

}

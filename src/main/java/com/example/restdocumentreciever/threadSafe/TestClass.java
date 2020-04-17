package com.example.restdocumentreciever.threadSafe;

import java.time.LocalDate;
import java.time.Month;

public class TestClass {

    public static void main(String[] args) {

        Function<Integer, Double> IntToDoubleFunc = new Function<Integer, Double>() {
            @Override
            public Double getResult(Integer param) {
                return param*10.0;
            }
        };

        Function<LocalDate, Month> LocalDateToMonthFunc = LocalDate::getMonth;

        LocalDate localDateNow = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2010, 2, 22);

        ThreadSafeClass threadSafeClass = new ThreadSafeClass();

        Future<Double> future1 = threadSafeClass.compute(10, IntToDoubleFunc);
        Future<Double> future2 = threadSafeClass.compute(12, IntToDoubleFunc);

        Future<Month> future3 = threadSafeClass.compute(localDateNow, LocalDateToMonthFunc);
        Future<Month> future4 = threadSafeClass.compute(localDate1, LocalDateToMonthFunc);

        Future<Double> future5 = threadSafeClass.compute(12, IntToDoubleFunc);
        Future<Double> future6 = threadSafeClass.compute(13, IntToDoubleFunc);

        Future<Month> future7 = threadSafeClass.compute(localDateNow, LocalDateToMonthFunc);
        Future<Month> future8 = threadSafeClass.compute(localDate1, LocalDateToMonthFunc);

    }

}

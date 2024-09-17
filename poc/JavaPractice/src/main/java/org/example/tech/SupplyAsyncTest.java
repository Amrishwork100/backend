package org.example.tech;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class SupplyAsyncTest {



    public String m2SupplyAsync() {

        CompletableFuture<String> supplyAsync1 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("First Thread name : " + Thread.currentThread().getName());
                return fetchData1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, Executors.newFixedThreadPool(3));

        CompletableFuture<String> supplyAsync2 = CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("Second Thread name : " + Thread.currentThread().getName());
                return fetchData2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },Executors.newFixedThreadPool(3));
        return supplyAsync1.join() + supplyAsync2.join();
    }

    private String fetchData1() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Thread name from method 1 : " + Thread.currentThread().getName());
        return "Java Technology ";
    }

    private String fetchData2() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Thread name from method 2 : " + Thread.currentThread().getName());
        return "Python Technology";
    }

    public static void main(String[] args) {
        SupplyAsyncTest t = new SupplyAsyncTest();
        // example of supplyAsync method of CompletableFuture .
        // It is mostly used for to call external APi or Database
        // It is produce a result for further processing
        // Task will execute concurrently without block main thread
        String finalVal = t.m2SupplyAsync();
        System.out.println(finalVal);


    }

}

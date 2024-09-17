package org.example.tech;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class RunAsyncTest {


    public Void runAsyncDemo() throws ExecutionException, InterruptedException {

        CompletableFuture<Void> runAsync1 = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("First Thread name : " + Thread.currentThread().getName());
                 firstData();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, Executors.newFixedThreadPool(3));

        CompletableFuture<Void> runAsync2 = CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Second Thread name : " + Thread.currentThread().getName());
                 secondData();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },Executors.newFixedThreadPool(3));
        System.out.println(runAsync1.get());
        System.out.println(runAsync2.get());
        return runAsync1.get();
    }

    private String firstData() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Thread name from method 1 : " + Thread.currentThread().getName());
        return "Java Technology ";
    }

    private String secondData() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("Thread name from method 2 : " + Thread.currentThread().getName());
        return "Python Technology";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        RunAsyncTest t = new RunAsyncTest();
        // example of runAsync method of CompletableFuture
        // It is used for to send notification and fire forgot the task
        // Return result is void.
        // Task will execute concurrently without block the main thread
        t.runAsyncDemo();

    }


    }

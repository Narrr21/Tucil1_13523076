package main;

public class Time {
    public long startTime;
    public long endTime;
    private boolean running;

    public void startTimer() {
        startTime = System.nanoTime();
        running = true;
        System.out.println("Timer started...");
    }

    public void stopTimer() {
        if (running) {
            endTime = System.nanoTime();
            running = false;
            System.out.println("Timer stopped.");
        } else {
            System.out.println("Timer is not running.");
        }
    }

    public void printTime() {
        if (running) {
            long elapsedTime = System.nanoTime() - startTime;
            System.out.println("Elapsed time: " + elapsedTime / 1_000_000.0 + " ms");
        } else {
            long elapsedTime = endTime - startTime;
            System.out.println("Total elapsed time: " + elapsedTime / 1_000_000.0 + " ms");
        }
    }
    
    public long getTime() {
    	long elapsedTime;
        if (running) {
            elapsedTime = System.nanoTime() - startTime;
        } else {
            elapsedTime = endTime - startTime;
        }
        return elapsedTime;
    }
    
    public void delay(int time) {
        try {
        	Thread.sleep(time);
        } catch (InterruptedException e) {
        	Thread.currentThread().interrupt();
        }
    }
}

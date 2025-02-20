package main;

public class Time {
	public boolean run;
    public long startTime;
    public long endTime;

    public void startTimer() {
        startTime = System.nanoTime();
        run = true;
        System.out.println("Timer start");
    }

    public void stopTimer() {
        if (run) {
            endTime = System.nanoTime();
            run = false;
            System.out.println("Timer stop");
        } else {
            System.out.println("Timer is not running.");
        }
    }

    public void printTime() {
    	long timeTaken;
        if (run) {
            timeTaken = System.nanoTime() - startTime;
        } else {
            timeTaken = endTime - startTime;
        }
        System.out.println("Time taken : " + timeTaken / 1_000_000.0 + " ms");
    }
    
    public double getTime() {
    	long timeTaken;
        if (run) {
            timeTaken = System.nanoTime() - startTime;
        } else {
            timeTaken = endTime - startTime;
        }
        return (timeTaken / 1_000_000.0);
    }
    
    public void delay(int time) {
        try {
        	Thread.sleep(time);
        } catch (InterruptedException e) {
        	Thread.currentThread().interrupt();
        }
    }
}

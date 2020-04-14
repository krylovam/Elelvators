package com;

import java.lang.Runnable;
import java.util.ArrayList;

public class Generator implements Runnable{
    private final ArrayList<Request> listOfRequests;
    private boolean isWork;
    private int max_floor;
    public Generator(ArrayList<Request> list, int max_floor){
        this.listOfRequests = list;
        this.isWork = true;
        this.max_floor = max_floor;
    }

    public void stop(){
        isWork = false;
    }
    @Override
    public void run(){
        while (this.isWork){
            Request request = new Request(this.max_floor);
            synchronized (listOfRequests) {
                listOfRequests.add(request);
                System.out.println("from: " + request.getFloor() + "  to: " + request.getFloorDestination() + " " + request.getDirection());
            }
            try {
                Thread.sleep(1500 + (int)(Math.random())*4000);
            } catch (InterruptedException e) {

            }
        }
    }

}

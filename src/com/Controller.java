package com;

import java.lang.Runnable;
import java.util.ArrayList;
import java.util.Iterator;
public class Controller implements Runnable{
    private final ArrayList<Request> listOfRequests;
    private ArrayList<Elevator> listOfElevators;
    boolean isWork;
    private int numOfElevators;

    public Controller(ArrayList<Request> list, int elevators){
        this.listOfRequests = list;
        this.numOfElevators = elevators;
        this.listOfElevators = new ArrayList<>();
        for (int i = 0; i < numOfElevators; i ++){
            this.listOfElevators.add(new Elevator(i + 1));
        }
        isWork = true;
    }
    public void stop(){
        isWork = false;
    }
    @Override
    public void run(){
        while (isWork) {
            Iterator<Elevator> iter = listOfElevators.iterator();
            while (iter.hasNext()) {

                Elevator elevator = iter.next();
                if (!listOfRequests.isEmpty()) {
                    synchronized (listOfRequests) {
                        if (elevator.getDirection() == Direction.WAIT) {
                            Request request = this.popRequest();
                            ArrayList<Request> requestsOnTheSameFloor = this.popFromFloor(request.getFloor(), request.getDirection());
                            requestsOnTheSameFloor.add(request);
                            elevator.setRequest(requestsOnTheSameFloor);
                        } else if (elevator.getDirection().equals(Direction.UP) || elevator.getDirection().equals(Direction.DOWN)) {
                            ArrayList<Request> requestsOnCurrentFloor = this.popFromFloor(elevator.getCurrentFloor(), elevator.getDirection());
                            Iterator<Request> iterator = requestsOnCurrentFloor.iterator();
                            while (iterator.hasNext()) {
                                elevator.addRequest(iterator.next());
                            }
                        }
                    }
                }
                elevator.move();
            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println();
        }

    }

    public synchronized Request popRequest(){
        Request request = null;
        if (!this.listOfRequests.isEmpty()){
            Iterator<Request> iterator = this.listOfRequests.iterator();
            request = iterator.next();
            iterator.remove();
        }
        return request;
    }

    public synchronized ArrayList<Request> popFromFloor(int floor, Direction direction){
        ArrayList<Request> resultList = new ArrayList<>();
        Iterator<Request> iterator = listOfRequests.iterator();
        while (iterator.hasNext()){
            Request request = iterator.next();
            if (request.getFloor() == floor && request.getDirection().equals(direction)){
                resultList.add(request);
                iterator.remove();
            }
        }
        return resultList;
    }
}

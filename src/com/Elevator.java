package com;

import java.lang.Runnable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Elevator{
    private int currentFloor;
    private Direction direction;
    private int id;
    private int floorDestination;
    private ArrayList<Request> requests;

    Elevator(int id){
        this.id = id;
        this.currentFloor = 1;
        this.direction = Direction.WAIT;
        this.floorDestination = this.currentFloor;
        this.requests = null;
    }


    public int getCurrentFloor(){
        return this.currentFloor;
    }
    public Direction getDirection(){
        return  this.direction;
    }
    public void setRequest(ArrayList<Request> requests){
        try {
            this.requests = requests;
            this.direction = Direction.GO_TO_REQUEST;
            this.floorDestination = requests.get(0).getFloor();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
    public void addRequest(Request newRequest){
        if (direction.equals(Direction.DOWN) && newRequest.getFloorDestination() < floorDestination){
            floorDestination = newRequest.getFloorDestination();
        }
        else if (direction.equals(Direction.UP) && newRequest.getFloorDestination() > floorDestination){
            floorDestination = newRequest.getFloorDestination();
        }
    }

    public void move(){
        if (!direction.equals(Direction.WAIT))
            if ((floorDestination - currentFloor) > 0)
                    currentFloor++;
            else if ((floorDestination - currentFloor) <0)
                    currentFloor--;
        if (currentFloor == floorDestination){
            if (direction.equals(Direction.GO_TO_REQUEST)){
                Iterator<Request> iterator = requests.iterator();
                int newDestination = iterator.next().getFloorDestination();
                while (iterator.hasNext()){
                    Request curr = iterator.next();
                    if (curr.getDirection().equals(Direction.UP) && (curr.getFloorDestination() > newDestination)){
                        newDestination = curr.getFloorDestination();
                    }
                    else if (curr.getDirection().equals(Direction.DOWN) && (curr.getFloorDestination() < newDestination)){
                        newDestination = curr.getFloorDestination();
                    }
                }
                floorDestination = newDestination;
                requests = null;
                direction = (floorDestination - currentFloor) > 0 ? Direction.UP : Direction.DOWN;
            }
            else {
                direction = Direction.WAIT;
            }
        }
        System.out.println("Elevator #" + id + " on " + currentFloor + " to " + floorDestination + " (" + direction+ ")");

    }
}

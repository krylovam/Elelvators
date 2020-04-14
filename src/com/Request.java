package com;

public class Request {
    private int floor;
    private Direction direction;
    private int floorDestination;
    private int max_floor = 10;
    public Request(int max_floor){
        this.max_floor = max_floor;
        this.floor = (int)(Math.random() * max_floor) + 1;
        floorDestination = floor;
        while (floor == floorDestination)
            floorDestination = (int)(Math.random() * max_floor) + 1;
        direction = (floorDestination - floor) > 0 ? Direction.UP : Direction.DOWN;


    }


    public int getFloor() {
        return  floor;
    }
    public Direction getDirection() {
        return direction;
    }

    public int getFloorDestination() {
        return floorDestination;
    }
}

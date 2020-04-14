package com;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number of floors:");
        int floors = scanner.nextInt();
        System.out.println("Enter a number of elevators");
        int elevators = scanner.nextInt();
        ArrayList<Request> list = new ArrayList<>();
        Controller controller = new Controller(list, elevators);
        Generator generator = new Generator(list, floors);
        Thread threadGenerator = new Thread(generator);
        Thread threadController = new Thread(controller);
        threadGenerator.start();
        threadController.start();
        try{
            threadController.join();
            threadGenerator.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}

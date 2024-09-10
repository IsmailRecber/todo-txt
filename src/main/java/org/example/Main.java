package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        TaskManager manager = new TaskManager(new ArrayList<>());
        Task task;
        int choice;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to your Task Manager!");
        System.out.println("--------------------2Do--------------------");

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("(1) Add new Task");
            System.out.println("(2) Remove task");
            System.out.println("(3) List Tasks");
            System.out.println("(4) Find by Id Task");
            System.out.println("(5) Find by Name Task");
            System.out.println("(6) Update Task");
            System.out.println("(7) Set completed");
            System.out.println("(0) Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter task details: id name content (separated by spaces)");
                    String taskDetails = scanner.nextLine();
                    String[] details = taskDetails.split(" ");
                    if (details.length != 3) {
                        System.out.println("Invalid input format. Please enter id, name, and content.");
                        break;
                    }
                    try {
                        int id = Integer.parseInt(details[0]);
                        String name = details[1];
                        String content = details[2];
                        task = new Task(id, name, content);
                        manager.add(task);
                        manager.writeToFile();
                        System.out.println("Writen to txt file!");
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid id format. Please enter a number.");
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    System.out.println("Enter the id of the task to remove:");
                    int removeId = scanner.nextInt();
                    manager.remove(removeId);
                    try {
                        manager.writeToFile();
                        System.out.println("Deleted from txt file!");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 3:
                    manager.listTasks(manager.getTaskList());
                    break;
                case 4:
                    System.out.println("Enter the id of the task to find:");
                    int findId = scanner.nextInt();
                    task = manager.findbyId(findId);
                    if (task != null) {
                        System.out.println(task);
                    } else {
                        System.out.println("Task with id " + findId + " not found.");
                    }
                    break;
                case 5:
                    System.out.println("Enter the name of the task to find:");
                    String findName = scanner.nextLine();
                    task = manager.findbyName(findName);
                    if (task != null) {
                        System.out.println(task);
                    } else {
                        System.out.println("Task with name " + findName + " not found.");
                    }
                    break;
                case 6:
                    System.out.println("Enter the id of the task to update:");
                    int updateId = scanner.nextInt();
                    manager.updateTask(updateId);
                    try {
                        manager.writeToFile();
                        System.out.println("Updated to txt file!");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 7:
                    System.out.println("Entry to Id for complete");
                    int id = scanner.nextInt();
                    manager.taskComplete(id);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            System.out.println("--------------------2Do--------------------");
        }
    }
}
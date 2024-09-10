package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TaskManager {
    private List<Task> taskList = new ArrayList<>();
    private Task task;

    public TaskManager(List<Task> taskList) {
        this.taskList = taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void add(Task task) {

        if (findbyId(task.getId()) != null) {
            System.out.println("Task with the same id already exists.");
            return;
        }

        taskList.add(task);
        System.out.println("Task successfully added: " + task);

    }


    public void remove(int id) {
        task = findbyId(id);
        if (task != null)
            this.taskList.remove(id);
        else
            System.out.println("Task id : " + id + " does not exist!!!");
    }

    public Task findbyId(int id) {
        for (Task task : taskList) {
            if (task.getId() == id)
                return task;
        }
        return null;
    }

    public Task findbyName(String name) {
        for (Task task : taskList) {
            if (task.getName().equals(name))
                return task;
        }
        return null;
    }

    public Task taskComplete(int id) {
        task = findbyId(id);

        if (task == null) {
            System.out.println("This id does not exist,please entry id again");
            return null;
        }

        this.task.setCompleted(true);

       return task;
    }

    public void listTasks(List<Task> taskList){
        int prefer;
        System.out.println("How list do you prefer?");
        System.out.println("(1)List by Name");
        System.out.println("(2)List by Id");
        Scanner scanner=new Scanner(System.in);
        prefer=scanner.nextInt();

        switch (prefer) {
            case 1:
                listbyName(taskList);
                break;
            case 2:
                listbyId(taskList);
                break;
            default:
                System.out.println("Invalid choice. Please choose 1 or 2.");
        }

    }

    public void listbyName(List<Task> taskList){
        List<Task> waiting=new ArrayList<>();
        waiting.addAll(taskList);
        Collections.sort(taskList,(t1, t2) -> {
            return t1.getName().compareTo(t2.getName());
        });
        for(Task wait:waiting)
            System.out.println(wait.toString());
    }

    public void listbyId(List<Task> taskList){
        for(Task task:taskList){
            System.out.println(task.toString());
        }
    }

    public Task updateTask(int id){
        task=findbyId(id);

        if(task==null){
            System.out.println("This id does not exist,please entry id again");
            return updateTask(id);
        }

        String value;
        System.out.println("Enter a new values : id name content(please use a space between values)");
        Scanner scanner=new Scanner(System.in);
        value = scanner.nextLine();
        String[] values=value.split(" ");
        if(values.length!=3){
            System.out.println("Wrong values length,please entry again");
            return  updateTask(id);
        }
        try {
            int newId = Integer.parseInt(values[0]);
            String newName = values[1];
            String newContent = values[2];

            task.setId(newId);
            task.setName(newName);
            task.setContent(newContent);

            System.out.println("Task successfully updated!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid id format, please enter again.");
            return updateTask(id);
        }



        return task;
    }


    public void writeToFile() throws IOException {

        FileWriter file=new FileWriter("C:\\Users\\isobj\\Desktop\\deneme.txt",false);
        try (PrintWriter writer = new PrintWriter(file)) {
            writer.println("--------------------2Do--------------------");
            for(Task task:taskList){

                    writer.println(task.toString());

            }
            writer.println("--------------------2Do--------------------");
        }



    }


}

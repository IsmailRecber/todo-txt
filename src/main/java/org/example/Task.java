package org.example;

public class Task{
    private int id;
    private String name;
    private String content;
    private boolean isCompleted;

    public Task(int id, String name, String content) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.isCompleted=false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
       isCompleted = completed;
      }

    @Override
    public String toString(){
        String status = isCompleted ? " ✓ " : " ";
        return "Id "+ "("+getId()+ ") " +getName()+ " ---> "+getContent()+status;
    }


}

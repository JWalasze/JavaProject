package com.project.javaproject;

public class Fork
{
    private final int id;
    private boolean isTaken;

    public Fork(int id)
    {
        this.id = id;
        isTaken = false;
    }

    public int getId()
    {
        return id;
    }

    public void changeStatus()
    {
        isTaken = !isTaken;
    }
}

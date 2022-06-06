package com.project.javaproject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Philosopher
{
    private final int id;
    private ArrayList<Fork> philosopherForks;
    private int numberOfTakenForks;

    public Philosopher(int id)
    {
        this.id = id;
        numberOfTakenForks = 0;
        philosopherForks = new ArrayList<>();
    }

    public int getId()
    {
        return id;
    }

    public int getNumberOfTakenForks()
    {
        return numberOfTakenForks;
    }

    public void addFork(Fork takenFork)
    {
        if (numberOfTakenForks == 2)
        {
            throw new RuntimeException("Nie można mieć 3 widelców!!!");
        }

        else
        {
            philosopherForks.add(takenFork);
            ++numberOfTakenForks;
        }
    }

    public void endEating()
    {
        philosopherForks.clear();
    }
}

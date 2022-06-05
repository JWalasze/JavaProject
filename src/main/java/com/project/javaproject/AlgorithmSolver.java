package com.project.javaproject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class AlgorithmSolver
{
    public enum State {EAT, THINK_1, THINK_0}
    private final HashMap<State, Color> mapColors;

    private Vector<Philosopher> philosophers;

    private final Pane root;
    public AlgorithmSolver(Pane myPane)
    {
        this.root = myPane;
        mapColors = new HashMap<>();
        mapColors.put(State.EAT, Color.GREEN);
        mapColors.put(State.THINK_1, Color.ORANGE);
        mapColors.put(State.THINK_0, Color.RED);

        for (int i = 1; i < root.getChildren().size(); ++i)
        {
            philosophers.add(new Philosopher(i));
        }
    }

    public void startPickedAlgorithm(String pickedAlgorithm)
    {
        if (pickedAlgorithm.equals("Kelner"))
        {
            this.waiterAlgorithm();
            System.out.println("Działa...");
            changeStatus(State.EAT, new Philosopher(2));
        }

        else
        {
            System.out.println("Niepoprawie wybrany algorytm!!!");
        }
    }

    public void waiterAlgorithm()
    {

    }

    public void changeStatus(State newStatus, Philosopher ph)
    {
        Circle pickedPhilosopher = (Circle) root.getChildren().get(ph.getId());
        pickedPhilosopher.setFill(mapColors.get(newStatus));
    }
}

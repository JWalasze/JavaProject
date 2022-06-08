package com.project.javaproject;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.*;

public class AlgorithmSolver
{
    private final Vector<Philosopher> philosophers;
    private final Vector<Fork> forks;

    private final StackPane root;
    private final TextArea resultsArea;

    public AlgorithmSolver(StackPane myPane, TextArea myTextArea)
    {
        this.root = myPane;
        this.resultsArea = myTextArea;

        forks = new Vector<>();
        philosophers = new Vector<>();

        int i = 0;
        for (Node node : root.getChildren())
        {
            if (node instanceof Circle && !Objects.equals(node.getId(), "circleTable"))
            {
                forks.add(new Fork(++i));
            }
        }

        for (int j = 0; j < forks.size(); ++j)
        {
            if (j == forks.size() - 1)
            {
                philosophers.add(new Philosopher(root, myTextArea, j+1, forks.get(j), forks.get(0)));
            }
            else
            {
                philosophers.add(new Philosopher(root, myTextArea, j+1, forks.get(j), forks.get(j+1)));
            }
        }
    }

    public void startPickedAlgorithm(String pickedAlgorithm)
    {
        if (pickedAlgorithm.equals("Dijkstra"))
        {
            this.dijkstraAlgorithm();
        }

        else
        {
            System.out.println("Niepoprawie wybrany algorytm!!!");
        }
    }

    public void dijkstraAlgorithm()
    {
        resultsArea.setText("Zaczynamy:\n");

        for (Philosopher philosopher : philosophers)
        {
            //philosopher.showForks();
            resultsArea.setText("Zaczynamy\n");
            Thread thread = new Thread(philosopher);
            thread.start();
        }
    }
}

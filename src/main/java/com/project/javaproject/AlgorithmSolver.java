package com.project.javaproject;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class AlgorithmSolver
{
    private final Pane root;
    public AlgorithmSolver(Pane myPane)
    {
        this.root = myPane;
    }

    public void startPickedAlgorithm(String pickedAlgorithm)
    {
        if (pickedAlgorithm.equals("Kelner"))
        {
            this.waiterAlgorithm();
            System.out.println("Działa...");
            Circle myCircle = (Circle) root.getChildren().get(1);
            myCircle.setFill(Color.GREEN);
        }

        else
        {
            System.out.println("Niepoprawie wybrany algorytm!!!");
        }
    }

    public void waiterAlgorithm()
    {

    }
}

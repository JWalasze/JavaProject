package com.project.javaproject;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.*;

public class AlgorithmSolver
{
    private final HashMap<State, Color> mapColors;

    private final Vector<Philosopher> philosophers;
    private final Vector<Fork> forks;

    private final StackPane root;
    public AlgorithmSolver(StackPane myPane)
    {
        this.root = myPane;
        mapColors = new HashMap<>();
        mapColors.put(State.EAT, Color.GREEN);
        mapColors.put(State.THINK_1, Color.ORANGE);
        mapColors.put(State.THINK_0, Color.RED);

        philosophers = new Vector<>();
        forks = new Vector<>();

        int i = 0;
        for (Node node : root.getChildren())
        {
            if (node instanceof Circle && !Objects.equals(node.getId(), "circleTable"))
            {
                philosophers.add(new Philosopher(++i));
                forks.add(new Fork(i));
            }
        }
        System.out.println(philosophers.size());
        System.out.println(forks.size());
    }

    public void startPickedAlgorithm(String pickedAlgorithm)
    {
        if (pickedAlgorithm.equals("Dijkstra"))
        {
            this.dijkstraAlgorithm();

            //0 oznacza 1 kółko, 1 oznacza 2 kółko ... 10 oznacza 11 kółko itd.
            changeStatus(State.EAT, getPickedCircle(1));
            changeStatus(State.EAT, getPickedCircle(4));
            changeStatus(State.EAT, getPickedCircle(9));
        }

        else
        {
            System.out.println("Niepoprawie wybrany algorytm!!!");
        }
    }

    public void dijkstraAlgorithm()
    {
        DijkstraAlg myDijkstra = this.new DijkstraAlg();
        myDijkstra.runDijkstraAlgorithm();
    }

    public void changeStatus(State newStatus, int ph)
    {
        assert ph >= 0 && ph < philosophers.size() : "Filozof spoza zakresu tablicy!!!";
        Circle pickedPhilosopher = this.getPhilosopherById(ph);
        assert pickedPhilosopher != null : "Błędnie wyszukany filozof!!!";
        pickedPhilosopher.setFill(mapColors.get(newStatus));
    }

    private int getPickedCircle(int id)
    {
        return philosophers.get(id-1).getId();
    }

    private Circle getPhilosopherById(int ph)
    {
        for (Node node : root.getChildren())
        {
            if (node instanceof Circle && Objects.equals(node.getId(), String.valueOf(ph)))
            {
                return (Circle) node;
            }
        }
        return null;
    }

    private class DijkstraAlg
    {
        public DijkstraAlg()
        {

        }

        public void runDijkstraAlgorithm()
        {
            System.out.println("Okej coś poszło...");
            System.out.println(AlgorithmSolver.this.philosophers.size()+6);
        }
    }
}

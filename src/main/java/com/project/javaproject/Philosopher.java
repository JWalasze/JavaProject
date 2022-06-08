package com.project.javaproject;

import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher implements Runnable
{
    private final int id;
    private final Fork firstFork;
    private final Fork secondFork;
    private boolean FLAG;
    private final StackPane root;
    private final HashMap<State, Color> mapColors;
    private final Lock lock;
    private final TextArea results;
    private final String firstDirection;
    private final String secondDirection;

    public Philosopher(StackPane root, TextArea results, int id, Fork rightFork, Fork leftFork)
    {
        this.id = id;

        if (this.id % 2 == 0)
        {
            this.firstFork = leftFork;
            this.secondFork = rightFork;
            firstDirection = "lewy";
            secondDirection = "prawy";
        }
        else
        {
            this.firstFork = rightFork;
            this.secondFork = leftFork;
            firstDirection = "prawy";
            secondDirection = "lewy";
        }

        this.FLAG = true;

        this.root = root;
        this.lock = new ReentrantLock();
        this.results = results;

        mapColors = new HashMap<>();
        mapColors.put(State.EAT, Color.GREEN);
        mapColors.put(State.THINK, Color.ORANGE);
        mapColors.put(State.HUNGARY, Color.RED);
    }

    public int getId() { return id; }

    public void changeFlag() { this.FLAG = !this.FLAG; }

    @Override
    public void run()
    {
        changeStatus(State.THINK, this.id);
        try
        {
            think();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        changeStatus(State.HUNGARY, this.id);
        while(FLAG)
        {
            synchronized (firstFork)
            {
                firstFork.changeStatus();
                System.out.println("Filozof " + this.id + " podniódł " + firstDirection + " widelec (id=" + this.firstFork.getId() + ")");

                /*lock.lock();
                results.appendText("Fil " + this.id + " :" + firstDirection + " up\n");
                lock.unlock();*/

                synchronized (secondFork)
                {
                    secondFork.changeStatus();
                    System.out.println("Filozof " + this.id + " podniódł " + secondDirection + " widelec (id=" + this.secondFork.getId() + ")");

                    /*lock.lock();
                    results.appendText("Fil " + this.id + " :" + secondDirection + " up\n");
                    lock.unlock();*/

                    changeStatus(State.EAT, this.id);
                    try
                    {
                        eat();
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    secondFork.changeStatus();
                    System.out.println("Filozof " + this.id + " odłożył " + secondDirection + " widelec (id=" + this.secondFork.getId() + ")");

                    /*lock.lock();
                    results.appendText("Fil " + this.id + " :" + secondDirection + " down\n");
                    lock.unlock();*/
                }
                firstFork.changeStatus();
                System.out.println("Filozof " + this.id + " odłożył " +  firstDirection + " widelec (id=" + this.firstFork.getId() + ")");

                /*lock.lock();
                results.appendText("Fil " + this.id + " :" + firstDirection + " down\n");
                lock.unlock();*/

                changeStatus(State.THINK, this.id);
            }
            try
            {
                think();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            changeStatus(State.HUNGARY, this.id);
        }
    }

    private void think() throws InterruptedException
    {
        int time = (int) Math.floor(2000 + Math.random()*(8000-2000+1));
        double seconds = ((double)(time) / 1000);
        Thread.sleep(time);
        System.out.println("Filozof " + id + " myśli przez " + seconds + " sekund");
    }

    private void eat() throws InterruptedException
    {
        int time = (int) Math.floor(2000 + Math.random()*(8000-2000+1));
        double seconds = ((double)(time) / 1000);
        Thread.sleep(time);
        System.out.println("Filozof " + id + " je przez " + seconds + " sekund");
    }

    public void showForks()
    {
        System.out.printf("Widelce filozofa %d: %d, %d%n", this.id, this.firstFork.getId(), this.secondFork.getId());
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

    public void changeStatus(State newStatus, int ph)
    {
        Circle pickedPhilosopher = this.getPhilosopherById(ph);
        try
        {
            pickedPhilosopher.setFill(mapColors.get(newStatus));
        }
        catch (NullPointerException e)
        {
            throw new RuntimeException("Przechwycony wyjątek: źle wybrany filozof (114 linijka)");
        }
    }
}

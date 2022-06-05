package com.project.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;



public class MainView implements Initializable
{
    @FXML
    private TextField numberOfPhilosopher;

    @FXML
    private ChoiceBox<String> chooseAlgorithm;
    private final String[] algorithms = new String[]{"Kelner"};

    @FXML
    private Label algorithm;

    @FXML
    private Label number;

    @FXML
    private Pane root;

    @FXML
    private Circle circleTable;

    private AlgorithmSolver alg;

    @FXML
    protected void onStartButton()
    {
        alg.startPickedAlgorithm(chooseAlgorithm.getValue());
    }

    @FXML
    protected void onSaveButton()
    {
        number.setText("Ilość ucztujących filozofów: " + numberOfPhilosopher.getText());

        if (root.getChildren().size() != 0)
        {
            root.getChildren().removeIf(node -> !Objects.equals(node.getId(), "circleTable"));
        }

        double tableRadius = circleTable.getRadius();
        double n = Double.parseDouble(numberOfPhilosopher.getText());
        double angle = 360/n;

        double smallRadius = 10;
        if (n > 20 && n <= 40)
        {
            smallRadius = 5;
        }

        else if(n > 40)
        {
            smallRadius = 3;
        }

        int radius = 70;
        double degree = 0;
        for (int i = 0; i < n; ++i)
        {
            double x = radius * Math.cos(Math.toRadians(degree)) + root.getWidth() / 2;
            double y = radius * Math.sin(Math.toRadians(degree)) + root.getHeight() / 2;

            Circle newPhilosopher = new Circle(x,y,smallRadius);
            root.getChildren().add(newPhilosopher);
            newPhilosopher.setFill(Color.BLUEVIOLET);

            degree += angle;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        chooseAlgorithm.getItems().addAll(algorithms);
        chooseAlgorithm.setOnAction(this::getChosenAlgorithm);
        alg = new AlgorithmSolver(root);
    }

    public void getChosenAlgorithm(ActionEvent event)
    {
        String myAlgorithm = chooseAlgorithm.getValue();
        algorithm.setText("Wybrany algorytm: " + myAlgorithm);
    }
}

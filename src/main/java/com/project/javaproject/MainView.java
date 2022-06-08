package com.project.javaproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;



public class MainView implements Initializable
{
    @FXML
    private TextField numberOfPhilosopher;

    @FXML
    private ChoiceBox<String> chooseAlgorithm;
    private final String[] algorithms = new String[]{"Dijkstra"};

    @FXML
    private Label algorithm;

    @FXML
    private Label number;

    @FXML
    private StackPane root;

    @FXML
    private TextArea output;

    @FXML
    private Circle circleTable;

    @FXML
    protected void onStartButton()
    {
        AlgorithmSolver alg = new AlgorithmSolver(root, output);
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

        output.setPrefRowCount(Integer.parseInt(numberOfPhilosopher.getText()));
        output.setPrefColumnCount(1);

        double smallRadius = 10;
        Font myFont = new Font(15);

        if (n > 20 && n <= 40)
        {
            smallRadius = 5;
            myFont = new Font(8);
        }

        else if(n > 40)
        {
            smallRadius = 3;
        }

        int radius = 70;
        double degree = 0;
        for (int i = 0; i < n; ++i)
        {
            double x = radius * Math.cos(Math.toRadians(degree));
            double y = radius * Math.sin(Math.toRadians(degree));

            Circle newPhilosopher = new Circle(smallRadius);
            newPhilosopher.setId(String.format("%d", i+1));

            Label newLabel = new Label(String.format("%d", i+1));
            newLabel.setFont(myFont);

            root.getChildren().addAll(newPhilosopher, newLabel);

            newPhilosopher.setTranslateX(x);
            newPhilosopher.setTranslateY(y);
            newPhilosopher.setFill(Color.BLUEVIOLET);

            newLabel.setTranslateX(x);
            newLabel.setTranslateY(y);
            newLabel.setTextFill(Color.WHITE);

            degree += angle;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        chooseAlgorithm.getItems().addAll(algorithms);
        chooseAlgorithm.setOnAction(this::getChosenAlgorithm);

        output.setEditable(false);
        output.setWrapText(true);
    }

    public void getChosenAlgorithm(ActionEvent event)
    {
        String myAlgorithm = chooseAlgorithm.getValue();
        algorithm.setText("Wybrany algorytm: " + myAlgorithm);
    }
}

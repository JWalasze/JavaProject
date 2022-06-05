module com.project.javaproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.project.javaproject to javafx.fxml;
    exports com.project.javaproject;
}
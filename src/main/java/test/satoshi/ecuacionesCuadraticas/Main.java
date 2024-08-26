package test.satoshi.ecuacionesCuadraticas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage primaryStage){
        primaryStage.setScene(new Scene(new Controller().getView()));
        primaryStage.setTitle("Ecuaciones Cuadráticas");
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}

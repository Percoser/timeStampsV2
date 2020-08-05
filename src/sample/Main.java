package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("homePage.fxml"));
        Parent mainPane = mainLoader.load();
        ScrollPane sp = new ScrollPane();
        sp.setContent(mainPane);
        Scene mainScene = new Scene(sp, 1200, 600);
        this.primaryStage.setScene(mainScene);

        this.primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

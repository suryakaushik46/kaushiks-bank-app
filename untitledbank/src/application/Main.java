package application;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;


public class Main extends Application {

    public static Stage primaryStage;

    private double xoffset=0;
    private double yoffset=0;

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Loginscreen.fxml"));


            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(scene);
            root.setOnMousePressed(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent e) {
                    xoffset=e.getSceneX();
                    yoffset=e.getSceneY();
                }
            });
            root.setOnMouseDragged(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent e) {
                    primaryStage.setX(e.getSceneX()-xoffset);;
                    primaryStage.setY(e.getSceneY()-yoffset);;

                }
            });


            this.primaryStage=primaryStage;
            primaryStage.show();


        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

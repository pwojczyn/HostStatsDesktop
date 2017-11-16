package hoststatsdesktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {
    private Scene scene;
    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Main.fxml"));
//        root.getStylesheets().add(getClass().getClassLoader().getResource("demo.css").toExternalForm());
//        primaryStage.setTitle("HostStats.xyz Desktop Client");
//        primaryStage.setScene(new Scene(root, 1024, 768));
//        primaryStage.show();

        // create the scene
        primaryStage.setTitle("HostStats Desktop Client");
        scene = new Scene(new Browser(),1024,768, Color.web("#666970"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
    class Browser extends Region {

        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();

        public Browser() {
            //apply the styles
            getStyleClass().add("browser");
            // load the web page
            webEngine.load("http://hoststats.xyz");
            //add the web view to the scene
            getChildren().add(browser);

        }

        private Node createSpacer() {
            Region spacer = new Region();
            HBox.setHgrow(spacer, Priority.ALWAYS);
            return spacer;
        }

        @Override
        protected void layoutChildren() {
            double w = getWidth();
            double h = getHeight();
            layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
        }

        @Override
        protected double computePrefWidth(double height) {
            return 1024;
        }

        @Override
        protected double computePrefHeight(double width) {
            return 768;
        }
    }
}

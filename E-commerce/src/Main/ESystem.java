package Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class ESystem extends Application {
    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader signupLoader = new FXMLLoader(getClass().getResource("../fxml/signup.fxml"));
        signupLoader.setRoot(new BorderPane());

        Scene scene = new Scene(signupLoader.load());
        stage.setScene(scene);
        stage.setTitle("Signup page");
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);

    }
}

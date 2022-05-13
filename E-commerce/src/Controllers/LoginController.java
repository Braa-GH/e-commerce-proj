package Controllers;

import Main.BEncryption;
import Main.DBConnection;
import Main.ESystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    Statement statement;
    ResultSet resultSet;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statement = DBConnection.statement();
    }

    @FXML
    private BorderPane root;
    @FXML
    private PasswordField passField;
    @FXML
    private Button btnLogin;
    @FXML
    private Button btnSignup;
    @FXML
    private TextField txtFieldUsername;
    @FXML
    private Label warningLabel;
    @FXML
    void actionLogin(ActionEvent event) throws SQLException {
        String username = txtFieldUsername.getText();
        String checkNameQuery = "SELECT name FROM Seller WHERE name = '" + username + "'";
        resultSet = statement.executeQuery(checkNameQuery);
        boolean complete = false;
        while (resultSet.next()){
            complete = true;
        }
        if (!complete){
            warningLabel.setText("Username is not exist!");
        }else {
            String password = passField.getText();
            String checkPassQuery = "SELECT password FROM Seller WHERE name = '" + username + "'";
            resultSet = statement.executeQuery(checkPassQuery);
            while (resultSet.next()){
                if (!BEncryption.BDecrypt(resultSet.getString("password")).equals(password)){
                    warningLabel.setText("Invalid password");
                }else {
                    warningLabel.setText("");
                    System.out.println("Success");
                }
            }

        }

    }
    @FXML
    void actionSignup(ActionEvent event) throws IOException {
        FXMLLoader signupLoader = new FXMLLoader(getClass().getResource("../fxml/signup.fxml"));
        signupLoader.setRoot(new BorderPane());
        ((Stage)root.getScene().getWindow()).setScene(new Scene(signupLoader.load()));
    }

}

package Controllers;

import Main.BEncryption;
import Main.DBConnection;
import Main.ESystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import sun.security.provider.MD5;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SignupController implements Initializable {
    Statement statement;
    ResultSet resultSet;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      statement = DBConnection.statement();
    }
    @FXML
    private PasswordField passField;
    @FXML
    private Button btnLogin;
    @FXML
    private TextField txtFieldEmail;
    @FXML
    private Button btnSignup;
    @FXML
    private TextField txtFieldUsername;
    @FXML
    private Label warningLabel;
    @FXML
    private BorderPane root;
    @FXML
    void actionSignup(ActionEvent event) throws SQLException {
        String email = txtFieldEmail.getText();
        if (!email.contains("@")){
            warningLabel.setText("Please enter a valid e-mail!");
        }else {
            String checkEmailQuery = "SELECT * FROM Seller WHERE email = '" + email + "'";
            resultSet =  this.statement.executeQuery(checkEmailQuery);
            boolean ok = true;
            while (resultSet.next()){
                warningLabel.setText("This Email is already exist!");
                ok = false;
            }
                if (ok){
                    String name = txtFieldUsername.getText();
                    String password = BEncryption.BEncrypt(passField.getText());
                    String registerQuery = "INSERT INTO Seller (name, email, password) VALUES ('" + name + "' , '" + email + "' , '" + password + "')";
                    statement.executeUpdate(registerQuery);
                    warningLabel.setText("");
                }

        }
    }
    @FXML
    void actionLogin(ActionEvent event) throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("../fxml/login.fxml"));
        loginLoader.setRoot(new BorderPane());
        ((Stage)root.getScene().getWindow()).setScene(new Scene(loginLoader.load()));
    }
}

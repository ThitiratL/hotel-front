package th.ac.ku.hotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@FxmlView("/templates/AdminLogin.fxml")

public class AdminLoginController {
    private String username = "admin", password = "123";

    @FXML
    Button loginBtn, backBtn;

    @FXML
    TextField usernameField;

    @FXML
    PasswordField passwordField;

    public void ActionOnBackBtn(ActionEvent event) throws IOException {
        backBtn = (Button) event.getSource();
        Stage stage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/Home.fxml"));
        stage.setScene(new Scene(loader.load()));
    }

    @FXML public void ActionOnLoginBtn(ActionEvent event) throws IOException {
        if (usernameField.getText().equals(username) && passwordField.getText().equals(password)) {
            loginBtn = (Button) event.getSource();
            Stage stage = (Stage)loginBtn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/AdminSystem.fxml"));
            stage.setScene(new Scene(loader.load()));
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Log in failed");
            alert.setContentText("Wrong username or password");
            alert.showAndWait();
        }
    }

}

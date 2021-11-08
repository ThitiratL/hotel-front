package th.ac.ku.hotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@FxmlView("/templates/payment.fxml")

public class PaymentController {
    @FXML
    Button backBtn, submitBtn;

    @FXML
    TextField name, phoneNumber, price, hour, minute, refCode;

    @FXML
    DatePicker date;

    public void ActionOnBackBtn(ActionEvent event) throws IOException {
        backBtn = (Button) event.getSource();
        Stage stage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/Home.fxml"));
        stage.setScene(new Scene(loader.load()));
    }

    public void ActionOnSubmitBtn(ActionEvent event) throws IOException {
        if (name.getText() == null || phoneNumber.getText() == null || price == null || date.getValue() == null || hour.getText() == null || minute.getText() == null || refCode == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Payment details");
            alert.setContentText("Please enter all the details");
            alert.showAndWait();
        }

        else {
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Success");
            alert2.setHeaderText(null);
            alert2.setContentText("Submit payment data successfully");
            alert2.showAndWait();

            submitBtn = (Button) event.getSource();
            Stage stage = (Stage) submitBtn.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/Home.fxml"));
            stage.setScene(new Scene(loader.load()));
        }


    }

}

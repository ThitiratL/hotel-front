package th.ac.ku.hotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@FxmlView("/templates/AdminSystem.fxml")

public class AdminSystemController {
    @FXML
    Button logoutBtn;

    @FXML
    ListView showBookingOrder;

    public void ActionOnLogoutBtn(ActionEvent event) throws IOException {
        logoutBtn = (Button) event.getSource();
        Stage stage = (Stage) logoutBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/Home.fxml"));
        stage.setScene(new Scene(loader.load()));
    }

}

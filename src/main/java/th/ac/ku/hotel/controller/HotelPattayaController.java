package th.ac.ku.hotel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import th.ac.ku.hotel.model.Hotel;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@Component
@FxmlView("/templates/HotelPattaya.fxml")

public class HotelPattayaController {
    @FXML
    Button backBtn, selectPtyBtn1, selectPtyBtn2, selectPtyBtn3;

    private ArrayList<Hotel> hotels;
    private LocalDate checkin;
    private LocalDate checkout;
    private int guest;

    public void setHotels(ArrayList<Hotel> hotels) {
        this.hotels = hotels;
    }

    public void setDate(LocalDate checkin, LocalDate checkout){
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public void setGuest(int guest){
        this.guest = guest;
    }

    public void ActionOnBackBtn(ActionEvent event) throws IOException {
        backBtn = (Button) event.getSource();
        Stage stage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/Home.fxml"));
        stage.setScene(new Scene(loader.load()));
    }

    public void ActionOnSelectPtyBtn1(ActionEvent event) throws IOException {
        selectPtyBtn1 = (Button) event.getSource();
        Stage stage = (Stage) selectPtyBtn1.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/HotelPattaya01.fxml"));
        stage.setScene(new Scene(loader.load()));
        HotelPattaya01Controller pattaya01Controller = loader.getController();
        pattaya01Controller.setHotel(hotels.get(0));
        pattaya01Controller.setDate(checkin,checkout);
        pattaya01Controller.setGuest(guest);
        pattaya01Controller.setHotels(hotels);
    }

    public void ActionOnSelectPtyBtn2(ActionEvent event) throws IOException {
        selectPtyBtn2 = (Button) event.getSource();
        Stage stage = (Stage) selectPtyBtn2.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/HotelPattaya02.fxml"));
        stage.setScene(new Scene(loader.load()));
        HotelPattaya02Controller pattaya02Controller = loader.getController();
        pattaya02Controller.setHotel(hotels.get(1));
        pattaya02Controller.setDate(checkin,checkout);
        pattaya02Controller.setGuest(guest);
        pattaya02Controller.setHotels(hotels);
    }

    public void ActionOnSelectPtyBtn3(ActionEvent event) throws IOException {
        selectPtyBtn3 = (Button) event.getSource();
        Stage stage = (Stage) selectPtyBtn3.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/HotelPattaya03.fxml"));
        stage.setScene(new Scene(loader.load()));
        HotelPattaya03Controller pattaya03Controller = loader.getController();
        pattaya03Controller.setHotel(hotels.get(2));
        pattaya03Controller.setDate(checkin,checkout);
        pattaya03Controller.setGuest(guest);
        pattaya03Controller.setHotels(hotels);
    }
}

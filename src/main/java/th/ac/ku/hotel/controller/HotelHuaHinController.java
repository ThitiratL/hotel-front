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
@FxmlView("/templates/HotelHuaHin.fxml")

public class HotelHuaHinController {
    @FXML
    Button backBtn, selectHuaHinBtn1, selectHuaHinBtn2, selectHuaHinBtn3;

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

    @FXML
    public void ActionOnSelectHuaHinBtn1(ActionEvent event) throws IOException {
        selectHuaHinBtn1 = (Button) event.getSource();
        Stage stage = (Stage) selectHuaHinBtn1.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/HotelHuaHin01.fxml"));
        stage.setScene(new Scene(loader.load(),900,700));
        HotelHuaHin01Controller huaHin01Controller = loader.getController();
        huaHin01Controller.setHotel(hotels.get(3));
        huaHin01Controller.setHotels(hotels);
        huaHin01Controller.setDate(checkin,checkout);
        huaHin01Controller.setGuest(guest);
    }

    @FXML
    public void ActionOnSelectHuaHinBtn2(ActionEvent event) throws IOException {
        selectHuaHinBtn2 = (Button) event.getSource();
        Stage stage = (Stage) selectHuaHinBtn2.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/HotelHuaHin02.fxml"));
        stage.setScene(new Scene(loader.load()));
        HotelHuaHin02Controller huaHin02Controller = loader.getController();
        huaHin02Controller.setHotel(hotels.get(4));
        huaHin02Controller.setHotels(hotels);
        huaHin02Controller.setDate(checkin,checkout);
        huaHin02Controller.setGuest(guest);
    }
    @FXML
    public void ActionOnSelectHuaHinBtn3(ActionEvent event) throws IOException {
        selectHuaHinBtn3 = (Button) event.getSource();
        Stage stage = (Stage) selectHuaHinBtn3.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/HotelHuaHin03.fxml"));
        stage.setScene(new Scene(loader.load()));
        HotelHuaHin03Controller huaHin03Controller = loader.getController();
        huaHin03Controller.setHotel(hotels.get(5));
        huaHin03Controller.setHotels(hotels);
        huaHin03Controller.setDate(checkin,checkout);
        huaHin03Controller.setGuest(guest);
    }

    public void ActionOnBackBtn(ActionEvent event) throws IOException {
        backBtn = (Button) event.getSource();
        Stage stage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/Home.fxml"));
        stage.setScene(new Scene(loader.load()));
    }
}

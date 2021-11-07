package th.ac.ku.hotel.controller;

import javafx.application.Platform;
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
@FxmlView("/templates/HotelHuaHin01.fxml")

public class HotelHuaHin01Controller {
    @FXML
    Button backBtn, bookHuaHinBtn1, bookHuaHinBtn2;

    private Hotel hotel;
    private ArrayList<Hotel> hotels;
    private LocalDate checkin;
    private LocalDate checkout;
    private int guest;
    private float total_price1, total_price2;
    private String roomtype_name1, roomtype_name2;
    private int guestmax1, guestmax2;

    @FXML
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                total_price1 = hotel.getRoomTypes().get(0).getPrice();
                total_price2 = hotel.getRoomTypes().get(1).getPrice();
                roomtype_name1 = hotel.getRoomTypes().get(0).getName();
                roomtype_name2 = hotel.getRoomTypes().get(1).getName();
                guestmax1 = hotel.getRoomTypes().get(0).getMaxGuest();
                guestmax2 = hotel.getRoomTypes().get(1).getMaxGuest();
            }
        });
    }

    public void calculateTotalPricePerGuest1(){
        total_price1 = total_price1 * (float) Math.ceil((float)guest/guestmax1);
    }

    public void calculateTotalPricePerGuest2(){
        total_price2 = total_price2 * (float) Math.ceil((float)guest/guestmax2);
    }

    public void calculateTotalPricePerNight1(){
        if(checkin.getYear() == checkout.getYear()){
            total_price1 = total_price1 * (checkout.getDayOfYear() - checkin.getDayOfYear());
        }else if(checkin.getYear() < checkout.getYear()){
            int lastDay_of_year = isLeap_year(checkin);
            total_price1 = total_price1 * ((lastDay_of_year - checkin.getDayOfYear()) + checkout.getDayOfYear());
        }
    }

    public void calculateTotalPricePerNight2(){
        if(checkin.getYear() == checkout.getYear()){
            total_price2 = total_price2 * (checkout.getDayOfYear() - checkin.getDayOfYear());
        }else if(checkin.getYear() < checkout.getYear()){
            int lastDay_of_year = isLeap_year(checkin);
            total_price2 = total_price2 * ((lastDay_of_year - checkin.getDayOfYear()) + checkout.getDayOfYear());
        }
    }

    public int isLeap_year(LocalDate checkin){
        if(checkin.isLeapYear()){
            return 366;
        }
        return 365;
    }

    public void ActionOnBackBtn(ActionEvent event) throws IOException {
        backBtn = (Button) event.getSource();
        Stage stage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/HotelHuaHin.fxml"));
        stage.setScene(new Scene(loader.load()));
        HotelHuaHinController huaHinController = loader.getController();
        huaHinController.setDate(checkin,checkout);
        huaHinController.setGuest(guest);
        huaHinController.setHotels(hotels);
    }


    public void ActionOnBookHuaHin(ActionEvent event) throws IOException {
        if(event.getSource() == bookHuaHinBtn1) {
            bookHuaHinBtn1 = (Button) event.getSource();
            Stage stage = (Stage) bookHuaHinBtn1.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/ConfirmBooking.fxml"));
            stage.setScene(new Scene(loader.load()));

            calculateTotalPricePerGuest1();
            calculateTotalPricePerNight1();
            ConfirmBookingController bookingController = loader.getController();
            bookingController.setter(hotel,checkin,checkout,roomtype_name1,total_price1);
            bookingController.setPrevious_page(hotel.getRoomTypes().get(0).getId());
            bookingController.setGuest(guest);
            bookingController.setHotels(hotels);

        }

        else if(event.getSource() == bookHuaHinBtn2) {
            bookHuaHinBtn2 = (Button) event.getSource();
            Stage stage = (Stage) bookHuaHinBtn2.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/ConfirmBooking.fxml"));
            stage.setScene(new Scene(loader.load()));

            calculateTotalPricePerGuest2();
            calculateTotalPricePerNight2();
            ConfirmBookingController bookingController = loader.getController();
            bookingController.setter(hotel,checkin,checkout,roomtype_name2,total_price2);
            bookingController.setPrevious_page(hotel.getRoomTypes().get(1).getId());
            bookingController.setGuest(guest);
            bookingController.setHotels(hotels);
        }
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setDate(LocalDate checkin, LocalDate checkout){
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public void setGuest(int guest){
        this.guest = guest;
    }


    public void setHotels(ArrayList<Hotel> hotels) {
        this.hotels = hotels;
    }
}

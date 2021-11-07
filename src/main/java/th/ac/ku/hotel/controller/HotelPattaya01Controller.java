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
@FxmlView("/templates/HotelPattaya01.fxml")

public class HotelPattaya01Controller {
    @FXML
    Button backBtn, bookPtyBtn1, bookPtyBtn2;

    private Hotel hotel;
    private LocalDate checkin;
    private LocalDate checkout;
    private int guest;
    private float total_price1, total_price2;
    private String roomtype_name1, roomtype_name2;
    private int guestmax1, guestmax2;
    private ArrayList<Hotel> hotels;


    @FXML
    public void initialize(){
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

    public void calculateTotalPrice1(){
        total_price1 = total_price1 * (float) Math.ceil((float)guest/guestmax1);
    }

    public void calculateTotalPrice2(){
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

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setDate(LocalDate checkin, LocalDate checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public void setGuest(int guest) {
        this.guest = guest;
    }

    public void setHotels(ArrayList<Hotel> hotels) {
        this.hotels = hotels;
    }

    public void ActionOnBackBtn(ActionEvent event) throws IOException {
        backBtn = (Button) event.getSource();
        Stage stage = (Stage) backBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/HotelPattaya.fxml"));
        stage.setScene(new Scene(loader.load()));
        HotelPattayaController pattayaController = loader.getController();
        pattayaController.setDate(checkin,checkout);
        pattayaController.setGuest(guest);
        pattayaController.setHotels(hotels);
    }


    public void ActionOnBookPtyBtn(ActionEvent event) throws IOException {
        if(event.getSource() == bookPtyBtn1) {
            bookPtyBtn1 = (Button) event.getSource();
            Stage stage = (Stage) bookPtyBtn1.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/ConfirmBooking.fxml"));
            stage.setScene(new Scene(loader.load()));

            calculateTotalPrice1();
            calculateTotalPricePerNight1();
            ConfirmBookingController bookingController = loader.getController();
            bookingController.setter(hotel,checkin,checkout,roomtype_name1,total_price1);
            bookingController.setPrevious_page(hotel.getRoomTypes().get(0).getId());
            bookingController.setHotels(hotels);
            bookingController.setGuest(guest);

        }else if(event.getSource() == bookPtyBtn2){
            bookPtyBtn2 = (Button) event.getSource();
            Stage stage = (Stage) bookPtyBtn2.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/ConfirmBooking.fxml"));
            stage.setScene(new Scene(loader.load()));

            calculateTotalPrice2();
            calculateTotalPricePerNight2();
            ConfirmBookingController bookingController = loader.getController();
            bookingController.setter(hotel,checkin,checkout,roomtype_name2,total_price2);
            bookingController.setPrevious_page(hotel.getRoomTypes().get(1).getId());
            bookingController.setHotels(hotels);
            bookingController.setGuest(guest);
        }
    }
}

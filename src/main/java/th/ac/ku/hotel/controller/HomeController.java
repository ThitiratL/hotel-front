package th.ac.ku.hotel.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import th.ac.ku.hotel.model.Hotel;
import th.ac.ku.hotel.model.RoomType;

import java.io.IOException;
import java.util.ArrayList;

@Component
@FxmlView("/templates/Home.fxml")

public class HomeController {
    @FXML
    ChoiceBox destination;

    @FXML
    DatePicker checkIn, checkOut;

    @FXML
    Spinner guest;

    @FXML
    Button searchBtn, checkBookingBtn, paymentBtn, adminBtn;

    ObservableList<String> placesList = FXCollections.observableArrayList("Pattaya", "Hua Hin");
    ArrayList<Hotel> hotels = new ArrayList<>();

    Hotel noursabah = new Hotel("H01","Noursabah Pattaya","Pattaya");
    Hotel theClassroomHotel = new Hotel("H02","The Classroom Hotel","Pattaya");
    Hotel waveHotel = new Hotel("H03","Wave Hotel Pattaya","Pattaya");
    Hotel anantara = new Hotel("H04","Anantara HuaHin Resort","Hua Hin");
    Hotel capeNidhra = new Hotel("H05","Cape Nidhra HuaHin","Hua Hin");
    Hotel theSeaCret = new Hotel("H06","The Sea-Cret HuaHin","Hua Hin");



    @FXML
    public void initialize() throws Exception {
        destination.setItems(placesList);
        guest.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 0));

        hotels.add(noursabah);
        noursabah.addRoomTypes(new RoomType("RT01","Deluxe Double Bed",20,0,6237f,2));
        noursabah.addRoomTypes(new RoomType("RT02","Deluxe Queen Twin Bed",10,0,12690f,4));

        hotels.add(theClassroomHotel);
        theClassroomHotel.addRoomTypes(new RoomType("RT03","Standard",10,0,1800f,2));
        theClassroomHotel.addRoomTypes(new RoomType("RT04","King Size",5,0,2000f,4));

        hotels.add(waveHotel);
        waveHotel.addRoomTypes(new RoomType("RT05","Basic Room",10,0,8382f,2));
        waveHotel.addRoomTypes(new RoomType("RT06","Sunset Room",7,0,9670f,5));

        hotels.add(anantara);
        anantara.addRoomTypes(new RoomType("RT07","Premium Garden View Room",20,0,2393f,3));
        anantara.addRoomTypes(new RoomType("RT08","Deluxe Garden View Room",10,0,2593f,3));

        hotels.add(capeNidhra);
        capeNidhra.addRoomTypes(new RoomType("RT09","Sky Pool Suite",15,0,6500f,2));
        capeNidhra.addRoomTypes(new RoomType("RT10","Family Pool Suite",10,0,11000f,4));

        hotels.add(theSeaCret);
        theSeaCret.addRoomTypes(new RoomType("RT11","Deluxe Double Room With Pool Access",25,0,5500,3));
        theSeaCret.addRoomTypes(new RoomType("RT12","Villa 2 Bed Rooms",15,0,5000f,2));
    }

    @FXML public boolean isEmptyCheckInAndCheckOut(){
        if (destination.getValue() == null || checkIn.getValue() == null || checkOut.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Enter all the information");
            alert.setContentText("Enter your destination and date");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    @FXML
    public boolean isWrongCheckInAndCheckOut(){
        if (destination.getValue() == null || checkIn.getValue() == null || checkOut.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Enter all the information");
            alert.setContentText("Enter your destination and date");
            alert.showAndWait();
            return false;
        }

        else if (checkIn.getValue().getYear() > checkOut.getValue().getYear()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Date");
            alert.setContentText("check your date again");
            alert.showAndWait();
            return false;
        }

        else if (checkIn.getValue().getYear() == checkOut.getValue().getYear()) {
            if (checkIn.getValue().getMonth().getValue() > checkOut.getValue().getMonth().getValue()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Date");
                alert.setContentText("check your date again");
                alert.showAndWait();
                return false;
            }
            else if (checkIn.getValue().getMonth().getValue() == checkOut.getValue().getMonth().getValue()) {
                if (checkIn.getValue().getDayOfMonth() >= checkOut.getValue().getDayOfMonth()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Invalid Date");
                    alert.setContentText("check your date again");
                    alert.showAndWait();
                    return false;
                }
            }
        }

        //กรณีที่จองโรงแรมมากกว่า 45 วัน
        if (checkIn.getValue().getYear() < checkOut.getValue().getYear()) { //ข้ามปี
            int countDay_checkIn = countDateInYear();

            if((countDay_checkIn + checkOut.getValue().getDayOfYear()) > 45){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Date");
                alert.setContentText("Booking more than 45 days, check your date again");
                alert.showAndWait();
                System.out.println(countDay_checkIn +" + "+checkOut.getValue().getDayOfYear());
                System.out.println(countDay_checkIn+checkOut.getValue().getDayOfYear());
                return false;
            }
        }

        else if (checkIn.getValue().getYear() == checkOut.getValue().getYear()) { //ปีเดียวกัน
            if ((checkOut.getValue().getDayOfYear() - checkIn.getValue().getDayOfYear()) > 45) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Date");
                alert.setContentText("Booking more than 45 days, check your date again");
                alert.showAndWait();
                System.out.println(checkOut.getValue().getDayOfYear() +" - "+checkIn.getValue().getDayOfYear());
                System.out.println(checkOut.getValue().getDayOfYear()-checkIn.getValue().getDayOfYear());
                return false;
            }
        }
        return true;
    }

    private int countDateInYear() {
        if (checkIn.getValue().isLeapYear()) {
            return  366 - checkIn.getValue().getDayOfYear();
        }
        return  365 - checkIn.getValue().getDayOfYear();
    }

    public void ActionOnSearchBtn(javafx.event.ActionEvent event) throws IOException {
        if((isEmptyCheckInAndCheckOut() && isWrongCheckInAndCheckOut())) {
            searchBtn = (Button) event.getSource();
            Stage stage = (Stage) searchBtn.getScene().getWindow();
            if (destination.getValue().equals("Hua Hin")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/HotelHuaHin.fxml"));
                stage.setScene(new Scene(loader.load()));
                HotelHuaHinController huaHinController = loader.getController();
                huaHinController.setHotels(hotels);
                huaHinController.setDate(checkIn.getValue(),checkOut.getValue());
                huaHinController.setGuest((int) guest.getValue());

            } else if (destination.getValue().equals("Pattaya")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/HotelPattaya.fxml"));
                stage.setScene(new Scene(loader.load()));
                HotelPattayaController pattayaController = loader.getController();
                pattayaController.setHotels(hotels);
                pattayaController.setDate(checkIn.getValue(),checkOut.getValue());
                pattayaController.setGuest((int) guest.getValue());
            }
        }
    }

    public void ActionOnCheckBookingBtn(ActionEvent event) throws IOException {
        checkBookingBtn = (Button) event.getSource();
        Stage stage = (Stage) checkBookingBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/checkBooking.fxml"));
        stage.setScene(new Scene(loader.load()));
    }

    @FXML public void ActionAdminBtn(ActionEvent event) throws IOException {
        adminBtn = (Button) event.getSource();
        Stage stage = (Stage) adminBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/AdminLogin.fxml"));
        stage.setScene(new Scene(loader.load()));
    }

    @FXML public void ActionOnPaymentBtn(ActionEvent event)throws IOException{
        paymentBtn = (Button) event.getSource();
        Stage stage = (Stage) paymentBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/templates/Payment.fxml"));
        stage.setScene(new Scene(loader.load()));
    }
}

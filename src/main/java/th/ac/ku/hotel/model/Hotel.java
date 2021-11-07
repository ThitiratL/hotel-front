package th.ac.ku.hotel.model;

import java.util.ArrayList;

public class Hotel {
    private String id;
    private String hotelName;
    private ArrayList<RoomType> roomTypes;
    private String provinceHotel;

    public Hotel(String id, String hotelName, String provinceHotel) {
        this.id = id;
        this.hotelName = hotelName;
        this.roomTypes = new ArrayList<>();
        this.provinceHotel = provinceHotel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public ArrayList<RoomType> getRoomTypes() {
        return roomTypes;
    }

    public void addRoomTypes(RoomType roomType){
        roomTypes.add(roomType);
    }

    public String getProvinceHotel() {
        return provinceHotel;
    }

    public void setProvinceHotel(String provinceHotel) {
        this.provinceHotel = provinceHotel;
    }
}

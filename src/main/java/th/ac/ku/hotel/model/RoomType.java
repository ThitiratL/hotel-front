package th.ac.ku.hotel.model;

public class RoomType {
    private String id;
    private String name;
    private int totalRoom;
    private int bookedRoom;
    private float price;
    private int maxGuest;

    public RoomType(String id, String name, int totalRoom, int bookedRoom, float price, int maxGuest) {
        this.id = id;
        this.name = name;
        this.totalRoom = totalRoom;
        this.bookedRoom = bookedRoom;
        this.price = price;
        this.maxGuest = maxGuest;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalRoom() {
        return totalRoom;
    }

    public void setTotalRoom(int totalRoom) {
        this.totalRoom = totalRoom;
    }

    public int getBookedRoom() {
        return bookedRoom;
    }

    public void setBookedRoom(int bookedRoom) {
        this.bookedRoom = bookedRoom;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMaxGuest() {
        return maxGuest;
    }

    public void setMaxGuest(int maxGuest) {
        this.maxGuest = maxGuest;
    }
}

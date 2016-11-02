package homestra.com.homestrav2.model;

/**
 * Created by ovicko on 28/10/2016.
 */

public class Room {
    private String room_title, RoomImagesUrl,roomEstate,roomAddress,contact_phone,room_desc,bedRooms,datePosted;
    private int room_cost,roomAvailable,roomId;



    public Room() {
    }

    public Room(
        String name, String RoomImagesUrl,String datePosted,
        String roomEstate,String roomAddress,String bedRooms,
        int roomAvailable , int room_cost,int roomId, String room_desc,
        String contact_phone)
            {
            this.room_title = name;
            this.RoomImagesUrl = RoomImagesUrl;
            this.roomId = roomId;
            this.room_cost = room_cost;
            this.room_desc = room_desc;
            this.contact_phone = contact_phone;
                this.bedRooms = bedRooms; this.roomAddress = roomAddress;
                this.roomEstate = roomEstate;
                this.roomAvailable = roomAvailable;
                this.datePosted = datePosted;
            }

    public String getTitle() {
        return room_title;
    }
    public String getDatePosted() {
        return datePosted;
    }
    public void setTitle(String name) {
        this.room_title = name;
    }
    public void setDatePosted(String date_posted) {
        this.datePosted= date_posted;
    }
    public String getBedRooms() {
        return bedRooms;
    }
    public void setBedRooms(String bed_rooms) {
        this.bedRooms = bed_rooms;
    }

    public String getRoomImagesUrl() {
        return RoomImagesUrl;
    }


    public void setRoomImagesUrl(String RoomImagesUrl) {
        this.RoomImagesUrl = RoomImagesUrl;
    }

    public int getCost() {
        return room_cost;
    }



    public void setCost(int room_cost) {
        this.room_cost = room_cost;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int room_id) {
        this.roomId = room_id;
    }

    public String getDescription() {
        return room_desc;
    }

    public void setDescription(String room_desc) {
        this.room_desc = room_desc;
    }

    public String getPhone() {
        return contact_phone;
    }

    public void setPhone(String contact_phone) {
        this.contact_phone = contact_phone;
    }

}
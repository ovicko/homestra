package homestra.com.homestrav2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import homestra.com.homestrav2.app.AppController;

/**
 * Created by ovicko on 30/10/2016.
 */

public class SingleRoomView extends AppCompatActivity{
    TextView roomIdUrl,datePosted,theRoomTitle,theRoomDesc;
    TextView roomCost;
    TextView contactPhone;
    TextView bedRooms;
    TextView roomId;
    int position;


    @Override
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleroomview);

        //locate the TextViews in singleroomview.xml
        theRoomTitle = (TextView)findViewById(R.id.room_title);
        theRoomDesc = (TextView)findViewById(R.id.theRoomDesc);
        contactPhone= (TextView) findViewById(R.id.contact_phone);
        roomCost= (TextView) findViewById(R.id.room_cost);
        NetworkImageView theImage = (NetworkImageView) findViewById(R.id.room_images);
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        bedRooms = (TextView) findViewById(R.id.bedRooms);
        roomId = (TextView) findViewById(R.id.roomId);
        datePosted = (TextView) findViewById(R.id.datePosted);
        roomIdUrl = (TextView) findViewById(R.id.room_url);

        Intent i = getIntent();
        position = i.getExtras().getInt("position");
        String room_id = i.getStringExtra("room_id");
        String roomImages = i.getStringExtra("room_images");
        String theurl = "http://jambohome.api.ovicko.com/advancedkeja/api/web/room1s/" +room_id;

        //load the text into the text views
        // thumbnail image
        theImage.setImageUrl(roomImages, imageLoader);
        //jsonObject request individual room
        JsonObjectRequest oneRoom = new JsonObjectRequest(Request.Method.GET, theurl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String theTitle = response.getString("room_title");
                    String theDesc = response.getString("room_desc");
                    String room_cost = "KSH " + response.getString("room_cost");
                    String date_posted = response.getString("date_posted");
                    String contact_phone = response.getString("contact_phone");
                    String bed_rooms = response.getString("bed_rooms").concat(" BedRooms");

                    theRoomTitle.setText(theTitle);
                    theRoomDesc.setText(theDesc);
                    roomCost.setText(room_cost);
                    contactPhone.setText(contact_phone);
                    bedRooms.setText(bed_rooms);
                    datePosted.setText(date_posted);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Volley","Error");
                    }
                });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(oneRoom);

    }


}

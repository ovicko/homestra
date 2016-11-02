package homestra.com.homestrav2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import homestra.com.homestrav2.model.Room;
import homestra.com.homestrav2.adapter.CustomListAdapter;
import homestra.com.homestrav2.app.AppController;

/**
 * Created by ovicko on 30/10/2016.
 */

public class SingleRoomView extends AppCompatActivity{
    TextView roomTitle,roomIdUrl,datePosted;
    TextView roomDesc;
    TextView roomCost;
    TextView contactPhone;
    NetworkImageView theImage;
    TextView bedRooms;
    TextView roomId;
    int position;


    private ProgressDialog pDialog;
    private List<Room> roomList = new ArrayList<Room>();
    private ListView listView;
    private CustomListAdapter adapter;
    private static String TAG = SingleRoomView.class.getSimpleName();
    @Override
    public  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singleroomview);
            //locate the TextViews in singleroomview.xml


            roomTitle = (TextView)  findViewById(R.id.room_title);
            roomDesc= (TextView) findViewById(R.id.room_desc);
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
        String room_cost = i.getStringExtra("room_cost");
        String room_title = i.getStringExtra("room_title");
        String room_desc = i.getStringExtra("room_desc");
        String contact_phone = i.getStringExtra("contact_phone");
        String roomImages = i.getStringExtra("room_images");
        String bed_rooms = i.getStringExtra("bed_rooms");
        String room_id = i.getStringExtra("room_id");
        String url = "http://jambohome.api.ovicko.com/advancedkeja/api/web/room1s/" +room_id;
        String date_posted = i.getStringExtra("date_posted");


        //load the text into the text views
        // thumbnail image
        theImage.setImageUrl(roomImages, imageLoader);
        roomTitle.setText(room_title);
        roomCost.setText(room_cost);
        roomDesc.setText(room_desc);
        contactPhone.setText(contact_phone);
        bedRooms.setText(bed_rooms);
        datePosted.setText(date_posted);
        roomId.setText("The room Id: " +room_id);
        roomIdUrl.setText("The Url Is: " +url);



    }


}

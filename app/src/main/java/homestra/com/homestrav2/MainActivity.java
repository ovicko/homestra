package homestra.com.homestrav2;

import homestra.com.homestrav2.adapter.CustomListAdapter;
import homestra.com.homestrav2.app.AppController;
import homestra.com.homestrav2.model.Room;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

public class MainActivity extends AppCompatActivity {

    // Log tag
    private static final String TAG = MainActivity.class.getSimpleName();

    // Movies json url
    private static final String url = "http://jambohome.api.ovicko.com/advancedkeja/api/web/room1s";
    private ProgressDialog pDialog;
    private List<Room> roomList = new ArrayList<Room>();
    private ListView listView;
    private CustomListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        adapter = new CustomListAdapter(this, roomList);
        listView.setAdapter(adapter);



        pDialog = new ProgressDialog(this);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();
        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Room room= new Room();
                                room.setTitle(obj.getString("room_title"));
                                room.setRoomImagesUrl("http://jambohome.api.ovicko.com/advancedkeja/backend/web/" + obj.getString("room_images"));
                                room.setDescription(((String) obj.get("room_desc")));
                                room.setCost(obj.getInt("room_cost"));
                                room.setRoomId(obj.getInt("room_id"));
                                room.setPhone(((String) obj.get("contact_phone")));
                                room.setBedRooms(obj.getString("bed_rooms"));
                                room.setDatePosted(obj.getString("date_posted"));
                                // adding room to rooms array
                                roomList.add(room);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        //capture listview click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //String room_title = ((TextView) view.findViewById(R.id.room_title)).getText().toString();
                //String room_desc = ((TextView) view.findViewById(R.id.room_desc)).getText().toString();
                //String room_cost = ((TextView) view.findViewById(R.id.room_cost)).getText().toString();
                //String contact_phone = ((TextView) view.findViewById(R.id.contact_phone)).getText().toString();
                //String bedRooms = ((TextView) view.findViewById(R.id.bedRooms)).getText().toString();
                String roomId = ((TextView) view.findViewById(R.id.roomId)).getText().toString();
                //String datePosted = ((TextView) view.findViewById(R.id.datePosted)).getText().toString();

                String roomImages = roomList.get(position).getRoomImagesUrl();
                Intent i = new Intent(getApplicationContext(),SingleRoomView.class);
                //i.putExtra("room_title",room_title);
                //i.putExtra("room_desc",room_desc);
                //i.putExtra("room_cost",room_cost);
                //i.putExtra("contact_phone",contact_phone);
                i.putExtra("position",position);
                i.putExtra("room_images",roomImages);
                //i.putExtra("bed_rooms",bedRooms);
                i.putExtra("room_id",roomId);
                //i.putExtra("date_posted",datePosted);

                //open singleroom view
                startActivity(i);
            }
        });

        
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}

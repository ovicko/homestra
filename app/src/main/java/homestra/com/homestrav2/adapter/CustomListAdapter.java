package homestra.com.homestrav2.adapter;

/**
 * Created by ovicko on 28/10/2016.
 */
import homestra.com.homestrav2.R;
import homestra.com.homestrav2.app.AppController;
import homestra.com.homestrav2.model.Room;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Room> roomItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CustomListAdapter(Activity activity, List<Room> roomItems) {
        this.activity = activity;
        this.roomItems = roomItems;
    }

    @Override
    public int getCount() {
        return roomItems.size();
    }

    @Override
    public Object getItem(int location) {
        return roomItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.room_images);
        TextView room_title = (TextView) convertView.findViewById(R.id.room_title);
        TextView room_desc = (TextView) convertView.findViewById(R.id.room_desc);
        TextView contact_phone = (TextView) convertView.findViewById(R.id.contact_phone);
        TextView room_cost = (TextView) convertView.findViewById(R.id.room_cost);
        TextView bedrooms = (TextView) convertView.findViewById(R.id.bedRooms);
        TextView roomid = (TextView) convertView.findViewById(R.id.roomId);
        TextView dateposted = (TextView) convertView.findViewById(R.id.datePosted);

        // getting room data for the row
        Room m = roomItems.get(position);

        // thumbnail image
        thumbNail.setImageUrl(m.getRoomImagesUrl(), imageLoader);

        // room_title
        room_title.setText(m.getTitle());
        bedrooms.setText(m.getBedRooms());
        roomid.setText(String.valueOf(m.getRoomId()));
        // room_desc
        room_desc.setText(String.valueOf(m.getDescription()));

        // room_county
        contact_phone.setText(String.valueOf(m.getPhone()));
        // release room_cost
        room_cost.setText("KSH  " +String.valueOf(m.getCost()));
        dateposted.setText(String.valueOf(m.getDatePosted()));

        return convertView;
    }

}
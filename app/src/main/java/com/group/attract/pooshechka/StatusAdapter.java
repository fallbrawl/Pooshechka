package com.group.attract.pooshechka;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by paul on 22.04.17.
 */

public class StatusAdapter extends ArrayAdapter<Status> {

    public StatusAdapter(Activity context, ArrayList<Status> statuses) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, statuses);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link Status} object located at this position in the list
        Status currentStatus = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.list_item_date);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        dateTextView.setText(currentStatus.getDate());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView resourceTextView = (TextView) listItemView.findViewById(R.id.list_item_resource_name);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        resourceTextView.setText(currentStatus.getResourceName());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        ImageView iconView = (ImageView) listItemView.findViewById(R.id.list_item_icon);
        // Get the image resource ID from the current Status object and
        // set the image to iconView

        switch (currentStatus.getStatus()){
            case 1:
                iconView.setImageResource(R.drawable.ic_ok144);
                break;
            case 2:
                iconView.setImageResource(R.drawable.ic_undefined_144px);
                break;
            case 3:
                iconView.setImageResource(R.drawable.internal_server_error_144x144px);
                break;
        }


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}

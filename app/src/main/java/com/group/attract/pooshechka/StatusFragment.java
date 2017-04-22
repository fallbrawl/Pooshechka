package com.group.attract.pooshechka;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by paul on 22.04.17.
 */

public class StatusFragment extends Fragment {

    final String url = "http://analyzer508714679.alekseyshevchuk.od.ua/index.php?get_status_for_mobile";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.control_status, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ListView listView = (ListView) getView().findViewById(R.id.list_for_statuses);
        final ImageView imageView = (ImageView) getView().findViewById(R.id.status_image_view);

        imageView.setBackgroundResource(R.drawable.what_about_statuses_800x536px);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),
                        "Статус ресурсов",
                        Toast.LENGTH_SHORT).show();
                imageView.setVisibility(View.INVISIBLE);
                listView.setVisibility(View.VISIBLE);

                new StatusTask().execute("http://analyzer508714679.alekseyshevchuk.od.ua/index.php?get_status_for_mobile");

            }
        });
    }

    private class StatusTask extends AsyncTask<String, Void, ArrayList<Status>> {

        private String response;

        @Override
        protected ArrayList<com.group.attract.pooshechka.Status> doInBackground(String... params) {

            return Utils.fetchEarthquakeData(params[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<com.group.attract.pooshechka.Status> contains) {

//            ArrayList<com.group.attract.pooshechka.Status> statuses = new ArrayList<com.group.attract.pooshechka.Status>();
//
//                statuses.add(new com.group.attract.pooshechka.Status(contains.getDate(),contains.getResourceName(),contains.getStatus()));
//            statuses.add(new com.group.attract.pooshechka.Status("Donut", "1.6", R.mipmap.ic_launcher));
//            statuses.add(new com.group.attract.pooshechka.Status("Donut", "1.6", R.mipmap.ic_launcher));
//            statuses.add(new com.group.attract.pooshechka.Status("Donut", "1.6", R.mipmap.ic_launcher));

            // Create an {@link AndroidFlavorAdapter}, whose data source is a list of
            // {@link AndroidFlavor}s. The adapter knows how to create list item views for each item
            // in the list.
            StatusAdapter statusesAdapter = new StatusAdapter(getActivity(), contains);

            // Get a reference to the ListView, and attach the adapter to the listView.
            ListView listView = (ListView) getView().findViewById(R.id.list_for_statuses);
            listView.setAdapter(statusesAdapter);



        }
    }
}

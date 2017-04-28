package com.group.attract.pooshechka;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by paul on 22.04.17.
 */

public class StatusFragment extends Fragment {

    final String url = "http://analyzer508714679.alekseyshevchuk.od.ua/index.php?get_status_for_mobile";

    private ProgressBar mProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.control_status, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ListView listView = (ListView) getView().findViewById(R.id.list_for_statuses);
        final ImageView imageView = (ImageView) getView().findViewById(R.id.status_image_view);
        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swiperefresh);


        swipeRefreshLayout.setEnabled(false);
        imageView.setBackgroundResource(R.drawable.what_about_statuses_800x536px);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(),
                        "Статус ресурсов",
                        Toast.LENGTH_SHORT).show();
                imageView.setVisibility(View.INVISIBLE);
                listView.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setEnabled(true);


                new StatusTask().execute(url);

            }
        });
    }

    private class StatusTask extends AsyncTask<String, Void, ArrayList<Status>> {

        @Override
        protected ArrayList<com.group.attract.pooshechka.Status> doInBackground(String... params) {


            return UtilsJson.fetchStatusData(params[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<com.group.attract.pooshechka.Status> contains) {

            // Create an {@link StatusAdapter}, whose data source is a list of
            // {@link Status}s. The adapter knows how to create list item views for each item
            // in the list.

            StatusAdapter statusesAdapter = new StatusAdapter(getActivity(), contains);

            // Get a reference to the ListView, and attach the adapter to the listView.

            ListView listView = (ListView) getView().findViewById(R.id.list_for_statuses);
            listView.setAdapter(statusesAdapter);




        }
    }
}

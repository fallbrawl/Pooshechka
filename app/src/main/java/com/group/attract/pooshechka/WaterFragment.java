package com.group.attract.pooshechka;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Fragment that displays "Water" status.
 */

public class WaterFragment extends Fragment {

    public ImageView imageView;
    private final String url = "http://infoxvod.com.ua/information/remonty";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.water, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        imageView = (ImageView) getView().findViewById(R.id.water_image_view);
        imageView.setBackgroundResource(R.drawable.problemo_800x536px);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),
                        "Статус воды",
                        Toast.LENGTH_SHORT).show();

                new CheckTask().execute(url);

            }
        });
    }

    private class CheckTask extends AsyncTask<String, Void, Boolean> {

        private Elements response;


        @Override
        protected Boolean doInBackground(String... params) {

            try {
                Document doc = Jsoup.connect(params[0]).get();
                response = doc.select("body");
            } catch (IOException e) {
                e.printStackTrace();
            }



            return response.toString().toLowerCase().contains("черноморского");
        }

        @Override
        protected void onPostExecute(Boolean contains) {

            if (contains){
                imageView.setBackgroundResource(R.drawable.yes_580px);
            } else {
                imageView.setBackgroundResource(R.drawable.no_problemo_580px);
            }
        }
    }
}


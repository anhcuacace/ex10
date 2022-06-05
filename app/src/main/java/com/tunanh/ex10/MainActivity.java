package com.tunanh.ex10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private AlbumAdapter adapter;
    private List<Album> albumList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        albumList = new ArrayList<>();
        recyclerView = findViewById(R.id.rcl_view);
        adapter = new AlbumAdapter(this);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        callApi();

    }

    private void callApi() {

        AsyncTaskExample asyncTask = new AsyncTaskExample();
        asyncTask.execute("https://jsonplaceholder.typicode.com/photos");
    }


    public class AsyncTaskExample extends AsyncTask<String, Void, String> {
        ProgressDialog p;
        HttpURLConnection httpURLConnection = null;
        String result = null;

        @Override
        protected String doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                int code = httpURLConnection.getResponseCode();
                BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String inputline;
                StringBuffer response = new StringBuffer();
                while ((inputline = in.readLine()) != null) {
                    response.append(inputline);
                }
                in.close();

                result = response.toString();


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
            }

            return result;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            p = new ProgressDialog(MainActivity.this);
            p.setMessage("Please wait...");
            p.setIndeterminate(false);
            p.setCancelable(false);
            p.show();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            p.hide();
            albumList.clear();

            if (result != null) {
                try {
                    JSONArray arr = new JSONArray(result);

                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject data = arr.getJSONObject(i);

                        Album album = new Album(
                                data.getInt("id"), data.getString("url"), data.getString("title"));

                        albumList.add(album);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            adapter.setData(albumList);
        }
    }
}
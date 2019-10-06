package android.oesterle.com.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private ImageView firstImage;
    private Spinner spin;
    private GridView gridView;

    public static final String IMAGE_SIZE = "w185";
    public static final String BASE_URL = "http://image.tmdb.org/t/p/";
    public static final String POPULAR_MOVIE = "/movie/popular";
    public static final String TOP_RATED_MOVIE = "/movie/top_rated";

    public static final String BASE_URL_LISTS = "https://api.themoviedb.org/3";
    public static final String API_KEY_ADDON = "?api_key="+Constant.API_KEY+"&page=1";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();

        String apiKey = Constant.API_KEY;

        //firstImage = (ImageView) findViewById(R.id.firstImage);

        gridView = (GridView)findViewById(R.id.gridview);



        spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                loadMovieDBData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        loadMovieDBData();


}

    public void loadMovieDBData() {
        String[] list = getResources().getStringArray(R.array.sorting);
        String requestURL = "";

        if(spin.getSelectedItem().toString().equals(list[0])) {
            requestURL = BASE_URL_LISTS + POPULAR_MOVIE + API_KEY_ADDON;
        } else {
            requestURL = BASE_URL_LISTS + TOP_RATED_MOVIE + API_KEY_ADDON;
        }

        if(isNetworkAvailable()) {
            new MovieDBQuery().execute(requestURL);
        } else {
            Toast.makeText(getApplicationContext(), "No Internet available", Toast.LENGTH_LONG).show();
        }

    }


    public class MovieDBQuery extends AsyncTask<String, Void, String> {

        // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            //URL searchUrl = params[0];
            URL searchUrl = buildUrl(params[0]);
            String movieDBResults = null;
            try {

                movieDBResults = getResponseFromHttpUrl(searchUrl);


            } catch (IOException e) {
                e.printStackTrace();
            }
            return movieDBResults;
        }

        @Override
        protected void onPostExecute(String movieDBResults) {
            // COMPLETED (27) As soon as the loading is complete, hide the loading indicator
            //mLoadingIndicator.setVisibility(View.INVISIBLE);

            if (movieDBResults != null && !movieDBResults.equals("")) {
                ArrayList movieList = MovieDBJsonParser.getMovieListFromJson(movieDBResults);
                ImageAdapter booksAdapter = new ImageAdapter(getApplicationContext(), movieList);
                gridView.setAdapter(booksAdapter);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Context context = MainActivity.this;

                        Class destnationActivity = ChildActivity.class;

                        Intent startChildActivityIntent = new Intent(context, destnationActivity);

                        Movie movie = (Movie) parent.getItemAtPosition(position);
                        startChildActivityIntent.putExtra(Movie.TITLE,movie.getTitle() );
                        startChildActivityIntent.putExtra(Movie.PLOT,movie.getPlot() );
                        startChildActivityIntent.putExtra(Movie.IMG_URL, movie.getImgUrl());
                        startChildActivityIntent.putExtra(Movie.VOTE_AVERAGE,movie.getVotaAverage() );
                        startChildActivityIntent.putExtra(Movie.RELEASE_DATE,movie.getReleaseDate() );

                        startActivity(startChildActivityIntent);

                    }
                });

            }
        }
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static URL buildUrl(String requestUrl) {
        /*
        Uri builtUri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, githubSearchQuery)
                .appendQueryParameter(PARAM_SORT, sortBy)
                .build();
        */
        Uri builtUri = Uri.parse(requestUrl);

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }


}

package android.oesterle.com.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;


import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private ImageView firstImage;
    private Spinner spin;

    private static final String IMAGE_SIZE = "w185";
    private static final String BASE_URL = "http://image.tmdb.org/t/p/";
    private static final String POPULAR_MOVIE = "/movie/popular";
    private static final String TOP_RATED_MOVIE = "/movie/top_rated";

    private static final String BASE_URL_LISTS = "https://api.themoviedb.org/3";
    private static final String API_KEY_ADDON = "?api_key="+Constant.API_KEY+"&page=1";



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Context context = getApplicationContext();

        String apiKey = Constant.API_KEY;

        firstImage = (ImageView) findViewById(R.id.firstImage);

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

        Picasso.get().load("http://i.imgur.com/DvpvklR.png").into(firstImage);

}

    public void loadMovieDBData() {
        String[] list = getResources().getStringArray(R.array.sorting);
        String requestURL = "";

        if(spin.getSelectedItem().toString().equals(list[0])) {
            requestURL = BASE_URL_LISTS + POPULAR_MOVIE + API_KEY_ADDON;
        } else {
            requestURL = BASE_URL + TOP_RATED_MOVIE + API_KEY_ADDON;
        }

        new MovieDBQuery().execute(requestURL);
    }


    public class MovieDBQuery extends AsyncTask<String, Void, String> {

        // COMPLETED (26) Override onPreExecute to set the loading indicator to visible
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //mLoadingIndicator.setVisibility(View.VISIBLE);
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
        protected void onPostExecute(String githubSearchResults) {
            // COMPLETED (27) As soon as the loading is complete, hide the loading indicator
            //mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (githubSearchResults != null && !githubSearchResults.equals("")) {
                // COMPLETED (17) Call showJsonDataView if we have valid, non-null results
                //showJsonDataView();
               // mSearchResultsTextView.setText(githubSearchResults);
            } else {
                // COMPLETED (16) Call showErrorMessage if the result is null in onPostExecute
                //showErrorMessage();
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

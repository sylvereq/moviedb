package android.oesterle.com.moviedb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MovieDBJsonParser {

    String RESULTS = "results";

    public static String[] getMovieListFromJson(String movieDBJsonString) {
        String[] movieList = null;
        try {
            JSONObject movieDBJson = new JSONObject(movieDBJsonString);
            JSONArray results = movieDBJson.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject movie = results.getJSONObject(i);
                movieList[i] = movie.getString("poster_path");
            }

        } catch (JSONException e) {

        }
        return movieList;
    }
}

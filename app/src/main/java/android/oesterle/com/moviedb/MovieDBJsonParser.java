package android.oesterle.com.moviedb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class MovieDBJsonParser {

    private static String RESULTS = "results";
    private static String TITLE = "original_title";
    private static String VOTE_AVERAGE = "vote_average";
    private static String PLOT = "overview";
    private static String PATH = "poster_path";
    private static String RELEASEDATE = "release_date";

    public static ArrayList<Movie> getMovieListFromJson(String movieDBJsonString) {
        //String[] movieList = new String[100];
        ArrayList movieList = new ArrayList<Movie>();
        try {
            JSONObject movieDBJson = new JSONObject(movieDBJsonString);
            JSONArray results = movieDBJson.getJSONArray("results");
            for (int i = 0; i < results.length(); i++) {
                JSONObject movie = results.getJSONObject(i);
                Movie mov = new Movie(movie.getString(PATH),movie.getString(TITLE), movie.getString(VOTE_AVERAGE), movie.getString(PLOT), movie.getString(RELEASEDATE));
                movieList.add(mov);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return movieList;
    }
}

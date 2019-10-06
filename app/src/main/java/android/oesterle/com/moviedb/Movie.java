package android.oesterle.com.moviedb;

public class Movie {

    public static String IMG_URL = "imgUrl";
    public static String TITLE = "title";
    public static String VOTE_AVERAGE = "votaAverage";
    public static String PLOT = "plot";
    public static String RELEASE_DATE = "releaseDate";

    private String imgUrl;
    private String title;
    private String votaAverage;
    private String plot;
    private String releaseDate;

    public Movie(String imgUrl, String title, String votaAverage, String plot, String releaseDate) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.votaAverage = votaAverage;
        this.plot = plot;
        this.releaseDate = releaseDate;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVotaAverage() {
        return votaAverage;
    }

    public void setVotaAverage(String votaAverage) {
        this.votaAverage = votaAverage;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }


}

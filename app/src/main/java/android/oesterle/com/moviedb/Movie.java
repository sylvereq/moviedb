package android.oesterle.com.moviedb;

public class Movie {

    private String imgUrl;
    private String title;
    private String votaAverage;
    private String plot;

    public Movie(String imgUrl, String title, String votaAverage, String plot) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.votaAverage = votaAverage;
        this.plot = plot;
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

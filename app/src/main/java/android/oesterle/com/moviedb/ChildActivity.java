package android.oesterle.com.moviedb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ChildActivity extends AppCompatActivity {

    private TextView movieName;
    private ImageView poster;
    private TextView plot;
    private TextView voteAverage;
    private TextView releaseDate;
    private Button backButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        movieName = (TextView) findViewById(R.id.tv_title);
        poster = (ImageView) findViewById(R.id.iv_moviePoster);
        plot = (TextView) findViewById(R.id.tv_plotSynopsis);
        voteAverage = (TextView) findViewById(R.id.tv_voteAverage);
        releaseDate = (TextView) findViewById(R.id.tv_releaseDate);
        backButton = (Button) findViewById(R.id.bt_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Intent intent = getIntent();


        movieName.setText(intent.getStringExtra(Movie.TITLE));
        plot.setText(intent.getStringExtra(Movie.PLOT));
        voteAverage.setText(intent.getStringExtra(Movie.VOTE_AVERAGE));
        releaseDate.setText(intent.getStringExtra(Movie.RELEASE_DATE));

        String imgUrl = intent.getStringExtra(Movie.IMG_URL);
        Picasso.get().load(MainActivity.BASE_URL + MainActivity.IMAGE_SIZE + imgUrl).into(poster);


    }
}

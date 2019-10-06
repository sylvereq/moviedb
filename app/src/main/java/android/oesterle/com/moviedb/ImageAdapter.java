package android.oesterle.com.moviedb;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {

    private final Context mContext;
    private final ArrayList movieList;
    private static final String IMAGE_SIZE = "w500";
    private static final String BASE_URL = "http://image.tmdb.org/t/p/";

    public ImageAdapter(Context context, ArrayList imageUrls) {
        this.mContext = context;
        this.movieList = imageUrls;
    }
    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView dummyImage = new ImageView(mContext);
        Movie movie = (Movie) movieList.get(position);
        Picasso.get().load(BASE_URL + IMAGE_SIZE + movie.getImgUrl()).into(dummyImage);
        //convertView.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 200));
        dummyImage.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

        dummyImage.setScaleType(ImageView.ScaleType.FIT_CENTER);

        return dummyImage;
    }
}

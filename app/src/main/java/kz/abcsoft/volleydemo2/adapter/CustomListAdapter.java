package kz.abcsoft.volleydemo2.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import kz.abcsoft.volleydemo2.R;
import kz.abcsoft.volleydemo2.app.AppController;
import kz.abcsoft.volleydemo2.model.Movie;

public class CustomListAdapter extends BaseAdapter{

    private Activity activity ;
    private LayoutInflater inflater ;
    private List<Movie> movieItems ;

    private ImageLoader imageLoader = AppController.getInstance().getImageLoader() ;

    public CustomListAdapter(Activity activity, List<Movie> movieItems){
        this.activity = activity ;
        this.movieItems = movieItems ;
    }

    @Override
    public int getCount() {
        return movieItems.size() ;
    }

    @Override
    public Object getItem(int i) {
        return movieItems.get(i) ;
    }

    @Override
    public long getItemId(int i) {
        return i ;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        if(view == null)
            view = inflater.inflate(R.layout.list_row, viewGroup, false) ;

        if(imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader() ;

        NetworkImageView tumbNail = (NetworkImageView)view.findViewById(R.id.thumbnail) ;
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView rating = (TextView) view.findViewById(R.id.rating);
        TextView genre = (TextView) view.findViewById(R.id.genre);
        TextView year = (TextView) view.findViewById(R.id.releaseYear);

        Movie m = movieItems.get(i) ;

        tumbNail.setImageUrl(m.getThumbnailUrl(), imageLoader);
        title.setText(m.getTitle());
        rating.setText("Rating: " + String.valueOf(m.getRating()));

        String genreStr = "";
        for (String str : m.getGenre()) {
            genreStr += str + ", ";
        }
        genre.setText(genreStr);
        year.setText(String.valueOf(m.getYear()));

        return view ;
    }
}

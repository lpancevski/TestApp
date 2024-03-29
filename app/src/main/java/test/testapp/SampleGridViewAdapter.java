package test.testapp;

/**
 * Created by Darko-PC on 4/16/2016.
 */
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import test.testapp.model.Datum;
import test.testapp.model.Model;

import static android.widget.ImageView.ScaleType.CENTER_CROP;

final class SampleGridViewAdapter extends ArrayAdapter<Datum> {



    String url="https://api.imgur.com/3/";
    private Context context;
    private List<Datum> datumList;

    public SampleGridViewAdapter(Context context,int resource, List<Datum> datumList) {


        super(context,resource, datumList);
        this.context = context;
        this.datumList = datumList;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {
        SquaredImageView view = (SquaredImageView) convertView;
        if (view == null) {
            view = new SquaredImageView(context);
            view.setScaleType(CENTER_CROP);
        }


        // Get the image URL for the current position.
       // String url = getItem(position);
        Datum datum = datumList.get(position);
        // Trigger the download of the URL asynchronously into the image view.
        Picasso.with(context) //
                .load(datum.getLink()) //
                .placeholder(R.drawable.placeholder) //
                .error(R.drawable.error) //
                .fit() //
                .tag(context) //
                .into(view);

        return view;
    }
//
    @Override public int getCount() {
        return datumList.size();
    }


    @Override public long getItemId(int position) {
        return position;
    }
}

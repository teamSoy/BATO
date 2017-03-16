package net.aguel.bato;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by NielJonCarl on 8/20/2016.
 */
public class CustomListFeedAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Feed> productItems;

    public CustomListFeedAdapter(Activity activity, List<Feed> productItems) {
        this.activity = activity;
        this.productItems = productItems;
    }

    public int getCount() {
        return productItems.size();
    }

    public Object getItem(int location) {
        return productItems.get(location);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_feed, null);
        }

        TextView Productname = (TextView) convertView.findViewById(R.id.productnameTV);
        TextView ProductState = (TextView) convertView.findViewById(R.id.productState);
        //getting product data for the row
        Feed p = productItems.get(position);


        // Product name
        Productname.setText(p.getName());

       if(p.getState()==1)
       {
           ProductState.setText("ON");
       }
       else if(p.getState()==0) {

           ProductState.setText("OFF");
       }

        return convertView;
    }
}
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
public class CustomSOSListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<EmergencyNumbers> hotline;

    public CustomSOSListAdapter(Activity activity, List<EmergencyNumbers> hotline) {
        this.activity = activity;
        this.hotline = hotline;
    }

    public int getCount() {
        return hotline.size();
    }

    public Object getItem(int location) {
        return hotline.get(location);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_hotlines, null);
        }

        TextView Productname = (TextView) convertView.findViewById(R.id.productnameTV);
        TextView ProductState = (TextView) convertView.findViewById(R.id.productState);
        //getting product data for the row
        EmergencyNumbers p = hotline.get(position);


        // Product name
        Productname.setText(p.getName());
        ProductState.setText(p.getNumber()+"");

        return convertView;
    }
}
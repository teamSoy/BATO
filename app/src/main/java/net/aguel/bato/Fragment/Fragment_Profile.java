package net.aguel.bato.Fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import net.aguel.bato.App.AppController;
import net.aguel.bato.Feed.FeedItem;
import net.aguel.bato.Feed.FeedListAdapter;
import net.aguel.bato.Home;
import net.aguel.bato.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by NielJonCarl on 8/20/2016.
 */
public class Fragment_Profile extends Fragment
{
    private static final String TAG = Home.class.getSimpleName();
    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<FeedItem> feedItems;
    private String URL_FEED = "";
    //"http://api.androidhive.info/feed/feed.json";

    private String address;
    private ProgressDialog pDialog;

    View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.fragment_profile,container,false);

        listView = (ListView) myView.findViewById(R.id.listhome);

        feedItems = new ArrayList<FeedItem>();

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String username = sharedPreferences.getString("userLoggedIn", "");
        URL_FEED= "http://teasoy.x10host.com/BATO/phpFiles/view_my_timeline.php?username="+username;
        listAdapter = new FeedListAdapter(getActivity(), feedItems); //comit
        listView.setAdapter(listAdapter);
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Your Reports");
        pDialog.setCancelable(false);
        pDialog.show();
        JsonArrayRequest productReq = new JsonArrayRequest(URL_FEED,new Response.Listener<JSONArray>()
        {
            public void onResponse(JSONArray response)
            {
                pDialog.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        FeedItem item = new FeedItem();
                        item.setId(i);
                        item.setName(obj.getString("FullName"));

                        // Image might be null sometimes
                        String image = obj.isNull("report_picture1") ? null : obj
                                .getString("report_picture1");
                        item.setImge(image);
                        item.setStatus(obj.getString("descriptions"));
                        item.setProfilePic(obj.getString("profile_picture"));
                        java.sql.Timestamp ts = java.sql.Timestamp.valueOf(obj.getString("date_reported"));
                        long tsTime = ts.getTime();
                        item.setTimeStamp(tsTime+"");
                        //obj.getString("date_reported")
                        // url might be null sometimes
                        String feedLongitude = obj.isNull("longitude") ? null : obj
                                .getString("longitude");
                        String feedLatitude = obj.isNull("latitude") ? null : obj
                                .getString("latitude");
                        Double longitude = Double.parseDouble(feedLongitude);
                        Double latitude = Double.parseDouble(feedLatitude);

                        getAddress(latitude,longitude);

                        item.setUrl(address);

                        feedItems.add(item);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                listAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getActivity(), "Error: "+error, Toast.LENGTH_SHORT).show();
            }
        });

        AppController.getInstance().addToRequestQueue(productReq);
        return myView;
    }


    public void getAddress(double lat, double lng) {
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 1);
            Address obj = addresses.get(0);
            String add = obj.getAddressLine(0);
            add = add + "\n" + obj.getLocality();
            add = add + "\n" + obj.getAdminArea();
            add = add + "\n" + obj.getCountryName();
            add = add + "\n" + obj.getCountryCode();

            Log.v("IGA", "Address" + add);
            // Toast.makeText(this, "Address=>" + add,
            // Toast.LENGTH_SHORT).show();
            address = add;
            // TennisAppActivity.showDialog(add);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
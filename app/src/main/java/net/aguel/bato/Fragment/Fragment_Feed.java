package net.aguel.bato.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import net.aguel.bato.App.AppController;
import net.aguel.bato.Feed.FeedItem;
import net.aguel.bato.Feed.FeedListAdapter;
import net.aguel.bato.Home;
import net.aguel.bato.List.EmergencyNumbers;
import net.aguel.bato.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by NielJonCarl on 8/20/2016.
 */
public class Fragment_Feed extends Fragment
{
    private static final String TAG = Home.class.getSimpleName();
    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<FeedItem> feedItems;
    private String URL_FEED = "http://teasoy.x10host.com/BATO/phpFiles/viewall_reports.php?username=11";
    //"http://api.androidhive.info/feed/feed.json";

    private ProgressDialog pDialog;

    View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.fragment_feed,container,false);

        listView = (ListView) myView.findViewById(R.id.list);

        feedItems = new ArrayList<FeedItem>();

        listAdapter = new FeedListAdapter(getActivity(), feedItems); //comit
        listView.setAdapter(listAdapter);
/*
        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Bulletin");
        pDialog.setCancelable(false);
        pDialog.show();
        */
        JsonArrayRequest productReq = new JsonArrayRequest(URL_FEED,new Response.Listener<JSONArray>()
        {
            public void onResponse(JSONArray response)
            {
                Toast.makeText(getActivity(), "Response Length: "+response.length(), Toast.LENGTH_SHORT).show();

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
                        String feedUrl = obj.isNull("profile_picture") ? null : obj
                                .getString("profile_picture");
                        item.setUrl(feedUrl);

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
}
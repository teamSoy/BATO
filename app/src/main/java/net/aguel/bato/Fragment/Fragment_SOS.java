package net.aguel.bato.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import net.aguel.bato.App.AppController;
import net.aguel.bato.CustomSOSListAdapter;
import net.aguel.bato.EmergencyNumbers;
import net.aguel.bato.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NielJonCarl on 8/20/2016.
 */
public class Fragment_SOS extends Fragment
{
    private final String Name = "Name";
    private final String State = "State";



    private ProgressDialog pDialog;
    private List<EmergencyNumbers> HotlineList = new ArrayList<EmergencyNumbers>();
    private ListView listView;
    private CustomSOSListAdapter adapter;
    Button MealsBack;

    Intent in;

    View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_sos,container,false);

        TextView pageTitle = (TextView) myView.findViewById(R.id.textView1);
        pageTitle.setText("Emergency Hotlines");

        listView = (ListView) myView.findViewById(R.id.listhome);
        adapter = new CustomSOSListAdapter(getActivity(), HotlineList);
        listView.setAdapter(adapter);




        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Hotlines");
        pDialog.setCancelable(false);
        pDialog.show();
        String url  = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("Server IP Address","http://")+"/BATO/phpFiles/view_emergencyhotlines.php";
        JsonArrayRequest productReq = new JsonArrayRequest(url,new Response.Listener<JSONArray>()
        {
            public void onResponse(JSONArray response)
            {
                HotlineList.clear();
                pDialog.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        EmergencyNumbers hotlines = new EmergencyNumbers();
                        hotlines.setName(obj.getString("hotline_name"));
                        hotlines.setNumber(obj.getInt("hotline_number"));
                        HotlineList.add(hotlines);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                pDialog.dismiss();
            }
        });

        AppController.getInstance().addToRequestQueue(productReq);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String name = ((TextView) view.findViewById(R.id.productnameTV)).getText().toString();
                String state = ((TextView) view.findViewById(R.id.productState)).getText().toString();

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+state));
                startActivity(callIntent);
            }
        });


        return myView;
    }

}
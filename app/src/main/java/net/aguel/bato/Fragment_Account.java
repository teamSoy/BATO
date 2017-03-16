package net.aguel.bato;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by NielJonCarl on 8/20/2016.
 */
public class Fragment_Account extends Fragment
{
    private final String Name = "Name";
    private final String State = "State";




    Intent in;

    View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.fragment_account,container,false);

/*
        listView = (ListView) myView.findViewById(R.id.mealsLV);
        adapter = new CustomListSwitchAdapter(getActivity(), ProductList);
        listView.setAdapter(adapter);


        ProductList.clear();

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading Switches");
        pDialog.setCancelable(false);
        pDialog.show();
        String url  = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext()).getString("Server IP Address","http://")+"/DEC/query/switch.php";
        JsonArrayRequest productReq = new JsonArrayRequest(url,new Response.Listener<JSONArray>()
        {
            public void onResponse(JSONArray response)
            {
                pDialog.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);
                        Switches switches = new Switches();
                        switches.setProductName(obj.getString("switch_name"));
                        switches.setState(obj.getInt("state"));
                        ProductList.add(switches);

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

                Bundle bundle = new Bundle();
                bundle.putString(Name,name);
                bundle.putString(State,state);
                View_Switch args = new View_Switch();
                args.setArguments(bundle);

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, args).addToBackStack(null).commit();
            }
        });

*/
        return myView;
    }


}
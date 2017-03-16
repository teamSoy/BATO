package net.aguel.bato.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.aguel.bato.R;

import java.util.List;

/**
 * Created by NielJonCarl on 8/20/2016.
 */
public class Fragment_Account extends Fragment
{
    ListView listView;
    View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.fragment_account,container,false);

        listView = (ListView) myView.findViewById(R.id.listhome);
        String[] listitems = new String[] {"My Post", "Following", "Emergency Numbers"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, listitems);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int itemPosition     = position;

                String  itemValue    = (String) listView.getItemAtPosition(position);

                //Snackbar.make(getView(), "Position :"+itemPosition+"  ListItem : " +itemValue , Snackbar.LENGTH_SHORT).show();

                if(itemPosition==0) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, new Fragment_Profile()).commit();
                }
                else if(itemPosition==1) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, new Fragment_Following()).commit();
                }
                else if(itemPosition==2) {
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, new Fragment_SOS()).commit();
                }

            }

        });

        return myView;
    }


}
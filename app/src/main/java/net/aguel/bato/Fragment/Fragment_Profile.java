package net.aguel.bato.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import net.aguel.bato.R;

/**
 * Created by NielJonCarl on 8/20/2016.
 */
public class Fragment_Profile extends Fragment
{
    View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.fragment_profile,container,false);

        return myView;
    }


}
package net.aguel.bato.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.aguel.bato.R;

/**
 * Created by NielJonCarl on 8/20/2016.
 */
public class Fragment_Account extends Fragment
{
    View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.fragment_account,container,false);


        return myView;
    }


}
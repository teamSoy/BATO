package net.aguel.bato.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.aguel.bato.R;

/**
 * Created by NielJonCarl on 8/20/2016.
 */
public class Fragment_Following extends Fragment
{
    View myView;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        myView = inflater.inflate(R.layout.fragment_following,container,false);

        return myView;
    }


}
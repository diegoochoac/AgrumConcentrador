package com.concentrador.agrum.agrumconcentrador.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.concentrador.agrum.agrumconcentrador.R;
import com.concentrador.agrum.agrumconcentrador.database.DatabaseCrud;

/**
 * Created by diego on 12/09/16.
 */
public class EventosFragment extends Fragment implements OnClickListener{

    Context thiscontext;

    public EventosFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.parametros_fragment, container, false);
        inicializarComponentes(rootview);
        thiscontext = container.getContext();
        return rootview;
    }


    private void inicializarComponentes(final View view) {

    }


    @Override
    public void onClick(View view) {

        Uri uri = Uri.parse("");
        switch (view.getId()) {
            case R.id.cmpTextHacienda:
                break;
        }

    }
}

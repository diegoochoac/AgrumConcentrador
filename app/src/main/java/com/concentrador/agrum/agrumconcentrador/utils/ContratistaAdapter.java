package com.concentrador.agrum.agrumconcentrador.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.concentrador.agrum.agrumconcentrador.R;
import com.concentrador.agrum.agrumconcentrador.database.Contratista;
import com.concentrador.agrum.agrumconcentrador.database.Usuario;

import java.util.List;

/**
 * Created by diego on 2/09/16.
 */
public class ContratistaAdapter extends ArrayAdapter<Contratista> {

    private Context mContext;
    private int row;
    private List<Contratista> list;

    public ContratistaAdapter(Context context, int textViewResourceId, List<Contratista> list) {
        super(context, textViewResourceId, list);
        this.mContext = context;
        this.row = textViewResourceId;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(row, null);

            holder = new ViewHolder();
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if ((list == null) || ((position + 1) > list.size()))
            return view; // Can't extract item

        Contratista obj = list.get(position);

        holder.name = (TextView) view.findViewById(R.id.campo1);

        if (null != holder.name && null != obj && obj.getContratistaName().length() != 0) {
            holder.name.setText("Nombre: " + obj.getContratistaName());
            holder.name.setVisibility(view.VISIBLE);
        }
        return view;
    }

    public static class ViewHolder {
        public TextView name;
    }
}
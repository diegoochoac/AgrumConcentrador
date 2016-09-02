package com.concentrador.agrum.agrumconcentrador.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.concentrador.agrum.agrumconcentrador.R;
import com.concentrador.agrum.agrumconcentrador.database.Usuario;

import java.util.List;

/**
 * Created by diego on 28/08/16.
 */
public class UsuarioAdapter extends ArrayAdapter<Usuario> {

    private Context mContext;
    private int row;
    private List<Usuario> list ;

    public UsuarioAdapter(Context context, int textViewResourceId, List<Usuario> list) {
        super(context, textViewResourceId, list);
        this.mContext=context;
        this.row=textViewResourceId;
        this.list=list;
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

        Usuario obj = list.get(position);

        holder.name = (TextView)view.findViewById(R.id.campo1);
        holder.phone = (TextView)view.findViewById(R.id.campo2);
        holder.labor = (TextView)view.findViewById(R.id.campo3);
        holder.contratista = (TextView)view.findViewById(R.id.campo4);

        if(null!=holder.name && null!=obj && obj.getUsuarioName().length()!=0 ){
            holder.name.setText("Nombre: "+obj.getUsuarioName());
            holder.phone.setText("Telefono: "+obj.getPhoneNumber());
            holder.labor.setText("Labor: "+obj.getSpecialty());
            if(holder.contratista != null) {
                holder.contratista.setText("Contratista:" + obj.getContratista().getContratistaName());
                holder.contratista.setVisibility(view.VISIBLE);
            }

            holder.name.setVisibility(view.VISIBLE);
            holder.phone.setVisibility(view.VISIBLE);
            holder.labor.setVisibility(view.VISIBLE);
        }
        return view;
    }

    public static class ViewHolder {
        public TextView name;
        public TextView phone;
        public TextView labor;
        public TextView contratista;
    }


    }

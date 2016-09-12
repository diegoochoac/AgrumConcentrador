package com.concentrador.agrum.agrumconcentrador.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.concentrador.agrum.agrumconcentrador.R;
import com.concentrador.agrum.agrumconcentrador.database.Terreno;
import com.concentrador.agrum.agrumconcentrador.database.Usuario;

import java.util.List;

/**
 * Created by diego on 29/08/16.
 */
public class TerrenoAdapter extends ArrayAdapter<Terreno>{

    private Context mContext;
    private int row;
    private List<Terreno> list ;

    public TextView codigo;
    public TextView hacienda;
    public TextView suerte;
    public TextView variedad;
    public TextView zona;
    public TextView area;

    public TerrenoAdapter(Context context, int textViewResourceId, List<Terreno> list) {
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

        Terreno obj = list.get(position);

        holder.codigo = (TextView)view.findViewById(R.id.campo1);
        holder.hacienda = (TextView)view.findViewById(R.id.campo2);
        holder.suerte = (TextView)view.findViewById(R.id.campo3);
        holder.variedad = (TextView)view.findViewById(R.id.campo4);
        holder.zona = (TextView)view.findViewById(R.id.campo5);
        holder.area = (TextView)view.findViewById(R.id.campo6);


        if(null!=holder.codigo && null!=obj && obj.getCod().length()!=0){
            holder.codigo.setText("Codigo: "+obj.getCod());
            holder.hacienda.setText("Hacienda: "+obj.getHacienda());
            holder.suerte.setText("Suerte: "+obj.getSte());
            holder.variedad.setText("Variedad: "+obj.getVariedad());
            holder.zona.setText("Zona: "+obj.getZonaAdmi());
            holder.area.setText("Area: "+obj.getArea());

            //holder.codigo.setVisibility(view.VISIBLE);
            //holder.hacienda.setVisibility(view.VISIBLE);
            //holder.suerte.setVisibility(view.VISIBLE);
            //holder.variedad.setVisibility(view.VISIBLE);
            //holder.zona.setVisibility(view.VISIBLE);
            //holder.area.setVisibility(view.VISIBLE);

        }
        return view;
    }

    public static class ViewHolder {
        public TextView codigo;
        public TextView hacienda;
        public TextView suerte;
        public TextView variedad;
        public TextView zona;
        public TextView area;

    }


}

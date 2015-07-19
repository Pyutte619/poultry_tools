package com.app.dyoni.poultrytools;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by piyut on 18/07/2015.
 */
public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;
    private final String[] arrayHarga;
    TextView txtTitle;TextView txtHarga;
    //private final Integer imageId;
    public CustomList(Activity context,
                      String[] web, String[] arrayHarga) {
        super(context, R.layout.listitem, web);
        this.context = context;
        this.web = web;
        this.arrayHarga=arrayHarga;
        //this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.listitem, null, true);
         txtTitle = (TextView) rowView.findViewById(R.id.listPakan);
         txtHarga = (TextView) rowView.findViewById(R.id.listHarga);

        //ImageView imageView = (ImageView) rowView.findViewById(R.id.imgListHarga);
        txtTitle.setText(web[position]);
        txtHarga.setText("Rp. "+arrayHarga[position]);

        //imageView.setImageResource(imageId);
        return rowView;
    }

}

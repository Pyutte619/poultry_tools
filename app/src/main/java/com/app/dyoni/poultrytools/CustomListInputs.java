package com.app.dyoni.poultrytools;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.cengalabs.flatui.views.FlatEditText;

import java.util.ArrayList;

/**
 * Created by tegar dion on 21/07/2015.
 */
public class CustomListInputs extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;
    private ArrayList<String> inputPakanString = new ArrayList<>();
    //String inputPakan;
    TextView txtTitle;
    FlatEditText etInputs;
    //private final Integer imageId;
    public CustomListInputs(Activity context,
                      String[] web) {
        super(context, R.layout.item_edittext, web);
        this.context = context;
        this.web = web;

        //this.imageId = imageId;

    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.item_edittext, null, true);
        txtTitle = (TextView) rowView.findViewById(R.id.daftarPakan);
        etInputs = (FlatEditText) rowView.findViewById(R.id.inputDaftarPakan);

        //ImageView imageView = (ImageView) rowView.findViewById(R.id.imgListHarga);
        txtTitle.setText(web[position]);

        //imageView.setImageResource(imageId);
        return rowView;
    }

    String getInputPakan(int posisi){
        inputPakanString.add(etInputs.getText().toString());

        return inputPakanString.get(posisi);
    }
}

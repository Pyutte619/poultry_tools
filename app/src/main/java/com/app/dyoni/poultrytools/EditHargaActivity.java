package com.app.dyoni.poultrytools;

import android.app.ListActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.cengalabs.flatui.FlatUI;

/**
 * Created by piyut on 18/07/2015.
 */
public class EditHargaActivity extends ActionBarActivity {

    ListView listHarga;String[] listHargaString;Integer imageLH;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editharga);
        FlatUI.initDefaultValues(this);
        FlatUI.setDefaultTheme(FlatUI.DEEP);//set the theme
        getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, FlatUI.GRASS, false, 2));

        listHarga= (ListView) findViewById(R.id.listView);
        Resources res=getResources();
        listHargaString=res.getStringArray(R.array.pakanArray);
        imageLH=R.drawable.circle;
        CustomList adapter = new
                CustomList(EditHargaActivity.this, listHargaString, imageLH);
        listHarga.setAdapter(adapter);
    }
}

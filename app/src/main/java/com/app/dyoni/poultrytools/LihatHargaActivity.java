package com.app.dyoni.poultrytools;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.views.FlatButton;

import java.util.ArrayList;

/**
 * Created by piyut on 18/07/2015.
 */
public class LihatHargaActivity extends ActionBarActivity implements View.OnClickListener{

    ListView listViewHarga;String[] listPakanString;
    String[] listHargaString={"0","0","0","0","0","0","0","0","0","0"};

    ArrayList<Integer> inputHargaPakan= new ArrayList<>();
    //Dialogs dialog=new Dialogs();
    FlatButton btn_editarga,btn_kembaliHitung;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihatharga);
        FlatUI.initDefaultValues(this);
        FlatUI.setDefaultTheme(FlatUI.DEEP);//set the theme
        getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, FlatUI.GRASS, false, 2));

        Intent intent = getIntent();
        if(intent!=null) {
            inputHargaPakan = intent.getIntegerArrayListExtra("input harga pakan");
        }
        //for(int i=0;i<10;i++) {
          //  Log.d("intent harga", String.valueOf(inputHargaPakan.get(i)));
       // }
        listViewHarga = (ListView) findViewById(R.id.listView);
        btn_editarga= (FlatButton) findViewById(R.id.button_editHarga);
        btn_kembaliHitung= (FlatButton) findViewById(R.id.button_kembaliHitung);

        Resources res=getResources();
        listPakanString =res.getStringArray(R.array.pakanArray);

        if(inputHargaPakan!=null){

            for(int i=0;i<10;i++) {
                listHargaString[i]= String.valueOf(inputHargaPakan.get(i));
            }
        }



        CustomList adapter = new
                CustomList(LihatHargaActivity.this, listPakanString,listHargaString);
        listViewHarga.setAdapter(adapter);
        btn_editarga.setOnClickListener(this);
        btn_kembaliHitung.setOnClickListener(this);
       }

   // void loadData(){
     //   SharedPreferences sharedPreferences=getSharedPreferences("dataHargaPakan", Context.MODE_PRIVATE);
       // String[] daftarHarga=sharedPreferences.getStringSet("daftar harga",);
    //}

    @Override
    public void onClick(View v) {
        if(v.equals(btn_editarga)) {
            Intent intent = new Intent(this, EditHargaActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("harga pakan",listHargaString);
            startActivity(intent);
        }
    }
}




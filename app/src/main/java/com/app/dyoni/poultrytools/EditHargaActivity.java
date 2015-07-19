package com.app.dyoni.poultrytools;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.views.FlatButton;
import com.cengalabs.flatui.views.FlatEditText;

import java.util.ArrayList;

/**
 * Created by tegar dion on 18/07/2015.
 */
public class EditHargaActivity extends ActionBarActivity implements View.OnClickListener{
    FlatButton btn_simpan;
    private ArrayList<FlatEditText> flatEditTexts = new ArrayList<FlatEditText>();
    private ArrayList<String> inputHargaString = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editharga);
        FlatUI.initDefaultValues(this);
        FlatUI.setDefaultTheme(FlatUI.DEEP);//set the theme
        getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, FlatUI.GRASS, false, 2));

        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_hargapakanJadi));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_hargabekatulPadi));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_hargadedakPadi));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_hargajagungGiling));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_hargapolar));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_hargatepungIkanKls1));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_hargatepungIkanKls2));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_hargakonsentrat));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_hargabungkilKedele));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_hargaminyakSawit));
        btn_simpan= (FlatButton) findViewById(R.id.button_save);
        btn_simpan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        for(int i=0;i<10;i++) {
            if((flatEditTexts.get(i).getText().toString()).isEmpty()){
                inputHargaString.add("0");
            }else{
                inputHargaString.add(flatEditTexts.get(i).getText().toString());
            }
        }

        for(int i=0;i<10;i++) {
            Log.d("harga pakans", String.valueOf(inputHargaString.get(i)));
        }
        Intent intent=new Intent(this,LihatHargaActivity.class);
        intent.putExtra("input harga pakan",inputHargaString);
        startActivity(intent);
    }
}

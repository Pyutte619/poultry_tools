package com.app.dyoni.poultrytools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;

import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.views.FlatButton;
import com.cengalabs.flatui.views.FlatTextView;

import java.util.ArrayList;

/**
 * Created by tegar dion on 18/07/2015.
 */
public class HasilActivity extends ActionBarActivity implements View.OnClickListener{
    ArrayList<Integer> hasilProtein= new ArrayList<>();
    ArrayList<Integer> hasilEnergy= new ArrayList<>();

    FlatButton back, lihatHarga;FlatTextView sumProteinText;FlatTextView sumEnergiText;
    FlatTextView sumHargaText;
    float sumProtein;float sumEnergy;float sumHargaPakan;
    private ArrayList<FlatTextView> flatTextViws = new ArrayList<FlatTextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FlatUI.initDefaultValues(this);
        FlatUI.setDefaultTheme(FlatUI.DEEP);//set the theme
        getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, FlatUI.GRASS, false, 2));
        setContentView(R.layout.activity_hasil);

        Intent intent = getIntent();
        hasilProtein =  intent.getIntegerArrayListExtra("hasil protein");
        hasilEnergy =  intent.getIntegerArrayListExtra("hasil energy");
        sumProtein = intent.getFloatExtra("sumProtein",0);
        sumEnergy = intent.getFloatExtra("sumEnergy",0);
        sumHargaPakan= intent.getFloatExtra("jumlahHargaPakan",0);

        for(int i=0;i<10;i++) {
            Log.d("protein intent", String.valueOf(hasilProtein.get(i)));
            Log.d("energi intent", String.valueOf(hasilEnergy.get(i)));
        }

        Log.d("energi", String.valueOf(sumEnergy));

            /*flatTextViws.add((FlatTextView) findViewById(R.id.text_hasil1));
            flatTextViws.add((FlatTextView) findViewById(R.id.text_hasil2));
            flatTextViws.add((FlatTextView) findViewById(R.id.text_hasil3));
            flatTextViws.add((FlatTextView) findViewById(R.id.text_hasil4));
            flatTextViws.add((FlatTextView) findViewById(R.id.text_hasil5));
            flatTextViws.add((FlatTextView) findViewById(R.id.text_hasil6));
            flatTextViws.add((FlatTextView) findViewById(R.id.text_hasil7));
            flatTextViws.add((FlatTextView) findViewById(R.id.text_hasil8));
            flatTextViws.add((FlatTextView) findViewById(R.id.text_hasil9));
            flatTextViws.add((FlatTextView) findViewById(R.id.text_hasil10));
            */
        sumProteinText= (FlatTextView) findViewById(R.id.text_protein);

        sumEnergiText= (FlatTextView) findViewById(R.id.text_energi);
        sumHargaText= (FlatTextView) findViewById(R.id.text_harga);
        back= (FlatButton) findViewById(R.id.button_back);
        lihatHarga = (FlatButton) findViewById(R.id.button_lihatHarga);

        back.setOnClickListener(this);
        lihatHarga.setOnClickListener(this);

            //flatTextViws.get(0).setText(""+hasilProtein.get(0));
           displaytextProtein();
        }


    void displaytextProtein(){
        //for(int i=0;i<10;i++){
          //  flatTextViws.get(i).setText(" "+(hasilProtein.get(i))+" %");
       // }
        sumProteinText.setText(" "+sumProtein+" %");
        sumEnergiText.setText(" "+sumEnergy+" Kkal");
        sumHargaText.setText("Rp. "+sumHargaPakan);
    }

    @Override
    public void onClick(View v) {
        if(v.equals(back)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Intent intent = new Intent(this, LihatHargaActivity.class);
            startActivity(intent);
        }
    }
}

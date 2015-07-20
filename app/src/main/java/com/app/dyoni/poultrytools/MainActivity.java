package com.app.dyoni.poultrytools;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.views.FlatButton;
import com.cengalabs.flatui.views.FlatEditText;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{
    private ArrayList<FlatEditText> flatEditTexts = new ArrayList<FlatEditText>();
    private ArrayList<FlatButton> flatButtons = new ArrayList<FlatButton>();
    private ArrayList<String> inputPakanString = new ArrayList<>();
    private ArrayList<Float> inputPakanFloat = new ArrayList<Float>();
    private ArrayList<Float> protein = new ArrayList<Float>();
    private ArrayList<Float> energy = new ArrayList<Float>();
    private ArrayList<Float> hasilProtein = new ArrayList<Float>();
    private ArrayList<Float> hasilEnergy = new ArrayList<Float>();
    Float[] hargaPakanFloat=new Float[10];
    String[] hargaPakan; Float[] defaults={Float.valueOf(0), Float.valueOf(0), Float.valueOf(0), Float.valueOf(0), Float.valueOf(0),
                                            Float.valueOf(0), Float.valueOf(0), Float.valueOf(0), Float.valueOf(0), Float.valueOf(0)};

    float sum= (float) 0.0;float sumProtein= (float) 0.0;float sumEnergy= (float) 0.0;float sumHargaPakan= (float) 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlatUI.initDefaultValues(this);
        FlatUI.setDefaultTheme(FlatUI.DEEP);//set the theme
        getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, FlatUI.GRASS, false, 2));

        Intent intent = getIntent();
        if(intent!=null) {
            hargaPakan = intent.getStringArrayExtra("harga pakan");
        }

        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_pakanJadi));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_bekatulPadi));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_dedakPadi));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_jagungGiling));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_polar));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_tepungIkanKls1));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_tepungIkanKls2));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_konsentrat));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_bungkilKedele));
        flatEditTexts.add((FlatEditText) findViewById(R.id.edittext_minyakSawit));

        flatButtons.add((FlatButton) findViewById(R.id.button_hitung));
        flatButtons.get(0).setOnClickListener(this);
        protein.add((float)0.22);
        protein.add((float)0.13);
        protein.add((float)0.10);
        protein.add((float)0.09);
        protein.add((float)0.16);
        protein.add((float)0.60);
        protein.add((float)0.40);
        protein.add((float)0.32);
        protein.add((float)0.44);
        protein.add((float)0.00);

        energy.add((float)2700);
        energy.add((float)1850);
        energy.add((float)2980);
        energy.add((float)3300);
        energy.add((float)1800);
        energy.add((float)3600);
        energy.add((float)2487);
        energy.add((float)2500);
        energy.add((float)2446);
        energy.add((float)7356);
    }

void getAllInputs(){
    for(int i=0;i<10;i++) {
        if((flatEditTexts.get(i).getText().toString()).isEmpty()){
            inputPakanString.add("0");
        }else{
        inputPakanString.add(flatEditTexts.get(i).getText().toString());
    }
    }
   // for(int i=0;i<10;i++) {
     //   Log.d("inputs",inputPakanString.get(i));
    //}

}

    void hitungHargaPakan(){
        if(hargaPakan!=null) {
            for (int i = 0; i < 10; i++) {
                hargaPakanFloat[i] = (Float.parseFloat(hargaPakan[i])) * Float.parseFloat(inputPakanString.get(i));

                Log.d("harga pakan ayam", String.valueOf(hargaPakanFloat[i]));
          }
        }else{
            hargaPakanFloat =defaults;
        }
        for (int i = 0; i < 10; i++) {
            sumHargaPakan=sumHargaPakan+hargaPakanFloat[i];
        }
    }


void convertToFloatandSum(){
    for(int i=0;i<10;i++) {
        inputPakanFloat.add((Float.parseFloat(inputPakanString.get(i))));
    }
    //for(int i=0;i<10;i++) {
          // Log.d("inputs", String.valueOf(inputPakanFloat.get(i)));
       // }

    for(int i=0;i<10;i++) {
        sum=sum+inputPakanFloat.get(i);
    }
    Log.d("sum", String.valueOf(sum));
}

    void calculateProtein(){
        for(int i=0;i<10;i++) {
            if((inputPakanFloat.get(i)).equals(0.0)){
                hasilProtein.add((float)0.0);
            }else{
                hasilProtein.add((float)(inputPakanFloat.get(i)/sum)*protein.get(i));
            }
        }
        //for(int i=0;i<10;i++) {
            //Log.d("hasil protein", String.valueOf(hasilProtein.get(i)));
        //}
        for(int i=0;i<10;i++) {
            sumProtein=sumProtein+hasilProtein.get(i);
        }
        sumProtein=sumProtein*100;
        Log.d("sum protein", String.valueOf(sumProtein));
    }

    void calculateEnergy(){
        for(int i=0;i<10;i++) {
            if((inputPakanFloat.get(i)).equals(0.0)) {
                hasilEnergy.add((float) 0.0);
            }else{
                hasilEnergy.add((float)(inputPakanFloat.get(i)/sum)*energy.get(i));
            }
        }
        for(int i=0;i<10;i++) {
            sumEnergy=sumEnergy+hasilEnergy.get(i);
        }
        //Log.d("sum protein", String.valueOf(sumEnergy));
        //for(int i=0;i<10;i++) {
          //  Log.d("hasil energi", String.valueOf(hasilEnergy.get(i)));
        //}
    }




    @Override
    public void onClick(View v) {
        getAllInputs();
        convertToFloatandSum();
        calculateProtein();
        calculateEnergy();
        hitungHargaPakan();

        Intent intent=new Intent(this,HasilActivity.class);
        intent.putExtra("hasil protein", hasilProtein);
        intent.putExtra("sumProtein",sumProtein);
        intent.putExtra("hasil energy", hasilEnergy);
        intent.putExtra("sumEnergy",sumEnergy);
        intent.putExtra("jumlahHargaPakan",sumHargaPakan);

        startActivity(intent);
    }
}

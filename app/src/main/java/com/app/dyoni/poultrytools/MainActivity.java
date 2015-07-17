package com.app.dyoni.poultrytools;

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
    private ArrayList<Float> hasilProtein = new ArrayList<Float>();
    float sum= (float) 0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FlatUI.initDefaultValues(this);
        FlatUI.setDefaultTheme(FlatUI.DEEP);//set the theme
        getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, FlatUI.GRASS, false, 2));

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
        for(int i=0;i<10;i++) {
            Log.d("hasil protein", String.valueOf(hasilProtein.get(i)));
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        getAllInputs();
        convertToFloatandSum();
        calculateProtein();
    }
}

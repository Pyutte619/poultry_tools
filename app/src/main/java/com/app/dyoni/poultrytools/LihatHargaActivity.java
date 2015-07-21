package com.app.dyoni.poultrytools;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.cengalabs.flatui.FlatUI;
import com.cengalabs.flatui.views.FlatButton;
import com.cengalabs.flatui.views.FlatEditText;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piyut on 18/07/2015.
 */
public class LihatHargaActivity extends ActionBarActivity implements View.OnClickListener{

    ListView listViewHarga;String[] listPakanString;
    FlatEditText usernameSignUp,emailSignUp,passwordSignup,emailLogin,passwordLogin,inputHargaET;
    String emailUser,usernameUser,passwordUser,inputHarga;
    String[] listHargaString;String loginSukses,loginGagal,daftarSukses,daftarGagal;
    ParseApplication dialogs=new ParseApplication();
    ParseObject users = new ParseObject("User");
    ArrayList<Integer> inputHargaPakan= new ArrayList<>();
    //Dialogs dialog=new Dialogs();
    FlatButton btn_keluar,btn_kembaliHitung;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihatharga);
        FlatUI.initDefaultValues(this);
        FlatUI.setDefaultTheme(FlatUI.DEEP);//set the theme
        getSupportActionBar().setBackgroundDrawable(FlatUI.getActionBarDrawable(this, FlatUI.GRASS, false, 2));


       // ParseUser currentUser = ParseUser.getCurrentUser();
       // if (currentUser != null) {
       //     // do stuff with the user

        //} else {
            // show the signup or login screen

      //      alertDialogs();

       // }

        Intent intent = getIntent();
        if(intent!=null) {
            inputHargaPakan = intent.getIntegerArrayListExtra("input harga pakan");
        }
        //for(int i=0;i<10;i++) {
          //  Log.d("intent harga", String.valueOf(inputHargaPakan.get(i)));
       // }

        btn_keluar = (FlatButton) findViewById(R.id.button_keluar);
        btn_kembaliHitung= (FlatButton) findViewById(R.id.button_kembaliHitung);

        Resources res=getResources();
        listPakanString =res.getStringArray(R.array.pakanArray);
        listHargaString =res.getStringArray(R.array.hargaPakan);

        if(inputHargaPakan!=null){

            for(int i=0;i<10;i++) {
                listHargaString[i]= String.valueOf(inputHargaPakan.get(i));
            }
        }

        listViewHarga = (ListView) findViewById(R.id.listView);

        CustomList adapter = new
                CustomList(LihatHargaActivity.this, listPakanString,listHargaString);

        listViewHarga.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               for(int i=0;i<10;i++){

                   if(position==i){
                        alertInputHarga(listPakanString[i],position);


                   }
               }

            }
        });
        btn_keluar.setOnClickListener(this);
        btn_kembaliHitung.setOnClickListener(this);
        listViewHarga.setAdapter(adapter);
       }

   // void loadData(){
     //   SharedPreferences sharedPreferences=getSharedPreferences("dataHargaPakan", Context.MODE_PRIVATE);
       // String[] daftarHarga=sharedPreferences.getStringSet("daftar harga",);
    //}

   void alertDialogs(){

       MaterialDialog dialog = new MaterialDialog.Builder(this)
               .title(R.string.title)
               .customView(R.layout.custom_view, true)
               .positiveText(R.string.positive)
               .negativeText(R.string.negative)
               .callback(new MaterialDialog.ButtonCallback() {
                   @Override
                   public void onPositive(MaterialDialog dialog) {
                       //login
                       emailUser=emailLogin.getText().toString();
                       passwordUser=passwordLogin.getText().toString();
                       Log.d("email login",emailUser);
                       Log.d("pass login",passwordUser);
                       if(emailUser.isEmpty()||passwordUser.isEmpty()){
                           alertDialogs();
                       }else{

                           loggingIn();

                       }
                   }

                   @Override
                   public void onNegative(MaterialDialog dialog) {
                       //daftar
                       MaterialDialog dialog2 = new MaterialDialog.Builder(LihatHargaActivity.this)
                               .title(R.string.title)
                               .customView(R.layout.custom_signup, true)
                               .positiveText(R.string.negativeSign)
                               .negativeText(R.string.positive)
                               .callback(new MaterialDialog.ButtonCallback() {
                                   @Override
                                   public void onPositive(MaterialDialog dialog) {
                                       //daftar
                                       emailUser=emailSignUp.getText().toString();
                                       usernameUser=usernameSignUp.getText().toString();
                                       passwordUser=passwordSignup.getText().toString();
                                       Log.d("email daftar",emailUser);
                                       Log.d("pass daftar",passwordUser);
                                       if(emailUser.isEmpty()||passwordUser.isEmpty()||usernameUser.isEmpty()){
                                           alertDialogs();
                                       }else{

                                           signingUp();}

                                   }

                                   @Override
                                   public void onNegative(MaterialDialog dialog) {
                                       //back to login
                                       alertDialogs();
                                   }
                               }).build();
                       emailSignUp = (FlatEditText) dialog2.getCustomView().findViewById(R.id.signup_email);
                       usernameSignUp = (FlatEditText) dialog2.getCustomView().findViewById(R.id.signup_username);
                       passwordSignup = (FlatEditText) dialog2.getCustomView().findViewById(R.id.signup_password);

                       dialog2.show();

                   }
               }).build()
               ;
       emailLogin =  (FlatEditText)  dialog.getCustomView().findViewById(R.id.edittext_email);
       passwordLogin = (FlatEditText) dialog.getCustomView().findViewById(R.id.edittext_password);

       dialog.show();
   }

    void alertInputHarga(String pakan, final int position){
            MaterialDialog dialog3 = new MaterialDialog.Builder(this)
                    .title("Masukan Harga "+pakan)
                    .customView(R.layout.input_harga, true)
                    .positiveText("Ok")
                    .negativeText("Cancel")
                    .callback(new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            //showToast("Password: " + passwordInput.getText().toString());
                            inputHarga=inputHargaET.getText().toString();
                            listHargaString[position]=inputHarga;

                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {
                        }
                    }).build();
        inputHargaET =  (FlatEditText)  dialog3.getCustomView().findViewById(R.id.edittext_inputharga);
        dialog3.show();
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
        }else if(id==R.id.action_logout){
            logggingOut();
        }

        return super.onOptionsItemSelected(item);
    }

    void signingUp(){
        ParseUser user = new ParseUser();
        user.setUsername(usernameUser);
        user.setPassword(passwordUser);
        user.setEmail(emailUser);


        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Log.d("daftar sukses","sukses");
                    daftarSukses="sukses";
                    // Hooray! Let them use the app now.
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                    //TODO:bikin alert salah password or username
                    daftarGagal="gagal";
                    Toast.makeText(LihatHargaActivity.this,"periksa koneksi internet anda",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void loggingIn(){
        ParseUser.logInInBackground(emailUser, passwordUser, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    // Hooray! The user is logged in.
                    //TODO:loginnya pake username bukan email
                    loginSukses="sukses";
                    Log.d("login sukses","sukses");
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    loginGagal="gagal";
                    //TODO:bikin alert salah password or username
                    alertDialogs();
                    Log.d("login gagal","gagal");
                }
            }
        });
    }

    void logggingOut(){
        ParseUser.logOut();
        ParseUser currentUser = ParseUser.getCurrentUser(); // this will now be null
    }

    @Override
    public void onClick(View v) {
        if(v.equals(btn_keluar)) {
            //Intent intent = new Intent(this, EditHargaActivity.class);
           // startActivity(intent);
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }else{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("harga pakan",listHargaString);
            startActivity(intent);
        }
    }

    void putHarga(final String harga, final int position){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("User");
        query.whereEqualTo("username",usernameUser);

// Retrieve the object by id
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
            users.put("hargaPakan",harga);
                listHargaString[position]=harga;
            }
        });
        }
    }





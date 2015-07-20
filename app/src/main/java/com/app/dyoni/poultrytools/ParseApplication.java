package com.app.dyoni.poultrytools;

import android.app.AlertDialog;
import android.app.Application;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.afollestad.materialdialogs.MaterialDialog;
import com.parse.Parse;

/**
 * Created by tegar dion on 18/07/2015.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        Parse.enableLocalDatastore(this);
        Parse.initialize(this,  "ik2yZ4YS0LNiczeA9U0OvCZQnWn5m7pCMk0eqbDI", "4TAis1kv4nxxSIJL0rehucQJAp0ScP0fmdehVyeM");
    }
}
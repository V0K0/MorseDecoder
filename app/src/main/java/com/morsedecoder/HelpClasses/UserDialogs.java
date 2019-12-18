package com.morsedecoder.HelpClasses;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.morsedecoder.R;

public final class UserDialogs {

    private UserDialogs(){}

    public static void ShowAlertDialog(Context context, String alertMessage){
        new AlertDialog.Builder(context).setTitle(context.getString(R.string.DEFAULT_ERROR))
                .setMessage(alertMessage)
                .setPositiveButton(context.getString(R.string.okey), (dialog, which) -> {
                });
    }

    public static void ShareDialog(Context context, String message){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        Intent childIntent = Intent.createChooser(intent, context.getString(R.string.share_through));
        context.startActivity(childIntent);
    }

    public static void checkCameraPermission(Activity activity){
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 0);
        }
    }
}

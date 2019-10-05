package com.example.whatsapp.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissions {
    public static boolean validatePermission(String[]permissions, Activity activity){
        if(Build.VERSION.SDK_INT>=23){
            List<String>listPermissions = new ArrayList<>();

            for(String permission : permissions){
               Boolean havePermission =  ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_GRANTED;

               if(!havePermission) listPermissions.add(permission);
            }
            if(listPermissions.isEmpty()) return true;

            String[]permissionsArray = new String[listPermissions.size()];
            listPermissions.toArray(permissionsArray);

            ActivityCompat.requestPermissions(activity,permissionsArray , 1);

        }


        return true;
    }



}

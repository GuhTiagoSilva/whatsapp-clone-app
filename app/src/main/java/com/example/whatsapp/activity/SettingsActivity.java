package com.example.whatsapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;

import com.example.whatsapp.R;
import com.example.whatsapp.helper.Permissions;

public class SettingsActivity extends AppCompatActivity {

    private String[] neededPermissions = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Permissions.validatePermission(neededPermissions,this);

        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Ajustes");
        setSupportActionBar(toolbar);

        //botão de voltar na toolbar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for(int permissionResult : grantResults){
            if(permissionResult== PackageManager.PERMISSION_DENIED){

                alertPermissionValidate();
            }
        }

    }
    private void alertPermissionValidate(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Permissões negadas");
        dialog.setMessage("Para utilizar o app é necessário aceitar as permissões necessárias");
        dialog.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog newDialog = dialog.create();
        newDialog.show();
    }

}

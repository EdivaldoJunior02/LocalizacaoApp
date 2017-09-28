package com.example.aluno.localizacao;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText txtLatitude;
    private EditText txtLongitude;


    private Location location;
    private LocationManager locationManager;

    private Address endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLatitude = (EditText) findViewById(R.id.eTLat);
        txtLongitude = (EditText) findViewById(R.id.eTLong);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getLocation();
    }

    void getLocation(){

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }else{
            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if(location!= null){
                double lat = location.getLatitude();
                double lon = location.getLongitude();

                txtLatitude.setText("Latitude: "+lat);
                txtLongitude.setText("Longitude: "+lon);
            }else {
                txtLatitude.setText("...");
                txtLongitude.setText("...");
            }
        }

    }

    @Override
    public void OnRequestPermissionsResult(int requestCode, @NonNull String[] permissions @NonNull int[] grantResults){

    }

}

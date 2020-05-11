package com.example.cameratospeech;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.SparseArray;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    SurfaceView cameraView;
    TextView textView;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;
/*
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraView.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            case  PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }
    TextToSpeech t1;
    Button b1;



    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1002;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        /*cameraView = (SurfaceView)findViewById(R.id.surface_view);
        textView = (TextView)findViewById(R.id.text_view);
        b1 = (Button) findViewById(R.id.button2);



        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR)
                {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toSpeak=textView.getText().toString();
                Toast.makeText(getApplicationContext(),toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
            }
        });
        TextRecognizer textRecognizer = new  TextRecognizer.Builder(getApplicationContext()).build();
        if(!textRecognizer.isOperational())
        {
            Log.w("MainActivity","Detector dependencies are not yet available");
        }
        else{
            cameraSource = new CameraSource.Builder(getApplicationContext(),textRecognizer)
                    .setFacing(CameraSource.CAMERA_FACING_BACK)
                    .setRequestedPreviewSize(1280,1024)
                    .setRequestedFps(2.0f)
                    .setAutoFocusEnabled(true)
                    .build();
            cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {

                    try {
                        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)
                        {
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.CAMERA},
                                    RequestCameraPermissionID);
                            return;

                        }
                        cameraSource.start(cameraView.getHolder());

                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    cameraSource.stop();
                }
            });

            textRecognizer.setProcessor(new Detector.Processor<TextBlock>() {
                @Override
                public void release() {

                }

                @Override
                public void receiveDetections(Detector.Detections<TextBlock> detections) {

                    final SparseArray<TextBlock> items = detections.getDetectedItems();
                    if(items.size()!=0)
                    {
                        textView.post(new Runnable() {
                            @Override
                            public void run() {
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int i = 0; i<items.size();++i)
                                {
                                    TextBlock item = items.valueAt(i);
                                    stringBuilder.append(item.getValue());
                                    stringBuilder.append("\n");
                                }
                                textView.setText(stringBuilder.toString());
                            }
                        });
                    }
                }
            });
        }
    */}
/*
    private void pickImageFromGallery() {
        Intent intent =new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }*/
    public void onPause()
    {
        /*if (t1!=null)
        {
            t1.stop();
            t1.shutdown();
        }*/
        super.onPause();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()){
                        case R.id.nav_camera:
                            selectedFragment = new CameraFragment();
                            break;
                        case R.id.nav_profile:
                            selectedFragment = new ProfileFragment();
                            break;
                        case R.id.nav_file:
                            selectedFragment = new FileFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();

                    return true;
                }
            };



}

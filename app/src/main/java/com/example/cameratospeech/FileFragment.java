package com.example.cameratospeech;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.text.TextBlock;
import com.google.android.gms.vision.text.TextRecognizer;

import java.util.Locale;


public class FileFragment extends Fragment {
    private static final int RESULT_OK = -1;
    ImageView mImageView;
    //Button mChooseBtn;
    View view;
    Button btndetect;
    TextView tView;
    TextToSpeech t2;

    private static final int IMAGE_PICK_CODE = 1000;
    private static final int PERMISSION_CODE = 1001;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view  = inflater.inflate(R.layout.fragmant_file,container,false);
        mImageView = (ImageView)view.findViewById(R.id.image_view);
        //mChooseBtn = (Button)view.findViewById(R.id.choose_image_btn);
        btndetect = (Button)view.findViewById(R.id.button_detect);
        tView = (TextView)view.findViewById(R.id.detectedText);

        mImageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_DENIED){
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        requestPermissions(permissions, PERMISSION_CODE);
                    }else{
                        pickImageFromGallery();
                    }
                }else{
                    pickImageFromGallery();
                }
            }
        });

        t2 = new TextToSpeech(getActivity().getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status!=TextToSpeech.ERROR)
                {
                    t2.setLanguage(Locale.UK);
                }
            }
        });

        btndetect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak= tView.getText().toString();
                Toast.makeText(getActivity().getApplicationContext(),toSpeak,Toast.LENGTH_SHORT).show();
                t2.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        return view;
    }

    private void pickImageFromGallery() {
        Intent intent =new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, IMAGE_PICK_CODE);
    }

    public void  getTextFromImage(View view){
        Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.p);
        TextRecognizer textRecognizer = new TextRecognizer.Builder(getContext()).build();
        if(!textRecognizer.isOperational()){
            Log.w("FileFragment","Detector dependencies are not yet available");
        }
        else{
            Frame frame = new Frame.Builder().setBitmap(bitmap).build();
            SparseArray<TextBlock> items = textRecognizer.detect(frame);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i<items.size();++i)
            {
                TextBlock myItem = items.valueAt(i);
                sb.append(myItem.getValue());
                sb.append("\n");
            }
            tView.setText(sb.toString());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case  PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(getActivity().getApplicationContext(), "Permission denied...!", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            mImageView.setImageURI(data.getData());
            BitmapDrawable bitmapDrawable = (BitmapDrawable) mImageView.getDrawable();
            Bitmap bitmap = bitmapDrawable.getBitmap();
            TextRecognizer recognizer = new TextRecognizer.Builder(getActivity()).build();

            if (!recognizer.isOperational()) {
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            } else {
                Frame frame = new Frame.Builder().setBitmap(bitmap).build();
                SparseArray<TextBlock> items = recognizer.detect(frame);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < items.size(); i++) {
                    TextBlock myItem = items.valueAt(i);
                    sb.append(myItem.getValue());
                    sb.append("\n");

                }
                tView.setText(sb.toString());
            }
        }
        }
    }


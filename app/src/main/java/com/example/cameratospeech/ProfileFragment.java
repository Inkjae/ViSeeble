package com.example.cameratospeech;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;


public class ProfileFragment extends Fragment {
    private static final int REQUEST_CALL = 1;
    Button callbtn;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmant_profile, container, false);
        callbtn = (Button) view.findViewById(R.id.callbtn);

        callbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });return view;
    }



    private void makePhoneCall() {
        if(ContextCompat.checkSelfPermission(getContext().getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
        }else{
            String dial = "tel:0844154891";
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            }else {
                Toast.makeText(getContext(),"Permission Denied",Toast.LENGTH_SHORT).show();
            }
        }
    }


}

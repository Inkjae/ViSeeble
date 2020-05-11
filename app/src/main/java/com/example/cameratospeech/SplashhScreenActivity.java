package com.example.cameratospeech;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class SplashhScreenActivity extends AppCompatActivity {

    private int SLEEP_TIMER = 5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splashh_screen);
        getSupportActionBar().hide();
        Splash splash = new Splash();
        splash.start();

        tts.getInstance(this)
                .setEngine("com.google.android.tts")
                .setLocale(new Locale("th"))
                .speak("Visible เป็นแอปพลิเคชั่นที่ให้ความช่วยเหลือผู้พิการทางด้านการมองเห็น จะอ่านข้อความออกมาเป็นเสียง ผ่านการสแกนด้วยกล้อง    ปุ่มด้านล่างซ้ายมือ เป็นปุ่มประวัติส่วนตัว หากเกิดเหตุฉุกเฉินผู้ช่วยเหลือสามารถให้ผู้อื่นดูข้อมูลฉุกเฉินที่เราบันทึกไว้ได้    ปุ่มด้านล่างตรงกลางเป็นปุ่มกล้อง เมื่อใช้กล้องส่องไปยังข้อความ และคลิกที่ปุ่มหนึ่งครั้ง จะอ่านข้อความออกมาเป็นเสียง     ปุ่มด้านล่างขวามือ เป็นปุ่มเปิดไฟล์หรือรูป เพื่อเปิดอ่านข้อความออกมาเป็นเสียง");
    }
    private class Splash extends Thread {
        public void run() {
            try {
                sleep(1000*SLEEP_TIMER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(SplashhScreenActivity.this,MainActivity.class);
            startActivity(intent);
            SplashhScreenActivity.this.finish();
        }
    }


}

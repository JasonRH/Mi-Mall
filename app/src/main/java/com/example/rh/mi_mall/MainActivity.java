package com.example.rh.mi_mall;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.rh.core.app.MyApp;

/**
 * @author RH
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(MyApp.getApplicationContext(), "这是全局Context", Toast.LENGTH_SHORT).show();
    }
}

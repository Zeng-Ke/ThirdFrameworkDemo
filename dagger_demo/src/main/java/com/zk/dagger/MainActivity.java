package com.zk.dagger;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zk.dagger.bean.Computer;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Computer computer = new Computer();
        computer.run();

        Computer computer2 = new Computer();
        computer2.run();


    }
}

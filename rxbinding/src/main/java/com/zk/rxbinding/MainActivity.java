package com.zk.rxbinding;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxAdapter;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class MainActivity extends AppCompatActivity {

    private TextView mTvTouch;
    private TextView mTvClick;
    private TextView mBtnCountDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvTouch = findViewById(R.id.touch);
        mTvClick = findViewById(R.id.click);

        mBtnCountDown = findViewById(R.id.btn_countdown);

        touch();
        click();
    }

    public void launch(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }

    public void countdown(View view) {
        launch(CountDownActivity.class);
    }

    public void inputsearch(View view) {
        launch(InputSearchActivity.class);
    }

    public void fastclick(View view) {
        launch(FastClickActivity.class);
    }

    public void multijudge(View view) {
        launch(MultJudgeActivity.class);
    }


    private void click() {
        RxView
                .clicks(mTvClick)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Toast.makeText(getApplicationContext(), o.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void touch() {

        RxView
                .touches(mTvTouch, new Predicate<MotionEvent>() {
                    @Override
                    public boolean test(MotionEvent motionEvent) throws Exception {
                        return true;
                    }
                })
                .subscribe(new Consumer<MotionEvent>() {

                    @Override
                    public void accept(MotionEvent motionEvent) throws Exception {
                        mTvTouch.setText(String.valueOf(motionEvent.getX()));

                    }
                });


    }


}

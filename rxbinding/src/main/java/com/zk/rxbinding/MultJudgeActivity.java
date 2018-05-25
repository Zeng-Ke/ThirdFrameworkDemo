package com.zk.rxbinding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function3;

public class MultJudgeActivity extends AppCompatActivity {

    private EditText mEdName;
    private EditText mEdAge;
    private EditText mEdAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mult_judge);

        mEdName = findViewById(R.id.ed_name);
        mEdAge = findViewById(R.id.ed_age);
        mEdAddress = findViewById(R.id.ed_address);

        final TextView btnRegister = findViewById(R.id.btn_register);

        final Observable<CharSequence> nameObservable = RxTextView.textChanges(mEdName).skip(1);
        final Observable<CharSequence> ageObservable = RxTextView.textChanges(mEdAge).skip(1);
        final Observable<CharSequence> addressObservable = RxTextView.textChanges(mEdAddress).skip(1);


        Observable.combineLatest(nameObservable, ageObservable, addressObservable, new Function3<CharSequence,
                CharSequence, CharSequence, Boolean>() {

            @Override
            public Boolean apply(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3)
                    throws Exception {
                if (charSequence != null && charSequence.length() > 0 && charSequence2 != null && charSequence2.length() > 0 &&
                        charSequence != null && charSequence3.length() > 0)
                    return true;
                return false;
            }
        })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        btnRegister.setEnabled(aBoolean);
                    }
                });

        RxView.clicks(btnRegister)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}

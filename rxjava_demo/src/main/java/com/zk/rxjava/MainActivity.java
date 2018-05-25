package com.zk.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zk.rxjava.operator.ConditionOperator;
import com.zk.rxjava.operator.CreateOperator;
import com.zk.rxjava.operator.FilterOperator;
import com.zk.rxjava.operator.FunctionOperator;
import com.zk.rxjava.operator.MergeOperator;
import com.zk.rxjava.operator.TransformOperator;
import com.zk.rxjava.rxjava_retrofit.MObserver;
import com.zk.rxjava.rxjava_retrofit.bean.CommonDataBean;
import com.zk.rxjava.rxjava_retrofit.bean.DoubleListBean;
import com.zk.rxjava.rxjava_retrofit.bean.HttpService;
import com.zk.rxjava.rxjava_retrofit.bean.PhoneInfoBean;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       /* final TextView textView = findViewById(R.id.tv);

        HttpService httpService = new HttpService();

        httpService.getPhoneNumber("13610100000",new MObserver<List<PhoneInfoBean>>() {
            @Override
            public void onNext(List<PhoneInfoBean> o) {
                if (o != null && o.size() > 0)
                    textView.setText(o.get(0).toString());
            }
        });
*/
       //FunctionOperator.debounce();
       FunctionOperator.subscribeOn_observerOn();


    }
}

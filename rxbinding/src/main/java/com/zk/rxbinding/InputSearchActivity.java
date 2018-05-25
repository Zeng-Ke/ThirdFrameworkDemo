package com.zk.rxbinding;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class InputSearchActivity extends AppCompatActivity {


    private EditText mEditText;
    private RecyclerView mRecyclerView;
    private MAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_search);
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mEditText = findViewById(R.id.edittext);

        final List<String> stringList = new ArrayList<>();
        stringList.add("Java");
        stringList.add("PHP");
        stringList.add("NET");
        stringList.add("Python");
        stringList.add("JS");


        RxTextView.textChanges(mEditText)
                //忽略第一次。避免editText初始化为空时就发送了一次事件
                .skip(1)
                //停止操作0.5秒后，发送事件
                .debounce(500, TimeUnit.MILLISECONDS)
                //当同时又多个网络请求时，会以最后一个发送请求为准，其它的请求网络数据会被最后一个被覆盖
                .switchMap(new Function<CharSequence, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(CharSequence charSequence) throws Exception {
                        if (charSequence != null) {
                            int length = charSequence.length();
                            if (length >= 2) {
                                return Observable.fromArray(stringList);
                            } else if (length >= 1) {
                                List<String> strings = stringList.subList(0, 2);
                                return Observable.fromArray(strings);
                            } else {
                                List<String> strings = stringList.subList(0, 0);
                                return Observable.fromArray(strings);
                            }
                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Consumer<List<String>>() {
                            @Override
                            public void accept(List<String> strings) throws Exception {
                                mAdapter.setData(strings);
                            }
                        });
    }


    public class MAdapter extends RecyclerView.Adapter<MAdapter.MHolder> {

        int color = Color.parseColor("#ff502d");
        private List<String> mList;


        public void setData(List<String> datas) {
            mList = datas;
            this.notifyDataSetChanged();
        }

        public void clear() {
            if (mList != null && mList.size() > 0)
                mList.clear();
        }


        @Override
        public MHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            //TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);

            TextView textView = new TextView(getApplicationContext());
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 90);
            textView.setLayoutParams(layoutParams);
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(color);
            return new MHolder(textView);
        }

        @Override
        public void onBindViewHolder(MHolder holder, int position) {
            if (mList != null && mList.size() > 0)
                holder.tv.setText(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList == null ? 0 : mList.size();
        }


        public class MHolder extends RecyclerView.ViewHolder {

            public TextView tv;

            public MHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView;
            }
        }
    }


}

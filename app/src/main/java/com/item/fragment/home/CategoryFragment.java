package com.item.fragment.home;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.item.fragment.R;
import com.item.fragment.base.BaseLazyFragment;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends BaseLazyFragment {

    private TextView textView;
    private String type;
    private String text;

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Bundle bundle = getArguments();
        type =  bundle.getString("type","");
        Log.d("jiejie","-----" + type);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        textView = view.findViewById(R.id.category_text);
        Log.d("jiejie","--------------onCreateView");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void onFirstUserVisible() {
        // 请求接口
        // http://gank.io/api/data/Android/3/1
        Log.d("jiejie","-----------" + "  onFirst");
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://gank.io/api/data/" + type +"/1/1")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call,final IOException e) {
                text = text + e;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(text);
                    }
                });

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                text = text + response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(text);
                    }
                });
            }
        });
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }
    protected CategoryFragment setArguments(String type){
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        setArguments(bundle);
        return this;
    }

}

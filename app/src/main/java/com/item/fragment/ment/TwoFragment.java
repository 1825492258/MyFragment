package com.item.fragment.ment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.item.fragment.R;
import com.item.fragment.base.BaseLazyFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends BaseLazyFragment {

    private TextView textView;
    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("jiejie","TwoFragment-----------" );
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        textView = view.findViewById(R.id.text);
        return textView;
    }


    @Override
    protected void onFirstUserVisible() {
        Log.d("jiejie","TwoFragment---onFirstUserVisible");
        textView.setText("网络请求的数据");
    }

    @Override
    protected void onUserVisible() {
        Log.d("jiejie","TwoFragment---onUserVisible");
    }

    @Override
    protected void onUserInvisible() {
        Log.d("jiejie","TwoFragment---onUserInvisible");
    }
}

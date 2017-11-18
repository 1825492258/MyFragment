package com.item.fragment.ment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.item.fragment.R;
import com.item.fragment.activity.IOneTrigger;

/**
 * 登录的界面
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private Button mSubmit; // 登录
    private TextView goRegister; // 去注册页面
    private IOneTrigger mTrigger;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 拿到我们的Activity的引用
        mTrigger = (IOneTrigger) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSubmit.setOnClickListener(this);
        goRegister.setOnClickListener(this);
    }

    private void initView(View view) {
        mSubmit = view.findViewById(R.id.btn_submit);
        goRegister = view.findViewById(R.id.txt_go_register);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_submit:
                break;
            case R.id.txt_go_register: // 去注册页
                mTrigger.triggerView();
                break;
        }
    }
}

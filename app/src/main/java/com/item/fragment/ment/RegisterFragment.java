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
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment implements View.OnClickListener {

    private Button mRegister; // 登录
    private TextView goLogin; // 去展示登录界面
    private IOneTrigger mTrigger;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mTrigger = (IOneTrigger)context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRegister = view.findViewById(R.id.btn_register);
        goLogin = view.findViewById(R.id.txt_go_login);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRegister.setOnClickListener(this);
        goLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_register: // 点击注册
                break;
            case R.id.txt_go_login: // 点击去登录
                mTrigger.triggerView();
                break;
        }
    }
}

# MyFragment
LazyFragment Activity和Fragment的交互

### SVG图片下载的网址
    AS自带     Vector Asset
    ICONS      https://material.io/icons/
    阿里图库    http://www.iconfont.cn/

### Fragment的懒加载处理
    如下是BaseLazyFragment的主要代码
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.d(TAG, "-----setUserVisibleHint" + isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    private synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    /**
     * when fragment is visible for the first time, here we can do some initialized work
     * or refresh data only once
     */
    protected abstract void onFirstUserVisible();

    /**
     * this method like the fragment's lifecycle method onResume()
     */
    protected abstract void onUserVisible();


    /**
     * when fragment is invisible for the first time
     */
    private void onFirstUserInvisible() {
        // here we do not recommend do something
    }

    /**
     * this method like the fragment's lifecycle method onPause()
     */
    protected abstract void onUserInvisible();
    然后配合ViewPager的使用
    fragments.add(new TwoFragment());
    fragments.add(new TwoFragment());
    fragments.add(new TwoFragment());
    fragments.add(new TwoFragment());
    // 设置TabLayout的模式
    mTabLayout.setTabMode(TabLayout.MODE_FIXED);
    mViewPager.setOffscreenPageLimit(fragments.size());
    mViewPager.setAdapter(new TabAdapter(getSupportFragmentManager()));
    // 关联ViewPager和TabLayout
    mTabLayout.setupWithViewPager(mViewPager);
    // 设置分割线
    LinearLayout linearLayout = (LinearLayout)mTabLayout.getChildAt(0);
    linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
    // 设置分割线间隔
    linearLayout.setDividerPadding(dip2px(15));
    在Fragment中的onFirstUserVisible() 方法中进行网络请求

### Activity 中嵌套2个Fragment,分别是登录界面和注册界面
    当在登录界面点击去注册展示注册界面，反之亦然
    所以需要定义一个接口定义一个方法triggerView
    Activity 实现这个方法来判断展示谁即可
    @Override
    public void triggerView() {
        Fragment fragment;
        if (mCurFragment == mLoginFragment) {
            if (mRegisterFragment == null) {
                // 默认情况下为null
                // 第一次之后就不为null了
                mRegisterFragment = new RegisterFragment();
            }
            fragment = mRegisterFragment;
        } else {
            fragment = mLoginFragment;
        }
        // 重新赋值当前正在显示的Fragment
        mCurFragment = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container, fragment)
                .commit();
    }
    在Fragment再拿到Activity的引用
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

### 实现Button 点击的效果的图片
    <?xml version="1.0" encoding="utf-8"?>
       <selector xmlns:android="http://schemas.android.com/apk/res/android">
           <item android:state_enabled="false">
               <shape android:shape="rectangle">
                   <corners android:radius="22dp" />
                   <solid android:color="#00000000" />
               </shape>
           </item>
           <item android:state_pressed="true">
               <shape android:shape="rectangle">
                   <corners android:radius="22dp" />
                   <solid android:color="#00000000" />
               </shape>
           </item>
           <item>
               <shape android:shape="rectangle">
                   <corners android:radius="22dp" />
                   <solid android:color="#70000000" />
               </shape>
           </item>
       </selector>

### EditText编辑时希望出现的一个左侧光标的实现
    <shape xmlns:android="http://schemas.android.com/apk/res/android"
       android:shape="rectangle">
       <size android:width="2dp" />
       <corners android:radius="0.5dp" />
       <solid android:color="@color/colorAccent" />
    </shape>
    android:textCursorDrawable="@drawable/ic_cursor"

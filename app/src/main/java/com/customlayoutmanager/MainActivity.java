package com.customlayoutmanager;

import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
    View view1;
    View statusBar;
    View navigationBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewGroup decorView= (ViewGroup) getWindow().getDecorView();
        ViewGroup layout= (ViewGroup) decorView.getChildAt(0);
        ViewGroup view= (ViewGroup) getWindow().getDecorView().findViewById(android.R.id.content);
        view1=new View(this);
        ViewGroup.LayoutParams params=new ViewGroup.LayoutParams(100,100);
        view1.setLayoutParams(params);
        view1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        view1.layout(0,0,100,100);
        view.addView(view1);

        ViewServer.get(this).addWindow(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewServer.get(this).removeWindow(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color.snow_white));
        }
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//        decorView.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() {
//            @Override
//            public void onChildViewAdded(View parent, View child) {
//                child.setBackgroundResource(R.mipmap.timg);
//            }
//
//            @Override
//            public void onChildViewRemoved(View parent, View child) {
//
//            }
//        });
//        Log.i("test","childCount-->"+decorView.getChildCount());
        ViewServer.get(this).setFocusedWindow(this);
    }
}

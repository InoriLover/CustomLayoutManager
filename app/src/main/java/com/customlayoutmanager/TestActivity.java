package com.customlayoutmanager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.customlayoutmanager.adapter.SimpleAdapter;
import com.customlayoutmanager.layout.CardLayoutManager;
import com.customlayoutmanager.layout.MyLayoutManager;
import com.customlayoutmanager.layout.SampleLayoutManager;
import com.customlayoutmanager.layout.TestLayoutManager;
import com.customlayoutmanager.layout.TestLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fishy on 2017/3/27.
 */

public class TestActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SimpleAdapter adapter;
    TextView tvLayoutName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        tvLayoutName= (TextView) findViewById(R.id.tvLayoutName);
        tvLayoutName.setText("LinearLayout");
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        adapter = new SimpleAdapter(createData(), this);
//        recyclerView.setLayoutManager(new TestLinearLayoutManager());
        recyclerView.setLayoutManager(new CardLayoutManager());
//        recyclerView.setLayoutManager(new TestLayoutManager());
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    List<String> createData() {
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            data.add("第" + i + "项");
        }
        return data;
    }
}

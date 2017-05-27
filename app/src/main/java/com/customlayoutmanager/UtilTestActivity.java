package com.customlayoutmanager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.customlayoutmanager.adapter.SimpleAdapter;
import com.customlayoutmanager.adapter.SimpleSpinnerAdapter;
import com.customlayoutmanager.util.BaseTypeConverter;
import com.customlayoutmanager.util.DeviceInfoUtil;
import com.customlayoutmanager.util.FishLog;
import com.customlayoutmanager.widget.WheelView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Created by Fishy on 2017/4/7.
 */

public class UtilTestActivity extends AppCompatActivity {
    //    WheelView wheelView;
    Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test_util);
//        wheelView = (WheelView) findViewById(R.id.wheel);
        spinner = (Spinner) findViewById(R.id.spinner);
        SimpleSpinnerAdapter adapter = new SimpleSpinnerAdapter(this, createData());
        spinner.setAdapter(adapter);
        List<String> data = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            data.add("第" + i + "项");
        }
        FishLog.i("test", "device:" + DeviceInfoUtil.getDeviceWidth(this) + ":" + DeviceInfoUtil.getDeviceHeight(this));
//        wheelView.setData(data);
        Log.i("test", DeviceInfoUtil.getAllDeviceInfo(getApplicationContext()));
        Log.i("test", "isDebug:" + BuildConfig.DEBUG);
        testBaseTypeConverter();
        Calendar now = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            }
        };
        DatePickerDialog dialog = new DatePickerDialog(this, dateSetListener,
                now.get(Calendar.YEAR), now.get(Calendar.MONTH), now.get(Calendar.DAY_OF_MONTH));
//        dialog.show();
    }

    List<String> createData() {
        List<String> data = new ArrayList<>();
        data.add("1");
        data.add("2");
        data.add("3");
        data.add("4");
        return data;
    }

    void testBaseTypeConverter() {
        testintToString();
        testStringToInt();
        testbytesToString();
        teststringToBytes();
        testbytesToHexString();
        testbytesToHexStringArray();
    }

    void testintToString() {
        FishLog.i("test", "4-->" + BaseTypeConverter.intToString(4, 2));
    }

    void testStringToInt() {
        FishLog.i("test", "004-->" + BaseTypeConverter.stringToInt("004"));
    }

    void testbytesToString() {
        byte[] data = new byte[]{102, 105, 115, 104};
        FishLog.i("test", BaseTypeConverter.bytesToString(data));
    }

    void teststringToBytes() {
        byte[] data = BaseTypeConverter.stringToBytes("fish");
        FishLog.i("test", "fish-->" + data[0]);
        FishLog.i("test", "fish-->" + data[1]);
        FishLog.i("test", "fish-->" + data[2]);
        FishLog.i("test", "fish-->" + data[3]);
    }

    void testbytesToHexString() {
        byte[] data = new byte[]{102, 105, 115, 104};
        FishLog.i("test", BaseTypeConverter.bytesToHexString(data));
    }

    void testbytesToHexStringArray() {
        byte[] data = new byte[]{102, 105, 115, 104};
        String[] result = BaseTypeConverter.bytesToHexStringArray(data);
        FishLog.i("test", "result-->" + result[0]);
        FishLog.i("test", "result-->" + result[1]);
        FishLog.i("test", "result-->" + result[2]);
        FishLog.i("test", "result-->" + result[3]);
    }
}

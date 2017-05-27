package com.customlayoutmanager.layout;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Fishy on 2017/4/1.
 */

public class SampleLayoutManager extends RecyclerView.LayoutManager {
    //宽、高,item宽高固定
    int itemWidth=-1;
    int itemHeight=-1;


    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        Log.i("test", "childCount:" + getChildCount());
        Log.i("test", "itemCount:" + getItemCount());
        Log.i("test", "isPreLayout:" + state.isPreLayout());
        //先测绘第一次
        if (getChildCount() == 0) { //First or empty layout
            //Scrap measure one child
            View scrap = recycler.getViewForPosition(0);
            addView(scrap);
//            Log.i("test",""+scrap);
            measureChildWithMargins(scrap, 0, 0);
            itemWidth = getDecoratedMeasuredWidth(scrap);
            itemHeight = getDecoratedMeasuredHeight(scrap);


            List<RecyclerView.ViewHolder> list=recycler.getScrapList();
            Log.i("test","size:"+list.size());
            detachAndScrapView(scrap, recycler);
            List<RecyclerView.ViewHolder> list2=recycler.getScrapList();
            Log.i("test","size2:"+list2.size());
            Log.i("test","holder2:"+list2.get(0));
            Log.i("test","id2:"+list2.get(0).itemView);
        }
//        for(int i=0;i<getItemCount();i++){
//            View scrap=recycler.getViewForPosition(0);
//            measureChild(scrap,0,0);
//            Log.i("test","index:"+(i+1)+"\twidth:"+scrap.getMeasuredWidth()+"\theight"+scrap.getMeasuredHeight());
//            Log.i("test","index:"+(i+1)+"\tDecoratedWidth:"+getDecoratedMeasuredWidth(scrap)+
//                    "\tDecoratedHeight"+getDecoratedMeasuredHeight(scrap));
//        }
    }
}

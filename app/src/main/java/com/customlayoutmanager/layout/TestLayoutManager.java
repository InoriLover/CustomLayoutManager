package com.customlayoutmanager.layout;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Fishy on 2017/3/27.
 */

public class TestLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //将视图缓存起来，放入scrap中
        detachAndScrapAttachedViews(recycler);

        //初始的偏移量
        int offsetY = 0;
        int count = getItemCount();
        for (int i = 0; i < count; i++) {
            //从缓存取出的view
            View view = recycler.getViewForPosition(i);
            //将view添加到绑定的recyclerView中
            addView(view);
            //测绘child，宽高设为0,0且可滑动的情况下
            //设为0的宽/高将处于非关系状态，不被其他view消耗
            measureChild(view, 0, 0);
            //得到宽高，带ItemDecoration
            int width = getDecoratedMeasuredWidth(view);
            int height = getDecoratedMeasuredHeight(view);
            //放置view布局，带ItemDecoration
            layoutDecorated(view, 0, offsetY, width, offsetY + height);
            //将offsetY增加
            offsetY += height;
        }
    }

    @Override
    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        Log.i("test","complete:"+state);
    }

    @Override
    public boolean canScrollHorizontally() {
        return super.canScrollHorizontally();
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.scrollVerticallyBy(dy, recycler, state);
    }

}

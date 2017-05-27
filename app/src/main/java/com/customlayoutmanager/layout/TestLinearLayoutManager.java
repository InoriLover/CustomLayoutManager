package com.customlayoutmanager.layout;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Fishy on 2017/4/5.
 */

public class TestLinearLayoutManager extends RecyclerView.LayoutManager {
    //item的宽、高
    //此处作为所有item宽、高一致的情况下使用
    int itemWidth = -1;
    int itemHeight = -1;
    //item位置
    int offset = -1;
    int totalHeight = 1500;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //数据为空的时候，清空视图，并缓存
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
        }
        //在准备的时候不作处理
        //主要是处理动画
        if (state.isPreLayout()) {
            return;
        }
        //子view为空，先测绘一个view并缓存起来
        //默认取值为第一项
        if (getChildCount() == 0) {
            View scrap = recycler.getViewForPosition(0);
            addView(scrap);
            measureChildWithMargins(scrap, 0, 0);

            //给item宽、高赋值
            itemWidth = getDecoratedMeasuredWidth(scrap);
            itemHeight = getDecoratedMeasuredHeight(scrap);

            detachAndScrapView(scrap, recycler);
        }
//        //正式layout之前先移除所有的view
//        detachAndScrapAttachedViews(recycler);
        //layout子view
        for (int i = 0; i < getItemCount(); i++) {
            View tempView = recycler.getViewForPosition(i);
            int topMargin = ((ViewGroup.MarginLayoutParams) tempView.getLayoutParams()).topMargin;
            int leftMargin = ((ViewGroup.MarginLayoutParams) tempView.getLayoutParams()).leftMargin;
            int bottomMargin = ((ViewGroup.MarginLayoutParams) tempView.getLayoutParams()).bottomMargin;
            addView(tempView);
            measureChild(tempView, 0, 0);
            if (offset == -1) {
                offset = 0;
            }
            layoutDecorated(tempView, leftMargin, topMargin + offset, leftMargin + itemWidth,
                    topMargin + offset + itemHeight);
//            detachView(tempView);

            Log.i("test", "topA:" + tempView.getTop());
//            if (i == 5) {
//                Log.i("test","topA:"+tempView.getTop());
//                tempView.setVisibility(View.VISIBLE);
//                attachView(tempView);
//                Log.i("test","topB:"+tempView.getTop());
//            }
            offset = offset + itemHeight + topMargin + bottomMargin;
        }

//        detachAndScrapAttachedViews(recycler);
//        Log.i("test", "size:" + getChildCount());
//        Log.i("test", "size2:" + recycler.getScrapList().size());
//        View view=recycler.getScrapList().get(24).itemView;
//        addView(view);
//        Log.i("test","top:"+view.getTop());
//        view.layout(0,0,itemWidth,itemHeight);
//        Log.i("test","childCount:"+getChildCount());
    }

    /**
     * 水平空间的距离
     *
     * @return
     */
    int getHorizontalSpace() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    /**
     * 竖直空间的距离
     *
     * @return
     */
    int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
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
        totalHeight -= dy;
        Log.i("test", "dy:" + dy);
        Log.i("test", "height:" + totalHeight);
        Log.i("test", "top:" + getChildAt(2).getTop());
        if (totalHeight >= 1500) {
            totalHeight = 1500;
            return 0;
        } else if (totalHeight <= 0) {
            totalHeight = 0;
            return 0;
        } else {
            offsetChildrenVertical(-dy);
            return dy;
        }
    }
}

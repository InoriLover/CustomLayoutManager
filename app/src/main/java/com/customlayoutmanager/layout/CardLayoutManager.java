package com.customlayoutmanager.layout;

import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Fishy on 2017/4/6.
 */

public class CardLayoutManager extends RecyclerView.LayoutManager {
    int totalHeight;
    int itemWidth;
    int itemHeight;

    int canScrollSpace;
    boolean canScroll;
    //可见区域的start和end
    int startY;
    int endY;
    //可见区域的开始、结束的index
    int startIndex = -1;
    int endIndex = -1;
    SparseArray<View> viewCache;

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount() == 0) {
            detachAndScrapAttachedViews(recycler);
        }
        if (state.isPreLayout()) {
            return;
        }
        if (getChildCount() == 0) {
            View scrap = recycler.getViewForPosition(0);
            addView(scrap);
            measureChildWithMargins(scrap, 0, 0);

            itemWidth = getDecoratedMeasuredWidth(scrap);
            itemHeight = getDecoratedMeasuredHeight(scrap);


            detachAndScrapView(scrap, recycler);
        }
        //总高度初始化
        totalHeight = 0;
        locateCurrentPosition(0);
        //这边将view缓存初始化大小
        viewCache = new SparseArray<View>(getItemCount());
        for (int i = 0; i < getItemCount(); i++) {
            View tempView = recycler.getViewForPosition(i);
            addView(tempView);
            //添加后直接加入缓存
            viewCache.put(i, tempView);
            measureChild(tempView, 0, 0);
            layoutDecorated(tempView, 0, totalHeight, itemWidth,
                    totalHeight + itemHeight);
            totalHeight = totalHeight + itemHeight;
        }
        //第一次测绘完成后，将不可见的部分的view先detach掉
        //以下
        for (int j = endIndex + 1; j < getItemCount(); j++) {
            //这边每次移除的是不可见的第一项
            //因为每移除一个，childView数组大小会-1
            //原来的写法detachViewAt(j)，会导致数组越界异常
            detachView(viewCache.get(j));
        }
        totalHeight -= getVerticalSpace();
        if (totalHeight < 0) {
            //不可滑动
            canScroll = false;
        } else {
            canScroll = true;
            canScrollSpace = totalHeight;
        }
    }

    @Override
    public boolean canScrollVertically() {
        return canScroll;
    }

    @Override
    public boolean canScrollHorizontally() {
        return super.canScrollHorizontally();
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        canScrollSpace -= dy;
//        Log.i("test", "dy:" + dy);
//        Log.i("test", "canScrollSpace:" + canScrollSpace);
//        Log.i("test", "totalHeight" + totalHeight);
        //真实的移动距离
        int realDistance;
        if (canScrollSpace >= totalHeight) {
            realDistance = canScrollSpace + dy - totalHeight;
            canScrollSpace = totalHeight;
        } else if (canScrollSpace <= 0) {
            realDistance = canScrollSpace + dy - 0;
            canScrollSpace = 0;
        } else {
            realDistance = dy;
        }
//        Log.i("index","realDistance-->"+realDistance);
        //计算当前的上边界的位置
        int currentStart = totalHeight - canScrollSpace;
        locateCurrentPosition(currentStart);

        offsetChildrenVertical(-realDistance);
        Log.i("count", "childCount-->" + getChildCount());
        return realDistance;
    }

    /**
     * 定位当前的位置，用来回收和添加view使用
     */
    void locateCurrentPosition(int start) {
        startY = start;
        endY = start + getVerticalSpace();
        int startIndex = startY / itemHeight;
        int endIndex = endY / itemHeight;
//        Log.i("index","end-->"+endY+"\tendIndex-->"+endIndex+"\titemheight-->"+itemHeight);
//        Log.i("index","start-->"+startY+"\tend-->"+endY);
        //只有当开始、结束位置变化时，进行回收和复用
        if (startIndex != this.startIndex) {
            detachOrReattachView(this.startIndex, startIndex, true);
            this.startIndex = startIndex;
        }
        if (endIndex != this.endIndex) {
            detachOrReattachView(this.endIndex, endIndex, false);
            this.endIndex = endIndex;
        }

    }

    /**
     * @param indexBefore
     * @param indexAfter
     */
    void detachOrReattachView(int indexBefore, int indexAfter, boolean isStart) {
        //说明不是滑动调用的，不处理
        if (indexBefore == -1 || indexAfter == -1) {
            return;
        }
        //接下来执行会越界
        if (indexAfter >= viewCache.size()) {
            indexAfter=viewCache.size()-1;
        }
        if (indexBefore == indexAfter) {
           return;
        }
        //向下滑动
        if (indexBefore < indexAfter) {
            //因为手指滑动过快并且itemHeight比较小的情况下
            //indexAfter会比indexBefore大许多
            //会导致视图bug，因为少attchItem
            if (isStart) {
                for (int i = indexBefore; i < indexAfter; i++) {
                    detachView(viewCache.get(i));
                }
            } else {
                //重新给child放置
                View before = viewCache.get(indexBefore);
//                Log.i("index","before-->"+indexBefore+"\tafter-->"+indexAfter);
                //before和after的index差太多的话
                //需要依次将相差的部分全部添加上去
                int tempStart = before.getBottom();
                for (int i = indexBefore+1; i <= indexAfter; i++) {
                    View view = viewCache.get(i);
                    attachView(view);
                    layoutDecorated(view, 0, tempStart, itemWidth,
                            tempStart + itemHeight);
                    tempStart += itemHeight;
                }
            }
            //向上滑动
        } else {
            if (isStart) {
                View before = viewCache.get(indexBefore);
                int tempStart = before.getTop();
                for (int i = indexBefore-1; i >= indexAfter; i--) {
                    View view = viewCache.get(i);
                    attachView(view);
                    layoutDecorated(view, 0, tempStart-itemHeight, itemWidth,
                            tempStart);
                    tempStart -= itemHeight;
                }
            } else {
                for (int i = indexBefore; i > indexAfter; i--) {
                    detachView(viewCache.get(i));
                }
            }
        }
    }

    /**
     * @return
     */
    int getVerticalSpace() {
        return getHeight() - getPaddingTop() - getPaddingBottom();
    }

}

package com.work.jsy.jiaobao2.circledemo.widgets.videolist.visibility.calculator;


/**
 * This is basic interface for Visibility calculator.
 * Methods of it strongly depends on Scroll events from ListView or RecyclerView
 *
 * @author Wayne
 */
public interface ListItemsVisibilityCalculator {
    void onScrolled(int scrollState);
    void onScrollStateIdle();
}

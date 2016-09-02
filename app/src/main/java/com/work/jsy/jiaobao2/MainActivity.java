package com.work.jsy.jiaobao2;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewTreeObserver;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.work.jsy.jiaobao2.adapters.MainViewPagertAdapter;
import com.work.jsy.jiaobao2.circledemo.activity.FriendFragment;
import com.work.jsy.jiaobao2.fragments.FifthFragment;
import com.work.jsy.jiaobao2.fragments.FirstFragment;
import com.work.jsy.jiaobao2.fragments.SecondFragment;
import com.work.jsy.jiaobao2.fragments.ThirdFragment;
import com.work.jsy.jiaobao2.util.KeyBoardUtil;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private final static String TAG="MainActivity";
    private RadioButton tv_first, tv_sec, tv_third, tv_fourth, tv_fifth;
    private ViewPager mViewPager;
    public static int mBottomHeight;
    public static RadioGroup rg_bottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        getBottomHeight();

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
//这是为什么
        //add
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void getBottomHeight() {
        ViewTreeObserver vto = rg_bottom.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                rg_bottom.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mBottomHeight=rg_bottom.getHeight();
                Log.i(TAG+"height",mBottomHeight+"");
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            mViewPager.getCurrentItem();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * initViews
     */
    private void initViews() {
        rg_bottom=(RadioGroup) findViewById(R.id.rg_bottom);
        tv_first = (RadioButton) findViewById(R.id.first);
        tv_sec = (RadioButton) findViewById(R.id.second);
        tv_third = (RadioButton) findViewById(R.id.third);
        tv_fourth = (RadioButton) findViewById(R.id.fourth);
        tv_fifth = (RadioButton) findViewById(R.id.fifth);
        mViewPager = (ViewPager) findViewById(R.id.fragment_main);
        addFragments(mViewPager);

    }

    /**
     * addFragments
     */
    private void addFragments(ViewPager viewPager) {
        MainViewPagertAdapter adapter = new MainViewPagertAdapter(this, viewPager);
        adapter.addTitle(tv_first, FirstFragment.class, null);
        adapter.addTitle(tv_sec, SecondFragment.class, null);
        adapter.addTitle(tv_third, ThirdFragment.class, null);
        adapter.addTitle(tv_fourth, FriendFragment.class, null);
        adapter.addTitle(tv_fifth, FifthFragment.class, null);
        adapter.notifyDataSetChanged();
        tv_first.setChecked(true);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        KeyBoardUtil.ShouldHideKeyboard(this, ev);
        return super.dispatchTouchEvent(ev);
    }
}

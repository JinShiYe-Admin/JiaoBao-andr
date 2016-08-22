package com.work.jsy.jiaobao2;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.work.jsy.jiaobao2.adapters.MainViewPagertAdapter;
import com.work.jsy.jiaobao2.fragments.FifthFragment;
import com.work.jsy.jiaobao2.fragments.FirstFragment;
import com.work.jsy.jiaobao2.fragments.ForthFragment;
import com.work.jsy.jiaobao2.fragments.SecondFragment;
import com.work.jsy.jiaobao2.fragments.ThirdFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    private TextView tv_first,tv_sec,tv_third,tv_fourth,tv_fifth;
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
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

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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
    private void initViews(){
        tv_first=(TextView)findViewById(R.id.first);
        tv_sec=(TextView)findViewById(R.id.second);
        tv_third=(TextView)findViewById(R.id.third);
        tv_fourth=(TextView)findViewById(R.id.fourth);
        tv_fifth=(TextView)findViewById(R.id.fifth);
        mViewPager=(ViewPager)findViewById(R.id.fragment_main);
        addFragments(mViewPager);
    }
    /**
     * addFragments
     */
    private void addFragments(ViewPager viewPager){
        MainViewPagertAdapter adapter=new MainViewPagertAdapter(this,viewPager);
        adapter.addTitle(tv_first, FirstFragment.class,null);
        adapter.addTitle(tv_sec, SecondFragment.class,null);
        adapter.addTitle(tv_third, ThirdFragment.class,null);
        adapter.addTitle(tv_fourth, ForthFragment.class,null);
        adapter.addTitle(tv_fifth, FifthFragment.class,null);
        adapter.notifyDataSetChanged();
        tv_first.setFocusable(true);
        tv_first.setFocusableInTouchMode(true);
        tv_first.requestFocus();
    }
}

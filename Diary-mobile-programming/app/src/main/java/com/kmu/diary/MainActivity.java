package com.kmu.diary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;  //버전 맞춰서 import
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.kmu.diary.R;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    Fragment selectedFragment = new homeFragment();
    DrawerLayout side_navigation;
    BottomNavigationView bottom_navigation;
    ActionBarDrawerToggle toggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        side_navigation = (DrawerLayout) findViewById(R.id.main_drawer);

        toggle = new ActionBarDrawerToggle(this,side_navigation,R.string.open, R.string.close);

        toggle.syncState();

           // 메뉴 버튼
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

        bottom_navigation = (BottomNavigationView) findViewById(R.id.navigation);
        bottom_navigation.setOnNavigationItemSelectedListener(bottomNavSelectedListener);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.setting,menu);

        return true;
    }

    //추가된 소스, ToolBar에 추가된 항목의 select 이벤트를 처리하는 함수
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);

        if(item.getItemId() == R.id.action_setting){
            // User chose the "Settings" item, show the app settings UI...
            Intent intent = new Intent(getApplicationContext(), settingActivity.class);
            startActivity(intent);
            return true;
        }
        else if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        else{
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
            return super.onOptionsItemSelected(item);

        }
    }

    // bottom_navigation selected 이벤트를 처리해주는 함수
    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        // 화면에 글자 띄워줌 (하단바 말고 주화면에)
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new homeFragment();
                    break;
                case R.id.navigation_calendar:
                    selectedFragment = new calendarFragment();
                    break;
                case R.id.navigation_search:
                    selectedFragment = new searchFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };


}

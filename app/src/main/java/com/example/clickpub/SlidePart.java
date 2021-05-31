package com.example.clickpub;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SlidePart extends AppCompatActivity {

    private ViewPager screenPager;
    IntroViewPagerAdapter introViewPagerAdapter;
    TabLayout tabIndicator;
    Button btnNext , btnGetstarted;
    int position = 0;
    Animation btnAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
     /*   if(restorePrefData())
        {
            Intent intent = new Intent(getApplicationContext(),Intro.class);
            startActivity(intent);
            finish();
        }*/
        setContentView(R.layout.activity_slide_part);

        btnNext = findViewById(R.id.btn_next);
        btnGetstarted = findViewById(R.id.btn_get_started);
        tabIndicator = findViewById(R.id.tab_indicator);
        btnAnim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);

        List<ScreenItem> mList = new ArrayList<>();
        mList.add(new ScreenItem("Publish announce","Find many announce and get what you want Find many announce and get what you want",R.drawable.buy));
        mList.add(new ScreenItem("Put your announce","You want to make an announce too, Find many announce and get what you want",R.drawable.add));
        mList.add(new ScreenItem("Manage your announce","Find many announce and get what you want wantFind many announce and get what you want",R.drawable.edit));

        screenPager = findViewById(R.id.screen_viewpager);
        introViewPagerAdapter = new IntroViewPagerAdapter(this,mList);
        screenPager.setAdapter(introViewPagerAdapter);

        tabIndicator.setupWithViewPager(screenPager);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                position = screenPager.getCurrentItem();
                if(position < mList.size())
                {
                    position++;
                    screenPager.setCurrentItem(position);
                }
                if(position == mList.size())
                {
                     loadLastScreen();
                }
            }
        });

        tabIndicator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                  if(tab.getPosition() == mList.size() -1)
                  {
                      loadLastScreen();
                  }

              }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        btnGetstarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Intro.class);
                startActivity(intent);
                //to know that the users already checked the intro screen
               // savePrefsData();
                finish();
            }
        });
    }

    /*private boolean restorePrefData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs",MODE_PRIVATE);
        Boolean isSlidePartOpnendBefore = pref.getBoolean("isSlidePartOpnend",false);
        return isSlidePartOpnendBefore;
    }

    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isSlidePartOpnend",true);
        editor.commit();
    }
*/

    private void loadLastScreen() {
        btnNext.setVisibility(View.INVISIBLE);
        btnGetstarted.setVisibility(View.VISIBLE);
        tabIndicator.setVisibility(View.INVISIBLE);
        btnGetstarted.setAnimation(btnAnim);
    }
}
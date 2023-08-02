package com.example.jalihara;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jalihara.TabAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class AboutUsActivity extends AppCompatActivity{

    TextView header;
    TabLayout tabLayoutAboutUs;
    ViewPager2 movePage;

    TabAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);


        header = findViewById(R.id.title);
        String username = ((UsernameGlobal) this.getApplication()).getUsername();
        header.setText("Get to know us!");


        // Navigation
        View toolbar = findViewById(R.id.toolbar);
        ImageView hamburger_bar = findViewById(R.id.hamburger_bar);
        View base_layout = findViewById(R.id.navigation_base);
        ViewGroup navigation_menu = findViewById(R.id.navigation_menu);
        View about_us_layout = findViewById(R.id.about_us_layout);
        hamburger_bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (navigation_menu.getVisibility() == View.VISIBLE){
                    navigation_menu.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(),
                            R.anim.slide_up
                    ));
                    navigation_menu.setVisibility(View.GONE);

                } else {
                    navigation_menu.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(),
                            R.anim.slide_down
                    ));
                    navigation_menu.setVisibility(View.VISIBLE);
                }
            }
        });
        base_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (navigation_menu.getVisibility() == View.VISIBLE){
                    navigation_menu.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(),
                            R.anim.slide_up
                    ));
                    navigation_menu.setVisibility(View.GONE);
                }
            }
        });
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (navigation_menu.getVisibility() == View.VISIBLE){
                    navigation_menu.startAnimation(AnimationUtils.loadAnimation(
                            getApplicationContext(),
                            R.anim.slide_up
                    ));
                    navigation_menu.setVisibility(View.GONE);
                }
            }
        });

        TextView home_btn = findViewById(R.id.menu_home);
        TextView tickets_btn = findViewById(R.id.menu_tickets);
        TextView about_us_btn = findViewById(R.id.menu_about_us);
        TextView log_out_btn = findViewById(R.id.menu_log_out);
        home_btn.setOnClickListener(v -> {
            Intent intent = new Intent(AboutUsActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tickets_btn.setOnClickListener(v -> {
            Intent intent = new Intent(AboutUsActivity.this, TicketsActivity.class);
            startActivity(intent);
        });
        about_us_btn.setOnClickListener(v -> {
            Intent intent = new Intent(AboutUsActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });
        log_out_btn.setOnClickListener(v -> {
            Intent intent = new Intent(AboutUsActivity.this, LoginActivity.class);
            startActivity(intent);
        });



        tabLayoutAboutUs = findViewById(R.id.tabLayout);
        movePage = findViewById(R.id.viewPager);
        adapter = new TabAdapter(getSupportFragmentManager(), getLifecycle());

        movePage.setAdapter(adapter);

        tabLayoutAboutUs.addTab(tabLayoutAboutUs.newTab().setText("About Us"));
        tabLayoutAboutUs.addTab(tabLayoutAboutUs.newTab().setText("Contact Us"));

        tabLayoutAboutUs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                movePage.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        movePage.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayoutAboutUs.selectTab(tabLayoutAboutUs.getTabAt(position));
            }
        });


    }

}


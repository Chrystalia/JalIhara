package com.example.jalihara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemAdapter.buttonClickListener, ItemHomeAdapter.buttonClickListener {

    ViewFlipper viewFlipper;
    TextView header;
    RecyclerView recyclerView;
    ItemHomeAdapter itemHomeAdapter;
    public List<Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // daniel
        header = findViewById(R.id.title);
        String username = ((UsernameGlobal) this.getApplication()).getUsername();
        header.setText("Welcome, " + username);
        // end daniel

        // nadya
        int image[] = {R.drawable.slideone, R.drawable.slidetwo, R.drawable.slidethree};

        viewFlipper = findViewById(R.id.viewFlipperHome);
//        for(int i = 0; i < image.length; i++){
//            flipperImages(image[i]);
//        }
        for(int images: image){
            flipperImages(images);
        }
        // end nadya

        itemList = new ArrayList<>();
        itemList.add(new Item("0", R.drawable.ticket1, "⏱ 10-11 Maret 2023 ", "Jogja Violin Vest #4", "Start your 2023 by visiting the Jogja Violin Festival 2023, while supporting the revival of Indonesia's tourism and creative economy!✨", "IDR 10.000"));
        itemList.add(new Item("1", R.drawable.ticket2, "⏱ 1-3 September 2023", "SyncFest 2023", "Synchronize Festival brings local musicians from the 60s to the 2000s. Experience the nostalgic feelings by attending the festival and enjoying the music of the past decades.", "IDR 550.000"));
        itemList.add(new Item("2", R.drawable.ticket3, "⏱ 19 September 2019", "SACF 2023", "SACF (Surabaya Art Cultural Festival), located at Surabaya City Square, starts at 6:00 PM WIB (Western Indonesian Time) and showcases various traditional and modern arts.", "IDR 5.000"));
        itemList.add(new Item("3", R.drawable.ticket4, "⏱ 7 Agustus 2023", "Barong Performance", "Unlike common barong performances in the area, the antics of the comic reliefs  can be quite hilarious.", "IDR 150.000"));
        itemList.add(new Item("4", R.drawable.ticket5, "⏱ 22-24 September 2023", "Pestapora 2023", "Tens of thousands of visitors, hundreds of guest stars, and various exciting activities provide a different experience for a music celebration.", "IDR 600.000"));
        TicketList.getInstance().setItemList(itemList);

        // Navigation
        View toolbar = findViewById(R.id.toolbar);
        ImageView hamburger_bar = findViewById(R.id.hamburger_bar);
        View base_layout = findViewById(R.id.navigation_base);
        ViewGroup navigation_menu = findViewById(R.id.navigation_menu);
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
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tickets_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TicketsActivity.class);
            startActivity(intent);
        });
        about_us_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });
        log_out_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
        itemList = new ArrayList<>();
        // add data to item list
        itemHomeAdapter = new ItemHomeAdapter(TicketList.getInstance().getItemList(), this);
        recyclerView.setAdapter((itemHomeAdapter));
    }

    public void flipperImages(int images){
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(images);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(1000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this,android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this,android.R.anim.slide_out_right);

    }

    @Override
    public void onButtonClick(int position) {
        Intent intent = new Intent(MainActivity.this, TicketsActivity.class);
        startActivity(intent);
    }
}
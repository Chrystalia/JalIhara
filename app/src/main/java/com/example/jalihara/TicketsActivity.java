package com.example.jalihara;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TicketsActivity extends AppCompatActivity implements ItemAdapter.buttonClickListener{

    TextView header;
    RecyclerView recyclerView;
    ItemAdapter itemAdapter;
    SearchView searchView;
    private List<Item> itemList;

    @NonNull
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);

        header = findViewById(R.id.title);
        String username = ((UsernameGlobal) this.getApplication()).getUsername();
        header.setText("Events");

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
            Intent intent = new Intent(TicketsActivity.this, MainActivity.class);
            startActivity(intent);
        });
        tickets_btn.setOnClickListener(v -> {
            Intent intent = new Intent(TicketsActivity.this, TicketsActivity.class);
            startActivity(intent);
        });
        about_us_btn.setOnClickListener(v -> {
            Intent intent = new Intent(TicketsActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });
        log_out_btn.setOnClickListener(v -> {
            Intent intent = new Intent(TicketsActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        // Search Bar
        searchView = findViewById(R.id.search_view);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
        itemList = new ArrayList<>();
        // add data to item list
        itemAdapter = new ItemAdapter(TicketList.getInstance().getItemList(), this);
        recyclerView.setAdapter((itemAdapter));
    }

    private void filterList(String newText) {
        List<Item> filteredList = new ArrayList<>();
        for(Item item : TicketList.getInstance().getItemList()){
            if(item.getItemTitle().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(item);
            }
        }
        if(filteredList.isEmpty()){
            Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
        } else {
            itemAdapter.setFilteredList(filteredList, this);
        }
    }

    @Override
    public void onButtonClick(int position) {
        Intent intent = new Intent(TicketsActivity.this, TicketFormActivity.class);
        String id =itemAdapter.getItemList().get(position).getItemId();
        intent.putExtra("title", TicketList.getInstance().getItemList().get((Integer.parseInt(id))).getItemTitle());
        startActivity(intent);
    }
}
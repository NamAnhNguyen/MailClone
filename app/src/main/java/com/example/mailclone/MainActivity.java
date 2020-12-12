package com.example.mailclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.mailclone.adapters.Email;
import com.example.mailclone.adapters.EmailLayoutAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.bloco.faker.Faker;

public class MainActivity extends AppCompatActivity {
    ArrayList<Email> items;
    ListView listView;
    ActionBar actionBar;
    SearchView searchView;

    MenuItem favoriteButton;
    MenuItem allMailButton;
    SimpleDateFormat f = new SimpleDateFormat("YYYY/MM/dd HH:mm");

    Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Faker faker = new Faker();
        Log.v("aaaaa", "aaaaaaaaa");
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_item);
        registerForContextMenu(listView);
        listView.setLongClickable(true);
        items = new ArrayList<Email>();
        for (int i = 0; i < 10; i++) {
            items.add(new Email(faker.internet.email(), faker.lorem.sentence(), faker.lorem.paragraph(2), f.format(faker.date.forward()), faker.bool.bool()));
        }
        EmailLayoutAdapter adapter = new EmailLayoutAdapter(items, this);
        ctx = this;
        listView.setAdapter(adapter);
        actionBar = getActionBar();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; add items to the action bar
        getMenuInflater().inflate(R.menu.main, menu);

        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                ArrayList<Email> newItems = new ArrayList<Email>();

                for (Email mail : items) {
                    if (mail.email.contains(query) || mail.subject.contains(query))
                        newItems.add(mail);
                }

                EmailLayoutAdapter adapter = new EmailLayoutAdapter(newItems, ctx);
                listView.setAdapter(adapter);

                searchView.setQuery("", false);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        favoriteButton = (MenuItem) menu.findItem(R.id.action_favorite);
        favoriteButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                ArrayList<Email> newItems = new ArrayList<Email>();

                for (Email mail : items) {
                    if (mail.checked == true)
                        newItems.add(mail);
                }

                EmailLayoutAdapter adapter = new EmailLayoutAdapter(newItems, ctx);
                listView.setAdapter(adapter);
                return false;
            }
        });


        allMailButton = (MenuItem) menu.findItem(R.id.action_allMail);
        allMailButton.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                EmailLayoutAdapter adapter = new EmailLayoutAdapter(items, ctx);
                listView.setAdapter(adapter);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId() == R.id.list_item) {
            menu.setHeaderTitle("Select action");
            menu.add(Menu.NONE, 0, 0, "Delete");
            menu.add(Menu.NONE, 1, 0, "Reply");
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 0) {
            Toast.makeText(getApplicationContext(), "Delete", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == 1) {
            Toast.makeText(getApplicationContext(), "Reply", Toast.LENGTH_LONG).show();
        }
        return super.onContextItemSelected(item);
    }
}
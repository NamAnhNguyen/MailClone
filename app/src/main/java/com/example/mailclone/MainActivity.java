package com.example.mailclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.mailclone.adapters.Email;
import com.example.mailclone.adapters.EmailLayoutAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Email> items;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.list_item);
        items = new ArrayList<Email>();
        for (int i = 0; i < 10; i++) {
            items.add(new Email("abc@xyz.com", "Hi" + i, "Xin chao Ciao Bonjour Hello" + i, "12", "a", false));
        }
        EmailLayoutAdapter adapter = new EmailLayoutAdapter(items, this);
        listView.setAdapter(adapter);
    }
}
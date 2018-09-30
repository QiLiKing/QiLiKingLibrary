package com.qlk.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;

import com.qlk.lib.recycler.RecyclerManager;

public class MainActivity extends AppCompatActivity {
    private RecyclerManager<UserInfo> mRecyclerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mRecyclerManager = RecyclerManager.with(this,recyclerView).
    }
}

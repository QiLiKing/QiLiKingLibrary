package com.qlk.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.SubMenu;
import android.view.WindowManager;

import com.qlk.frozen.recycler.RecyclerManager;

public class MainActivity extends AppCompatActivity {
    private RecyclerManager<UserInfo> mRecyclerManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_AppCompat_Light_NoActionBar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu subMenu = menu.addSubMenu("1111");
        subMenu.add("1111");
        subMenu = menu.addSubMenu("2222");
        subMenu.add("2222");
        return super.onCreateOptionsMenu(menu);
    }
}

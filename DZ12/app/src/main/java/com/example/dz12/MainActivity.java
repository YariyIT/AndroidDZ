package com.example.dz12;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar materialToolbar = findViewById(R.id.material_toolbar);       // Создаём переменную для обращения к полю -material_toolbar-
        setSupportActionBar(materialToolbar);

        NavigationView navigationView = findViewById(R.id.navigation_view);       // Создаём переменную для обращения к полю -navigation_view-
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);       // Создаём переменную для обращения к полю -drawer_layout-

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(MainActivity.this, drawerLayout, materialToolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        replaceFragment(new PullUpsPage());

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.pull_ups){
                    replaceFragment(new PullUpsPage());
                    drawerLayout.closeDrawer(GravityCompat.START);       // Чтобы закрывалось меню при нажатии
                } else if (item.getItemId()==R.id.push_ups) {
                    replaceFragment(new PushUpsPage());
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId()==R.id.squats) {
                    replaceFragment(new SquatsPage());
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else if (item.getItemId()==R.id.run) {
                    replaceFragment(new RunPage());
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });

    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}
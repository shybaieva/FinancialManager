package net.shybaieva.financialmanager;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavigBar);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new StatisticFragment()).commit();
    }

   private BottomNavigationView.OnNavigationItemSelectedListener navListener =
           new BottomNavigationView.OnNavigationItemSelectedListener() {
               @Override
               public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                   Fragment fragment = null;

                   switch (item.getItemId()){
                       case R.id.dashboard:{
                           fragment = new StatisticFragment();
                           break;
                       }
                       case R.id.income:{
                           fragment = new IncomeFragment();
                           break;
                       }
                       case R.id.expense:{
                           fragment = new ExpenseFragment();
                           break;
                       }
                   }

                   getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).commit();

                   return true;
               }
           };
}

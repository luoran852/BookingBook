package com.example.bookingbook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView botNav;
    HomeFragment homeFragment = new HomeFragment();
    SearchFragment searchFragment = new SearchFragment();
    RankingFragment rankingFragment = new RankingFragment();
    MyPageFragment myPageFragment = new MyPageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.mainFragment, homeFragment).commitAllowingStateLoss();
        botNav = findViewById(R.id.botNavMain);

        botNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        transaction.replace(R.id.mainFragment, homeFragment).commitAllowingStateLoss();
                        break;
                    case R.id.nav_search:
                        transaction.replace(R.id.mainFragment, searchFragment).commitAllowingStateLoss();
                        break;
                    case R.id.nav_ranking:
                        transaction.replace(R.id.mainFragment, rankingFragment).commitAllowingStateLoss();
                        break;
                    case R.id.nav_my:
                        transaction.replace(R.id.mainFragment, myPageFragment).commitAllowingStateLoss();
                        break;
                }

                return true;
            }
        });
    }

    @Override
    protected void onDestroy() {                            // 액티비티를 종료하면 호출, finsh() 또는 휴대폰 뒤로가기 버튼
        super.onDestroy();
        SharedPreferences sharedPreferences = getSharedPreferences("login", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        String name = sharedPreferences.getString("login","noname");
        if(!name.equals("noname"))
        {
            SharedPreferences pref = getSharedPreferences("login", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            editor.commit();
            Toast.makeText(this, "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}
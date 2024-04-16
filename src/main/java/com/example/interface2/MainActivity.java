package com.example.interface2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private List<Map<String, Object>> data;
    private List<String> applicants = new ArrayList<>();
    private List<String> scores = new ArrayList<>();
    private List<String> priorities = new ArrayList<>();
    private List<String> profiles = new ArrayList<>();
    private List<Boolean> isChecked = new ArrayList<>();
    private boolean isScoreFiltered = false;
    private boolean isPriorityFiltered = false;
    private static final int YOUR_REQUEST_CODE = 1;
    private List<Map<String, Object>> applicantData = new ArrayList<>();
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        data = new ArrayList<>();
        initAdapterData(); // Перенос initAdapterData() и добавление данных

        adapter = new SimpleAdapter(this, data, R.layout.znac_item, new String[]{"applicant", "score", "priority", "profile", "checked"},
                new int[]{R.id.nameTextView, R.id.scoreTextView, R.id.priorityTextView, R.id.profileTextView, R.id.checkBox});
        listView.setAdapter(adapter);
        setupClickListeners(); // Передача адаптера в метод setupClickListeners

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_filter_by_status) {
                    // Обработка нажатия на "Фильтр по статусу"
                } else if (id == R.id.nav_filter_by_score) {
                    // Обработка нажатия на "Фильтр по ЕГЭ"
                } else if (id == R.id.nav_filter_by_priority) {
                    // Обработка нажатия на "Фильтр по приоритету"
                } else if (id == R.id.nav_add_applicant) {
                    Intent intent = new Intent(MainActivity.this, AddApplicantActivity.class);
                    startActivityForResult(intent, YOUR_REQUEST_CODE);
                }

                drawerLayout.closeDrawer(GravityCompat.START); // Закрываем боковое меню
                return true;
            }
        });
    }

    private void setupClickListeners() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> clickedItem = (Map<String, Object>) adapter.getItem(position);
                String applicantName = (String) clickedItem.get("applicant");

                Intent intent = new Intent(MainActivity.this, AddApplicantActivity.class);
                intent.putExtra("clickedApplicant", applicantName);
                startActivity(intent);
            }
        });
    }

    private void initAdapterData() {
        // Заполнение списка данных (предположим, что applicants, scores, priorities, profiles, isChecked - это ваши списки)
        for (int i = 0; i < applicants.size(); i++) {
            Map<String, Object> listItem = new HashMap<>();
            listItem.put("applicant", applicants.get(i));
            listItem.put("score", scores.get(i));
            listItem.put("priority", priorities.get(i));
            listItem.put("profile", profiles.get(i));
            listItem.put("checked", isChecked.get(i));
            data.add(listItem);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnedData) {
        super.onActivityResult(requestCode, resultCode, returnedData);

        if (requestCode == YOUR_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (returnedData != null && returnedData.hasExtra("newApplicantData")) {
                String newApplicantData = returnedData.getStringExtra("newApplicantData");

                String[] splitData = newApplicantData.split(" ");
                if (splitData.length >= 7) {
                    String fullName = splitData[0] + " " + splitData[1] + " " + splitData[2];
                    String phoneNumber = splitData[3];
                    int egeScore = Integer.parseInt(splitData[4]);
                    int priority = Integer.parseInt(splitData[5]);
                    String profile = splitData[6];

                    // Форматирование ФИО
                    String[] splitFullName = fullName.split(" ");
                    if (splitFullName.length == 3) {
                        fullName = splitFullName[0] + " " + splitFullName[1] + " " + splitFullName[2];
                    }

                    // Добавление нового аппликанта к списку
                    Map<String, Object> newApplicant = new HashMap<>();
                    newApplicant.put("applicant", fullName);
                    newApplicant.put("phone", phoneNumber);
                    newApplicant.put("score", egeScore);
                    newApplicant.put("priority", priority);
                    newApplicant.put("profile", profile);
                    newApplicant.put("checked", false);

                    data.add(newApplicant); // Обновление списка данных

                    // Обновление адаптера
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void filterByStatus() {
        // Создаем новый список данных на основе текущего состояния чекбоксов
        List<Map<String, Object>> filteredData = new ArrayList<>();

        for (Map<String, Object> item : data) {
            boolean isChecked = (boolean) item.get("checked");
            if (isChecked) {
                filteredData.add(item);
            }
        }

        for (Map<String, Object> item : data) {
            boolean isChecked = (boolean) item.get("checked");
            if (!isChecked) {
                filteredData.add(item);
            }
        }

        // Обновляем данные и обновляем адаптер
        data.clear();
        data.addAll(filteredData);
        adapter.notifyDataSetChanged();
    }

    private void filterByScore() {
        if (!isScoreFiltered) {
            Collections.sort(data, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    return Integer.parseInt(String.valueOf(o2.get("score"))) - Integer.parseInt(String.valueOf(o1.get("score")));
                }
            });
            isScoreFiltered = true;
        } else {
            Collections.sort(data, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    return Integer.parseInt(String.valueOf(o1.get("score"))) - Integer.parseInt(String.valueOf(o2.get("score")));
                }
            });
            isScoreFiltered = false;
        }
        adapter.notifyDataSetChanged();
    }

    private void filterByPriority() {
        if (!isPriorityFiltered) {
            Collections.sort(data, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    return Integer.parseInt(String.valueOf(o1.get("priority"))) - Integer.parseInt(String.valueOf(o2.get("priority")));
                }
            });
            isPriorityFiltered = true;
        } else {
            Collections.sort(data, new Comparator<Map<String, Object>>() {
                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    return Integer.parseInt(String.valueOf(o2.get("priority"))) - Integer.parseInt(String.valueOf(o1.get("priority")));
                }
            });
            isPriorityFiltered = false;
        }
        adapter.notifyDataSetChanged();
    }
}
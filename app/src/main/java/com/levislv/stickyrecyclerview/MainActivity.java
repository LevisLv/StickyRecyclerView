package com.levislv.stickyrecyclerview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.levislv.stickyrecyclerview.widget.Model;
import com.levislv.stickyrecyclerview.widget.StickyRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private StickyRecyclerView contactRecyclerView;

    private List<Model> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        modelList = new ArrayList<>();
        String[] letters = getResources().getStringArray(R.array.letters);
        for (int i = 0, length = letters.length; i < length; i++) {
            for (int j = 1; j < 5; j++) {
                Model model = new Model(letters[i], letters[i].toLowerCase() + j);
                modelList.add(model);
            }
        }
    }

    private void initView() {
        contactRecyclerView = findViewById(R.id.contact_recycler_view);
        contactRecyclerView.setDataSource(modelList);
    }
}

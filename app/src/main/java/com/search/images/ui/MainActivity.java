package com.search.images.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.search.images.R;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            return;
        }

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ImageFragment fragment = new ImageFragment();
        transaction.replace(R.id.imageFragment, fragment);
        transaction.commit();
    }
}

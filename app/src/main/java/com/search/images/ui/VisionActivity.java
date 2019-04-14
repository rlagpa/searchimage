package com.search.images.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.search.images.R;
import com.search.images.config.Constants;

import butterknife.ButterKnife;

public class VisionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
        setContentView(R.layout.activity_vision);

        if(savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            VisionFragment fragment = VisionFragment.newInstance(getIntent().getStringExtra(Constants.EXTRA_IMAGE_URL),
                    getIntent().getBooleanExtra(Constants.EXTRA_HORIZONTAL_IMAGE, true));
            transaction.replace(R.id.visionFragment, fragment);
            transaction.commit();
        }
    }

}

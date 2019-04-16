package com.search.images.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.search.images.R;
import com.search.images.config.Constants;
import com.search.images.di.DaggerSearchImageComponent;
import com.search.images.di.SearchImageModule;
import com.search.images.model.vision.Face;
import com.search.images.model.vision.Vision;
import com.search.images.service.SearchService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.stream.Collectors;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisionFragment extends Fragment {

    @Inject
    SearchService searchService;

    @BindView(R.id.image_vision)
    protected ImageView image;
    @BindView(R.id.text_vision_info)
    protected TextView text;

    private String imageUrl;

    public static VisionFragment newInstance(String imageUrl) {
        VisionFragment fragment = new VisionFragment();
        Bundle arg = new Bundle();
        arg.putString(Constants.Bundle.IMAGE_URL, imageUrl);
        fragment.setArguments(arg);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSearchImageComponent.builder()
                .searchImageModule(new SearchImageModule(getContext()))
                .build().inject(this);

        //noinspection ConstantConditions
        imageUrl = getArguments().getString(Constants.Bundle.IMAGE_URL);
        loadData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vision_result, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(getContext()).load(imageUrl).into(image);
    }

    public void loadData() {
        if (TextUtils.isEmpty(imageUrl)) {
            return;
        }

        searchService.getVision(imageUrl);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onVisionResult(Vision result) {
        if (result.isInValid()) {
            Toast.makeText(getContext(), getString(R.string.msg_not_avaliable), Toast.LENGTH_SHORT).show();
            return;
        }

        text.setText(
                result.getResult().getFaces().stream()
                        .map(face -> face.toString(getString(R.string.format_age), getString(R.string.format_gender), getString(R.string.format_accuracy)))
                        .collect(Collectors.joining("\n")));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.Bundle.IMAGE_URL, imageUrl);
    }

}

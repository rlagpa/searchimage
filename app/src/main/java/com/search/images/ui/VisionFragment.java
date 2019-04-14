package com.search.images.ui;

import android.os.Bundle;
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
import com.search.images.model.FaceVO;
import com.search.images.model.VisionVO;
import com.search.images.service.network.HttpResponseListener;
import com.search.images.service.network.SearchService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class VisionFragment extends Fragment implements HttpResponseListener<VisionVO> {

    @Inject
    SearchService searchService;

    @BindView(R.id.image_vision)
    protected ImageView image;
    @BindView(R.id.text_vision)
    protected TextView text;

    private String imageUrl;
    private boolean isHorizontal;

    public static VisionFragment newInstance(String imageUrl, boolean isHorizontal) {
        VisionFragment fragment = new VisionFragment();
        Bundle arg = new Bundle();
        arg.putString(Constants.BUNDLE_IMAGE_URL, imageUrl);
        arg.putBoolean(Constants.BUNDLE_HORIZONTAL_IMAGE, isHorizontal);
        fragment.setArguments(arg);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSearchImageComponent.builder()
                .searchImageModule(new SearchImageModule(getContext()))
                .build().inject(this);

        imageUrl = getArguments().getString(Constants.BUNDLE_IMAGE_URL);
        isHorizontal = getArguments().getBoolean(Constants.BUNDLE_HORIZONTAL_IMAGE);
        loadData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vision_result, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Glide.with(getContext()).load(imageUrl).into(image);
    }

    public void loadData() {
        if(TextUtils.isEmpty(imageUrl)) {
            return;
        }

        searchService.getImageVision(this, imageUrl);
    }


    @Override
    public void onSuccess(Response<VisionVO> response) {
        if (response.body() == null || response.body().getResult().getFaces().size() == 0) {
            Toast.makeText(getContext(), getString(R.string.msg_not_avaliable), Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder builder = new StringBuilder();
        for(FaceVO face : response.body().getResult().getFaces()) {
            setResult(builder, face);
        }

        text.setText(builder.toString());
    }


    public void setResult(StringBuilder builder, FaceVO face) {
        int age = Math.round(face.getFacial_attributes().getAge());
        boolean isFemale = (face.getFacial_attributes().getGender().getFemale() < face.getFacial_attributes().getGender().getMale())? false:  true;
        int accuracy = Math.round(face.getScore() * 100);

        builder.append(getString(R.string.text_age, age));
        builder.append("   ");
        builder.append(getString(R.string.text_gender, (isFemale)? getString(R.string.female) : getString(R.string.male)));
        builder.append("   ");
        builder.append(getString(R.string.text_accuracy, accuracy));
        builder.append("\n");
    }

    @Override
    public void onFail() {
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.BUNDLE_IMAGE_URL, imageUrl);
    }

}

package com.search.images.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.search.images.R;
import com.search.images.config.Constants;
import com.search.images.model.DocumentVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageRecyclerAdapter extends RecyclerView.Adapter<ImageRecyclerAdapter.SearchHolder> {
    private Context context;
    private List<DocumentVO> voList = new ArrayList<>();

    public ImageRecyclerAdapter(Context context) {
        this.context = context;
    }

    public void initializeList() {
        voList.clear();
    }

    public void setData(List<DocumentVO> voList) {
        this.voList.addAll(voList);
        notifyDataSetChanged();
    }

    @Override
    public SearchHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchHolder holder, final int position) {
        final DocumentVO item = voList.get(position);

        if (item == null) {
            return;
        }

        holder.bind(item);
    }

    @Override
    public int getItemViewType(int position) {
        if (voList.get(position).width > voList.get(position).height) {
            return R.layout.item_card_horizontal;
        } else {
            return R.layout.item_card_vertical;
        }
    }

    @Override
    public int getItemCount() {
        return voList == null ? 0 : voList.size();
    }

    public class SearchHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_display) ImageView imageView;

        public SearchHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }

        void bind(DocumentVO item) {
            Glide.with(context).load(item.image_url).into(imageView);
        }

        @OnClick(R.id.image_display)
        void onClick() {
            int position = getLayoutPosition();
            DocumentVO item = voList.get(position);
            boolean isHorizontal = (item.width > item.height)? true : false;

            Intent intent = new Intent(context, VisionActivity.class);
            intent.putExtra(Constants.EXTRA_IMAGE_URL, item.image_url);
            intent.putExtra(Constants.EXTRA_HORIZONTAL_IMAGE, isHorizontal);
            context.startActivity(intent);
        }
    }
}

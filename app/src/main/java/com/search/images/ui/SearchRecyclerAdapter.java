package com.search.images.ui;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.search.images.R;
import com.search.images.config.Constants;
import com.search.images.model.search.Document;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.SearchHolder> {
    private Context context;
    private List<Document> voList = new ArrayList<>();

    public SearchRecyclerAdapter(Context context) {
        this.context = context;
    }

    void initializeList() {
        voList.clear();
    }

    void setData(List<Document> voList) {
        this.voList.addAll(voList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchHolder holder, final int position) {
        final Document item = voList.get(position);

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

    class SearchHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_display) ImageView imageView;

        SearchHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
        }

        void bind(Document item) {
            Glide.with(context).load(item.image_url).into(imageView);
        }

        @OnClick(R.id.image_display)
        void onClick() {
            int position = getLayoutPosition();
            Document item = voList.get(position);

            Intent intent = new Intent(context, VisionActivity.class);
            intent.putExtra(Constants.Intent.EXTRA_IMAGE_URL, item.image_url);
            context.startActivity(intent);
        }
    }
}

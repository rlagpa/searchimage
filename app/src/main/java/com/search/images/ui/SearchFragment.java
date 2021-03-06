package com.search.images.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.search.images.R;
import com.search.images.config.Constants;
import com.search.images.di.DaggerSearchImageComponent;
import com.search.images.di.SearchImageModule;
import com.search.images.model.search.SearchResult;
import com.search.images.service.SearchService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchFragment extends Fragment {

    @Inject
    SearchRecyclerAdapter adapter;
    @Inject
    SearchService searchService;

    @BindView(R.id.recycler_search)
    protected SearchRecyclerView recyclerView;
    @BindView(R.id.text_search)
    protected EditText textSearch;

    LinearLayoutManager layoutManager;
    int nextPageNum = 1;
    boolean isLastPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSearchImageComponent.builder()
                .searchImageModule(new SearchImageModule(getContext()))
                .build().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_recyclerview, container, false);
        ButterKnife.bind(this, view);

        if(savedInstanceState != null) {
            textSearch.setText(savedInstanceState.getString(Constants.Bundle.QUERY));
            loadData();
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.initAdapter(adapter);
    }

    @OnClick(R.id.button_search)
    void onClick() {
        initailize();
        loadData();
        hideKeyboard();
    }

    public void initailize() {
        nextPageNum = 1;
        adapter.initializeList();
    }

    public void loadData() {
        String query = textSearch.getText().toString();
        if (TextUtils.isEmpty(query)) {
            return;
        }

        searchService.search(query, nextPageNum);
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
        hideKeyboard();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearchResult(SearchResult result) {
        if (result.isInvalid()) {
            Toast.makeText(getContext(), getString(R.string.msg_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        isLastPage = result.getMeta().is_end();
        nextPageNum++;
        recyclerView.setData(result);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.Bundle.QUERY, textSearch.getText().toString());
    }

    @SuppressWarnings("ConstantConditions")
    private void hideKeyboard() {
        getContext();
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        final int THRESHOLD = 5;

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            int totalItemCount = layoutManager.getItemCount();
            int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
            boolean isReachedBottom = lastVisiblePosition + THRESHOLD >= totalItemCount;

            if (totalItemCount <= 0 || isLastPage) {
                return;
            }
            if (isReachedBottom) {
                loadData();
            }

        }
    };
}

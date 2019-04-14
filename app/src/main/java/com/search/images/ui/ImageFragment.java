package com.search.images.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
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
import com.search.images.model.SearchResultVO;
import com.search.images.service.network.HttpResponseListener;
import com.search.images.service.network.SearchService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Response;

public class ImageFragment extends Fragment implements HttpResponseListener<SearchResultVO> {

    @Inject
    ImageRecyclerAdapter adapter;
    @Inject
    SearchService searchService;

    @BindView(R.id.recycler_images)
    protected ImageRecyclerView recyclerView;
    @BindView(R.id.text_search)
    protected EditText textSearch;

    LinearLayoutManager layoutManager;
    int pageNum = 1;
    boolean isLastPage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerSearchImageComponent.builder()
                .searchImageModule(new SearchImageModule(getContext()))
                .build().inject(this);

        if(savedInstanceState != null) {
            textSearch.setText(savedInstanceState.getString(Constants.BUNDLE_QUERY));
            loadData();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        hideKeyboard();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image_recyclerview, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.initAdapter(adapter);
    }

    @OnClick(R.id.button_search)
    void onClick() {
        Log.e("ham", "loadData");
        initailize();
        loadData();
        hideKeyboard();
    }

    public void initailize() {
        searchService.pageNumGenerator.set(1);
        adapter.initializeList();
    }

    public void loadData() {
        String query = textSearch.getText().toString();
        if (TextUtils.isEmpty(query)) {
            return;
        }

        Log.e("ham", "getSearchList");
        searchService.getSearchList(this, query, pageNum);
    }

    @Override
    public void onSuccess(Response<SearchResultVO> response) {
        if (response.body() == null) {
            Toast.makeText(getContext(), getString(R.string.msg_not_found), Toast.LENGTH_SHORT).show();
            return;
        }

        isLastPage = response.body().getMeta().getIsEnd();
        pageNum = response.body().getMeta().getPageNum();
        recyclerView.setData(response.body());
    }

    @Override
    public void onFail() {
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(Constants.BUNDLE_QUERY, textSearch.getText().toString());
    }

    private void hideKeyboard() {
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getContext().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        int previousTotal;
        int firstVisibleItem;
        int visibleItemCount;
        int totalItemCount;
        int visibleThreshold = 5;
        boolean loading = true;

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            if (isLastPage) {
                return;
            }

            visibleItemCount = recyclerView.getChildCount();
            totalItemCount = layoutManager.getItemCount();
            firstVisibleItem = layoutManager.findFirstVisibleItemPosition();

            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }
            if (!loading && (totalItemCount - visibleItemCount)
                    <= (firstVisibleItem + visibleThreshold)) {

                loadData();

                loading = true;
            }
        }
    };

}

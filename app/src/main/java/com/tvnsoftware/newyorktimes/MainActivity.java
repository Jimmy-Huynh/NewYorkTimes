package com.tvnsoftware.newyorktimes;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.tvnsoftware.newyorktimes.Api.CommonInterface;
import com.tvnsoftware.newyorktimes.Api.RetrofitManager;
import com.tvnsoftware.newyorktimes.Api.restservice.ArticalService;
import com.tvnsoftware.newyorktimes.Utils.Contant;
import com.tvnsoftware.newyorktimes.adapter.ArticleAdapter;
import com.tvnsoftware.newyorktimes.dialog.DialogSetting;
import com.tvnsoftware.newyorktimes.model.Article;
import com.tvnsoftware.newyorktimes.model.Doc;
import com.tvnsoftware.newyorktimes.model.Response;
import com.tvnsoftware.newyorktimes.model.SetingModel;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    protected ProgressDialog progress;
    private ArticleAdapter mAdapter;
    private StaggeredGridLayoutManager mGridLayoutManager;

    private String mQ = "";
    private String mFq = "";
    private String mBeginDate = "";
    private String mEndDate = "";
    private String mSort = "";
    private String mFl = "";
    private String mHl = "";
    private int mPage = 0;
    private String mFacetField = "";
    private String mFacetFilter = "";

    @BindView(R.id.rcArticle)
    RecyclerView rcArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RetrofitManager.getInstance().config(getApplicationContext());
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        setUpUI();
        getAPI();
    }

    private void setUpUI() {
        mAdapter = new ArticleAdapter(MainActivity.this);
        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        mGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        // Attach the layout manager to the recycler view
        rcArticles.setLayoutManager(mGridLayoutManager);
        rcArticles.setAdapter(mAdapter);
        mAdapter.setLoadMore(new ArticleAdapter.Listener() {
            @Override
            public void loadMore() {
                getAPI();
            }
        });

        mAdapter.setClickOnItem(new ArticleAdapter.ClickOnItem() {
            @Override
            public void clickOn(Doc doc) {
                Intent i = new Intent(MainActivity.this, ArticleDetailActivity.class);
                i.putExtra(Contant.DOC_WEB, doc.getWebUrl());
                startActivity(i);
            }
        });
    }

    private void getAPI() {
        showLoading();
        Map<String, String> data = new HashMap<>();
        data.put("api-key", Contant.API_KEY);
        if (!mQ.isEmpty()) data.put("q", mQ);
        if (!mBeginDate.isEmpty()) data.put("begin_date", mBeginDate);
        if (!mSort.isEmpty()) data.put("sort", mSort);
        data.put("page", mPage + "");

        ArticalService service = new ArticalService();
        service.setRequest(data);
        service.request(this, new CommonInterface.ModelResponse<Article>() {
            @Override
            public void onSuccess(Article result) {
                if (null != result) {
                    hideLoading();
                    if (0 == mPage) {
                        mAdapter.setData(result.getResponse().getDocs());
                    } else {
                        mAdapter.addData(result.getResponse().getDocs());
                    }

                    mPage++;


                } else {

                }
            }

            @Override
            public void onFail() {
                hideLoading();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        final MenuItem seattingItem = menu.findItem(R.id.action_settings);
        seattingItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                DialogSetting dialog = new DialogSetting(MainActivity.this, new DialogSetting.Listener() {
                    @Override
                    public void onButtonClick(SetingModel setingModel) {
                        if (null != setingModel) {
                            mSort = setingModel.getSort();
                            mBeginDate = setingModel.getBeginDate();
                            mPage = 0;
                            getAPI();
                        }
                    }
                });
                dialog.showDialog();
                return true;
            }
        });
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                mPage = 0;
                mQ = query;
                getAPI();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    protected void showLoading() {
        if (!progress.isShowing() && isNetworkAvailable()) {
            progress.show();
        }
    }

    protected void hideLoading() {
        if (progress.isShowing()) {
            progress.dismiss();
        }
    }

}

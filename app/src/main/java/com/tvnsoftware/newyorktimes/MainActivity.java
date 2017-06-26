package com.tvnsoftware.newyorktimes;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import com.tvnsoftware.newyorktimes.Api.CommonInterface;
import com.tvnsoftware.newyorktimes.Api.RetrofitManager;
import com.tvnsoftware.newyorktimes.Api.restservice.ArticalService;
import com.tvnsoftware.newyorktimes.Utils.Contant;
import com.tvnsoftware.newyorktimes.adapter.ArticleAdapter;
import com.tvnsoftware.newyorktimes.model.Article;
import com.tvnsoftware.newyorktimes.model.Response;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

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
        getAPI();
    }

    private void getAPI() {
        Map<String, String> data = new HashMap<>();
        data.put("api-key", Contant.API_KEY);
        ArticalService service = new ArticalService();
        service.setRequest(data);
        service.request(this, new CommonInterface.ModelResponse<Article>() {
            @Override
            public void onSuccess(Article result) {
                if (null != result) {
                    Log.d("tamHH", result.getResponse().getDocs().size() + "");
                    ArticleAdapter adapter = new ArticleAdapter(MainActivity.this);

                    rcArticles.setAdapter(adapter);
                } else {

                }
            }

            @Override
            public void onFail() {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}

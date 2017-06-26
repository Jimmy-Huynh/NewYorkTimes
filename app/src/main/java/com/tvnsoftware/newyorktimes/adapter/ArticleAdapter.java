package com.tvnsoftware.newyorktimes.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tvnsoftware.newyorktimes.R;
import com.tvnsoftware.newyorktimes.model.Doc;
import com.tvnsoftware.newyorktimes.model.Multimedia;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by TamHH on 6/26/2017.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleAdapterViewHolder> {
    private List<Doc> mDocs;
    private Context mContext;
    private Listener mListener;
    private ClickOnItem mClickOnItem;

    public interface Listener {
        public void loadMore();
    }

    public interface ClickOnItem {
        public void clickOn(Doc doc);
    }

    @Override
    public ArticleAdapter.ArticleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_article, parent, false);
        return new ArticleAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.ArticleAdapterViewHolder holder, int position) {
        final Doc doc = mDocs.get(position);
        holder.tvTitle.setText(doc.getSnippet());
        holder.tvContents.setText(doc.getLeadParagraph());
        if (null != doc.getMultimedia()) {
            if (doc.getMultimedia().size() > 0) {
//                Multimedia multimedia = doc.getMultimedia().get(0);
//                int iWidth = getWidthScreen()/2;
//                int iHeight = iWidth
                Glide.with(mContext).load(doc.getMultimedia().get(0).getUrl()).into(holder.ivCover);
            }

        }
        if (position == mDocs.size() - 1) {
            mListener.loadMore();
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickOnItem.clickOn(doc);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDocs.size();
    }

    public class ArticleAdapterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.ivCover)
        ImageView ivCover;

        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvContents)
        TextView tvContents;
        @BindView(R.id.cardViewMain)
        CardView cardView;
        public ArticleAdapterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public ArticleAdapter(Context context) {
        mContext = context;
        mDocs = new ArrayList<>();
    }

    public void setData(List<Doc> docs) {
        mDocs = docs;
        notifyDataSetChanged();
    }

    public void addData(List<Doc> docs) {
        mDocs.addAll(docs);
        notifyDataSetChanged();
    }

    private int getWidthScreen() {
        Configuration configuration = mContext.getResources().getConfiguration();
        return configuration.screenWidthDp;
    }

    public void setLoadMore(Listener listener) {
        mListener = listener;
    }

    public void setClickOnItem(ClickOnItem clickOnItem) {
        mClickOnItem = clickOnItem;
    }
}

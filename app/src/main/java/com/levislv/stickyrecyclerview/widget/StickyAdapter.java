package com.levislv.stickyrecyclerview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.levislv.stickyrecyclerview.R;

import java.util.List;

public class StickyAdapter extends RecyclerView.Adapter<StickyAdapter.ViewHolder> {

    public static final String BUNDLE_KEY_HAS_STICKY_VIEW = "has_sticky_view";
    public static final String BUNDLE_KEY_LETTER = "letter";
    public static final String BUNDLE_KEY_PRE_LETTER = "pre_letter";

    private Context mContext;
    private List<Model> mList;

    public StickyAdapter(Context context) {
        mContext = context;
    }

    /**
     * 只供{@link StickyRecyclerView}调用，其他地方不调
     *
     * @param list
     */
    public void setDataSource(List<Model> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public Model getItem(int position) {
        return mList == null || position >= mList.size() ? null : mList.get(position);
    }

    @SuppressLint("InflateParams")
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_contact, null));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int position) {
        final Model model = getItem(position);
        if (model != null) {
            viewHolder.tvTitle.setText(model.getLetter());
            viewHolder.tvContent.setText(model.getName());
            String letter = model.getLetter();
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_KEY_LETTER, letter);
            if (position == 0) {
                viewHolder.layoutTitle.setVisibility(View.GONE);
                bundle.putBoolean(BUNDLE_KEY_HAS_STICKY_VIEW, false);
                bundle.putString(BUNDLE_KEY_PRE_LETTER, letter);
            } else {
                String preLetter = getItem(position - 1).getLetter();
                bundle.putString(BUNDLE_KEY_PRE_LETTER, preLetter);
                if (!TextUtils.equals(letter, preLetter)) {
                    viewHolder.layoutTitle.setVisibility(View.VISIBLE);
                    bundle.putBoolean(BUNDLE_KEY_HAS_STICKY_VIEW, true);
                } else {
                    viewHolder.layoutTitle.setVisibility(View.GONE);
                    bundle.putBoolean(BUNDLE_KEY_HAS_STICKY_VIEW, false);
                }
            }
            viewHolder.itemView.setTag(bundle);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private View layoutTitle;
        private TextView tvTitle;
        private TextView tvContent;

        ViewHolder(View view) {
            super(view);
            layoutTitle = view.findViewById(R.id.layout_title);
            tvTitle = view.findViewById(R.id.tv_title);
            tvContent = view.findViewById(R.id.tv_content);
        }
    }
}

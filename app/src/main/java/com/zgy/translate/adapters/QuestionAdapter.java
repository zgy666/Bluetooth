package com.zgy.translate.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zgy.translate.R;

import java.util.List;

/**
 * Created by zhouguangyue on 2017/12/19.
 */

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder> {

    private Context mContext;
    private List<Integer> helpList;

    public QuestionAdapter(Context context, List<Integer> helpList){
        mContext = context;
        this.helpList = helpList;
    }

    @Override
    public QuestionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new QuestionViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_question, parent, false));
    }

    @Override
    public void onBindViewHolder(QuestionViewHolder holder, int position) {
        holder.iv.setBackground(mContext.getDrawable(helpList.get(position)));
    }

    @Override
    public int getItemCount() {
        return helpList.size();
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder{
        //TextView title, content;
        ImageView iv;

        private QuestionViewHolder(View itemView) {
            super(itemView);
            //title = itemView.findViewById(R.id.iq_tv_title);
            //content = itemView.findViewById(R.id.iq_tv_content);
            iv = itemView.findViewById(R.id.iq_iv);
        }
    }

}

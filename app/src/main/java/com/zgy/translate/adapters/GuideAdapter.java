package com.zgy.translate.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zgy.translate.R;
import com.zgy.translate.adapters.interfaces.GuideAdapterInterface;

import java.util.List;

/**
 * Created by zhouguangyue on 2018/4/9.
 */

public class GuideAdapter extends RecyclerView.Adapter<GuideAdapter.GuideViewHolder> {

    private Context mContext;
    private List<Integer> guideList;
    private GuideAdapterInterface adapterInterface;

    public GuideAdapter(Context context, List<Integer> guideList, GuideAdapterInterface adapterInterface){
        mContext = context;
        this.guideList = guideList;
        this.adapterInterface = adapterInterface;
    }

    @Override
    public GuideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new GuideViewHolder(LayoutInflater.from(mContext)
        .inflate(R.layout.item_question, parent, false));
    }

    @Override
    public void onBindViewHolder(GuideViewHolder holder, int position) {
        if(position == guideList.size()){
            adapterInterface.endIndex();
        }
        else{
            holder.iv.setBackground(mContext.getDrawable(guideList.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        return guideList.size() + 1;
    }

    class GuideViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;

        private GuideViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iq_iv);
        }
    }

}

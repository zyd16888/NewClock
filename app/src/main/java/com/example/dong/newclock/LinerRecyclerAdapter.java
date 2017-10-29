package com.example.dong.newclock;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dong on 2017/10/11.
 */

public class LinerRecyclerAdapter extends RecyclerView.Adapter<LinerRecyclerHolder>{
    private Context mContext;
    private List<String> mDatas;

    public LinerRecyclerAdapter(Context context,List<String> Datas){
        mContext = context;
        mDatas = Datas;

    }



    @Override
    public LinerRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinerRecyclerHolder holder = new LinerRecyclerHolder(LayoutInflater.from(mContext).inflate(R.layout.stop_watch_item,parent,false));
        return holder;

    }

    @Override
    public void onBindViewHolder(LinerRecyclerHolder holder, int position) {
        holder.tv.setText(mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void clean(){
        mDatas.clear();
    }
}


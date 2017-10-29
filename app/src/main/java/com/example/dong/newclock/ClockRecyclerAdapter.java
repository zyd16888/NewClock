package com.example.dong.newclock;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by dong on 2017/10/11.
 */

class ClockRecyclerAdapter extends RecyclerView.Adapter<ClockRecyclerHolder> {

    private Context mcontext;
    private List<ClockItem> mDatas;
    private ClockFragement clockFragement;
    private OnItemOnClickListener monItemOnClickListener;

   public interface OnItemOnClickListener{
       void onItemOnClick(View view, int position);
       void onItemLongOnClick(View view,int position);
   }

    public void setOnItemClickListener(OnItemOnClickListener listener) {
        this.monItemOnClickListener = listener;
    }



    public ClockRecyclerAdapter(Context context, List<ClockItem> Datas, com.example.dong.newclock.ClockFragement clockFragment){
        mcontext = context;
        mDatas = Datas;
        this.clockFragement=clockFragment;
    }

    @Override
    public ClockRecyclerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ClockRecyclerHolder holder = new ClockRecyclerHolder(clockFragement, LayoutInflater.from(
                mcontext).inflate(R.layout.clock_item,parent,false));
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(final ClockRecyclerHolder holder,final int position) {
        String time = mDatas.get(position).getClockItemTime().get(java.util.Calendar.HOUR_OF_DAY) +":"+mDatas.get(position).getClockItemTime().get(java.util.Calendar.MINUTE);
        String repeat=mDatas.get(position).getClockItemDate();
        if(monItemOnClickListener!=null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    monItemOnClickListener.onItemOnClick(holder.itemView, position);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    monItemOnClickListener.onItemLongOnClick(holder.itemView, position);
                    return false;
                }
            });
        }
        holder.clockItemTime.setText(time);
        holder.clockItemRepeat.setText(repeat);
        holder.clockItemSwitch.setChecked(mDatas.get(position).getClockItemSwitch());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    public ClockItem getItem(int position){
        position = Math.max(0,position);
        return  mDatas.get(position);
    }
    public void addData(ClockItem newItems){
        if (newItems !=null){
            mDatas.add(newItems);
        }
    }

    public void  deleteItem(int positon){
        mDatas.remove(positon);
        notifyItemRemoved(positon);
        //notifyDataSetChanged();
    }

}

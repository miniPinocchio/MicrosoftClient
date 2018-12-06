package com.microsoft.dapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.microsoft.bean.PropertyBean;
import com.microsoft.microsoftclient.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Created by huiliu on 2018/12/6.
 * @email liu594545591@126.com
 * @introduce
 */
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder> {
    private Context mContext;
    private List<PropertyBean> mListBeans;

    public TaskListAdapter(Context context, List<PropertyBean> listBeans) {
        mContext = context;
        mListBeans = listBeans;
    }

    @NonNull
    @Override
    public TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_property_detail, parent, false);
        return new TaskListViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListViewHolder holder, int position) {
        PropertyBean propertyBean = mListBeans.get(position);

        holder.mTvPropertyTime.setText(propertyBean.getUpdated_at());
        holder.mTvPropertyTitle.setText(propertyBean.getTask_name());
        holder.mTvPropertyGold.setText(propertyBean.getTb());
//        holder.mTvPropertyNumbers.setText(propertyBean.get);//流水号
    }

    @Override
    public int getItemCount() {
        return mListBeans.size() > 0 ? mListBeans.size() : 0;
    }

    class TaskListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_property_title)
        TextView mTvPropertyTitle;
        @BindView(R.id.tv_property_time)
        TextView mTvPropertyTime;
        @BindView(R.id.tv_property_gold)
        TextView mTvPropertyGold;
        @BindView(R.id.tv_property_numbers)
        TextView mTvPropertyNumbers;

         TaskListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

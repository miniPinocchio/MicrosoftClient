package com.microsoft.dapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.microsoft.bean.DyListBean;
import com.microsoft.microsoftclient.R;

import java.util.List;

/**
 * Created by huiliu on 2018/11/19.
 *
 * @email liu594545591@126.com
 * @introduce
 */
public class TaskDyListAdapter extends RecyclerView.Adapter<TaskDyListAdapter.TaskViewHolder> {
    private Context mContext;
    private List<DyListBean> mDyListBeans;
    private View.OnClickListener mOnClickListener;

    public TaskDyListAdapter(Context context, List<DyListBean> dyListBeans, View.OnClickListener onClickListener) {
        mContext = context;
        mDyListBeans = dyListBeans;
        mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_task_dy_list, parent, false);
        return new TaskViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        DyListBean dyListBean = mDyListBeans.get(position);
        holder.tv_title.setText(dyListBean.getTitle());
        holder.tv_reward.setText(dyListBean.getBonus());
        holder.rl_dy_item.setOnClickListener(mOnClickListener);
        holder.rl_dy_item.setTag(position);
        //TODO 接口缺少参数
//        holder.tv_person_num.setText(dyListBean.getBonus());
//        holder.tv_title.setText(dyListBean.getTitle());
    }

    @Override
    public int getItemCount() {
        return mDyListBeans.size() > 0 ? mDyListBeans.size() : 0;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rl_dy_item;
        ImageView iv_header;
        TextView tv_title;
        TextView tv_person_num;
        TextView tv_reward;
        TextView tv_reward_rmb;

        public TaskViewHolder(View itemView) {
            super(itemView);
            iv_header = itemView.findViewById(R.id.iv_dy_head);
            tv_title = itemView.findViewById(R.id.tv_dy_task_name);
            tv_person_num = itemView.findViewById(R.id.tv_dy_join_person);
            tv_reward = itemView.findViewById(R.id.tv_dy_task_reward);
            tv_reward_rmb = itemView.findViewById(R.id.tv_dy_task_rmb);
            rl_dy_item = itemView.findViewById(R.id.rl_item_dy_task);
        }
    }
}

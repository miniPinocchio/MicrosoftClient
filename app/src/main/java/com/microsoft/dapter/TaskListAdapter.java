package com.microsoft.dapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by huiliu on 2018/11/19.
 *
 * @email liu594545591@126.com
 * @introduce
 */
public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskViewHolder> {


    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_header;
        TextView tv_title;
        TextView tv_person_num;
        TextView tv_reward;

        public TaskViewHolder(View itemView) {
            super(itemView);

        }
    }
}

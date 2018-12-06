package com.microsoft.dapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.microsoft.microsoftclient.R;

import java.util.List;

/**
 * @author Created by huiliu on 2018/12/6.
 * @email liu594545591@126.com
 * @introduce
 */
public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    private Context mContext;
    private List<String> mStrings;

    public CustomerAdapter(Context context, List<String> strings) {
        mContext = context;
        mStrings = strings;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.item_customer_center,parent,false);
        return new CustomerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder {

        public CustomerViewHolder(View itemView) {
            super(itemView);
        }
    }
}

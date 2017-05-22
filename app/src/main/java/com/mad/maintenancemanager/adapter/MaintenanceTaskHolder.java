package com.mad.maintenancemanager.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.mad.maintenancemanager.R;

public class MaintenanceTaskHolder extends RecyclerView.ViewHolder {
    private final TextView mName, mDescription, mCreatorId, mAssignedTo, mItems;
    private final ImageView mTaskType;
    private final CardView mCardView;

    public MaintenanceTaskHolder(View itemView) {
        super(itemView);
        mName = (TextView) itemView.findViewById(R.id.task_name);
        mDescription = (TextView) itemView.findViewById(R.id.task_desc);
        mCreatorId = (TextView) itemView.findViewById(R.id.task_creator_id);
        mTaskType = (ImageView) itemView.findViewById(R.id.task_type);
        mAssignedTo = (TextView) itemView.findViewById(R.id.task_assignee);
        mCardView = (CardView) itemView.findViewById(R.id.task_card_view);
        mItems = (TextView) itemView.findViewById(R.id.task_items);
    }

    public void setName(String name) {
        mName.setText(name);
    }

    public void setDescription(String text) {
        mDescription.setText(text);
    }

    public void setCreatorId(String text) {
        mCreatorId.setText(text);
    }

    public void setTaskType(boolean taskType) {
        if (taskType) {
            mTaskType.setImageResource(R.drawable.ic_internal_task);
        } else {
            mTaskType.setImageResource(R.drawable.ic_external_task);
        }
    }

    public void setItems(String items) {

        mItems.setText(items);
    }

    public void setItemsVisibility() {
        if (mItems.getVisibility() == View.GONE) {
            mItems.setVisibility(View.VISIBLE);
        } else {
            mItems.setVisibility(View.GONE);
        }
    }

    public void setAssignee(String assignee) {
         mAssignedTo.setText(assignee);
    }

    public void setLongClick(View.OnLongClickListener longClick) {
        mCardView.setOnLongClickListener(longClick);
    }

    public void setClick(View.OnClickListener tap) {
        mCardView.setOnClickListener(tap);
    }


}


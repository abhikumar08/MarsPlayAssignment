package com.luezoid.marsplayassignment.Base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import java.util.List;

/**
 * Created by abhishek on 28/08/17.
 */

public abstract class BaseRecyclerViewAdapter<T, V extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<V> {

  protected List<T> mList;
  protected Context context;

  public BaseRecyclerViewAdapter(List<T> mList, Context context) {
    this.mList = mList;
    this.context = context;
  }

  @Override public int getItemCount() {
    return mList == null ? 0 : mList.size();
  }

  public void setData(List<T> mList) {
    this.mList = mList;
    notifyDataSetChanged();
  }
}

package com.luezoid.marsplayassignment.home.viewImages;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.luezoid.marsplayassignment.Base.BaseRecyclerViewAdapter;
import com.luezoid.marsplayassignment.R;
import java.util.List;

/**
 * Created by Abhishek Kumar (akabhikumar08@gmail.com) on 08/10/18.
 */
public class ImageListAdapter extends BaseRecyclerViewAdapter<String, ImageListItemViewHolder> {

  public ImageListAdapter(List<String> mList, Context context) {
    super(mList, context);
  }

  @NonNull @Override
  public ImageListItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
    View view = inflater.inflate(R.layout.item_image_list, viewGroup, false);
    return new ImageListItemViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ImageListItemViewHolder imageListItemViewHolder, int i) {
    imageListItemViewHolder.bindData(mList.get(i));
  }
}

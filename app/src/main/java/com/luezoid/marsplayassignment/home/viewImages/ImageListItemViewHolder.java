package com.luezoid.marsplayassignment.home.viewImages;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import com.luezoid.marsplayassignment.R;
import com.luezoid.marsplayassignment.home.viewImages.events.ImageSelectedEvent;
import com.squareup.picasso.Picasso;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by Abhishek Kumar (akabhikumar08@gmail.com) on 08/10/18.
 */
public class ImageListItemViewHolder extends RecyclerView.ViewHolder {
  private ImageView imageView;

  public ImageListItemViewHolder(View itemView) {
    super(itemView);
    imageView = itemView.findViewById(R.id.imageView);
  }

  public void bindData(String imageUrl) {
    Picasso.with(imageView.getContext()).load(imageUrl).into(imageView);
    itemView.setOnClickListener(
        view -> EventBus.getDefault().post(new ImageSelectedEvent(imageUrl)));
  }
}

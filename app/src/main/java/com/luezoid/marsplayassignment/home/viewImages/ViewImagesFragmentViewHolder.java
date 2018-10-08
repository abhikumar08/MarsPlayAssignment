package com.luezoid.marsplayassignment.home.viewImages;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import com.github.chrisbanes.photoview.PhotoView;
import com.luezoid.marsplayassignment.Base.BaseViewHolder;
import com.luezoid.marsplayassignment.R;
import com.luezoid.marsplayassignment.di.ComponentFactory;
import com.luezoid.marsplayassignment.di.S3Helper;
import com.luezoid.marsplayassignment.home.viewImages.events.ImageSelectedEvent;
import com.luezoid.marsplayassignment.home.viewImages.events.ImagesFetchedEvent;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Abhishek Kumar (akabhikumar08@gmail.com) on 08/10/18.
 */
public class ViewImagesFragmentViewHolder extends BaseViewHolder<List<String>> {

  private RecyclerView recyclerView;
  private ProgressBar progressBar;
  private PhotoView zoomableImageView;
  private CardView enalrgedImage;
  private ImageButton closeButton;
  private ImageListAdapter adapter;

  @Inject S3Helper s3Helper;

  public ViewImagesFragmentViewHolder(View view, @Nullable List<String> data, Context context) {
    super(view, data, context);
  }

  @Override protected void setUpComponent() {
    ComponentFactory.getInstance().getNetComponent().inject(this);
  }

  @Override protected void attachListeners(List<String> data) {

  }

  @Override public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override public void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Override protected void initializeView(@NonNull List<String> data) {
    recyclerView = view.findViewById(R.id.imagesRecyclerView);
    progressBar = view.findViewById(R.id.progressBar);
    enalrgedImage = view.findViewById(R.id.enlargedIargedImageView);
    zoomableImageView = view.findViewById(R.id.zoomableImageView);
    closeButton = view.findViewById(R.id.closeButton);
    closeButton.setOnClickListener(view -> {
      enalrgedImage.setVisibility(View.GONE);
    });

    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    recyclerView.setLayoutManager(linearLayoutManager);

    if (adapter == null) {
      progressBar.setVisibility(View.VISIBLE);
      adapter = new ImageListAdapter(data, context);
      AsyncTask.execute(() -> {
        ArrayList<String> images = s3Helper.getImages();
        EventBus.getDefault().post(new ImagesFetchedEvent(images));
      });
    }
    recyclerView.setAdapter(adapter);
  }

  @Subscribe(threadMode = ThreadMode.MAIN) public void onEvent(ImageSelectedEvent event) {
    enalrgedImage.setVisibility(View.VISIBLE);
    Picasso.with(zoomableImageView.getContext())
        .load(event.getSelectedImageUrl())
        .into(zoomableImageView);
  }

  @Override public void setData(@NonNull List<String> data) {
    super.setData(data);
    progressBar.setVisibility(View.GONE);
    adapter.setData(data);
  }
}

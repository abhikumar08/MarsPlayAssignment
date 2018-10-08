package com.luezoid.marsplayassignment.home.viewImages;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.luezoid.marsplayassignment.Base.BaseFragment;
import com.luezoid.marsplayassignment.R;
import com.luezoid.marsplayassignment.home.viewImages.events.ImagesFetchedEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Abhishek Kumar (akabhikumar08@gmail.com) on 08/10/18.
 */
public class ViewImagesFragment extends BaseFragment<ViewImagesFragmentViewHolder> {

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return setupUI(inflater, container, R.layout.fragment_view_imges, savedInstanceState);
  }

  @Override public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override public void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onImagesFetchedEvent(ImagesFetchedEvent event) {
    viewHolder.setData(event.getImages());
  }

  @Override protected ViewImagesFragmentViewHolder setupViewHolder(View view) {
    return new ViewImagesFragmentViewHolder(view, null, context);
  }

  @Override protected void setUpComponent() {

  }
}

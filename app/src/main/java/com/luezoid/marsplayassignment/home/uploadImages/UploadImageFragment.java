package com.luezoid.marsplayassignment.home.uploadImages;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.luezoid.marsplayassignment.Base.BaseFragment;
import com.luezoid.marsplayassignment.R;
import com.luezoid.marsplayassignment.home.events.HomeActivityEvents;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Abhishek Kumar (akabhikumar08@gmail.com) on 08/10/18.
 */
public class UploadImageFragment extends BaseFragment<UploadImageFragmentViewHolder> {

  @Override protected UploadImageFragmentViewHolder setupViewHolder(View view) {
    Bundle bundle = getArguments();
    Uri imageUri = Uri.parse(bundle.getString("uri"));
    return new UploadImageFragmentViewHolder(view, imageUri, getContext());
  }


  @Override protected void setUpComponent() {

  }

  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return setupUI(inflater, container, R.layout.fragment_upload_image, savedInstanceState);
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
  public void onEvent(HomeActivityEvents.ImageUploadedEvent event) {
    getActivity().getSupportFragmentManager().popBackStack();
  }
}


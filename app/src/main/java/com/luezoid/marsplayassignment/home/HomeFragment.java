package com.luezoid.marsplayassignment.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.luezoid.marsplayassignment.R;
import com.luezoid.marsplayassignment.ViewOpenEvent;
import com.luezoid.marsplayassignment.home.events.HomeActivityEvents;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Abhishek Kumar (akabhikumar08@gmail.com) on 08/10/18.
 */
public class HomeFragment extends Fragment {


  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View fragmentView = inflater.inflate(R.layout.fragment_main_fragment, container, false);
    Button uploadNewImageButton = fragmentView.findViewById(R.id.buttonuploadNewImage);
    Button viewImages = fragmentView.findViewById(R.id.viewImagesButton);

    viewImages.setOnClickListener(view -> EventBus.getDefault()
        .post(new ViewOpenEvent(ViewOpenEvent.ViewType.VIEW_IMAGES_FRAGMENT)));

    uploadNewImageButton.setOnClickListener(
        view -> EventBus.getDefault().post(new HomeActivityEvents.UploadNewImageClicked()));

    return fragmentView;
  }


}

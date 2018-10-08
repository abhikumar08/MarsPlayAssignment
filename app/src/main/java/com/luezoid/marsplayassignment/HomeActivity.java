package com.luezoid.marsplayassignment;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.luezoid.marsplayassignment.Base.FilePickerActivity;
import com.luezoid.marsplayassignment.Base.PermissionListener;
import com.luezoid.marsplayassignment.di.ComponentFactory;
import com.luezoid.marsplayassignment.di.S3Helper;
import com.luezoid.marsplayassignment.home.HomeFragment;
import com.luezoid.marsplayassignment.home.events.HomeActivityEvents;
import com.luezoid.marsplayassignment.home.uploadImages.UploadImageFragment;
import com.luezoid.marsplayassignment.home.viewImages.ViewImagesFragment;
import com.yalantis.ucrop.UCrop;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomeActivity extends FilePickerActivity {

  @Inject S3Helper s3Helper;

  @Override protected void setUpComponent() {
    ComponentFactory.getInstance().getNetComponent().inject(this);
  }

  @Override protected void setUpViewHolder(View view) {

  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState==null){
      HomeFragment homeFragment = new HomeFragment();
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      ft.add(R.id.content_frame, homeFragment, HomeFragment.class.getSimpleName()).commit();
    }

  }

  @Override protected void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override protected void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(threadMode = ThreadMode.MAIN) public void onEvent(ViewOpenEvent viewOpenEvent) {
    switch (viewOpenEvent.getViewType()) {
      case VIEW_IMAGES_FRAGMENT:
        ViewImagesFragment uploadImageFragment = new ViewImagesFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, uploadImageFragment,
            UploadImageFragment.class.getSimpleName())
            .addToBackStack(ViewImagesFragment.class.getSimpleName())
            .commit();
        break;
    }
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onEvent(HomeActivityEvents.UploadNewImageClicked event) {

    requestPermissions(Manifest.permission.CAMERA, 4, new PermissionListener() {
      @Override public void onGranted(int requestCode) {
        if (requestCode == 4) {
          String fileName = "IMG" + System.currentTimeMillis();
          selectImage((imageFile, tag) -> {
            Uri uri = Uri.fromFile(imageFile);
            UCrop.of(uri, uri)
                .withAspectRatio(16, 9)
                .withMaxResultSize(720, 1080)
                .start(HomeActivity.this);
          }, fileName);
        }
      }

      @Override public void onRejected(int requestCode) {
        return;
      }
    });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
      final Uri resultUri = UCrop.getOutput(data);
      Bundle bundle1 = new Bundle();
      bundle1.putString("uri", resultUri.toString());
      UploadImageFragment uploadImageFragment = new UploadImageFragment();
      uploadImageFragment.setArguments(bundle1);
      FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
      ft.replace(R.id.content_frame, uploadImageFragment, UploadImageFragment.class.getSimpleName())
          .addToBackStack(UploadImageFragment.class.getSimpleName())
          .commit();
    } else if (resultCode == UCrop.RESULT_ERROR) {
      final Throwable cropError = UCrop.getError(data);
    }
  }

  @Override public void onBackPressed() {
    super.onBackPressed();
  }
}

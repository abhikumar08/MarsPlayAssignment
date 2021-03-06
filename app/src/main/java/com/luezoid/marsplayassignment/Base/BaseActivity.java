package com.luezoid.marsplayassignment.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by abhishek on 27/08/17.
 */

public abstract class BaseActivity extends PermissionsActivity {


  public final String TAG = this.getClass().getSimpleName();

  // to be used to unregister eventbus in onstop
  private boolean isEventBusRegisteredInOnResume = false;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //MyApplication.getInstance().getNetComponent().inject(this);
  }

  protected void setUpUi(int layout, int layoutId) {
    setContentView(layout);
    setUpComponent();
    setUpViewHolder(findViewById(layoutId));
  }

  protected abstract void setUpComponent();

  protected abstract void setUpViewHolder(View view);

  @Override protected void onResume() {
    super.onResume();
    //MyApplication.getInstance().setCurrentActivity(this);
    //if (!EventBus.getDefault().isRegistered(this)) {
    //  EventBus.getDefault().register(this);
    //  isEventBusRegisteredInOnResume = true;
    //}
  }

  @Override protected void onStart() {
    super.onStart();
  }

  @Override protected void onPause() {
    super.onPause();
    //if (EventBus.getDefault().isRegistered(this) && isEventBusRegisteredInOnResume) {
    //  EventBus.getDefault().unregister(this);
    //}
  }

  @Override protected void onStop() {
    super.onStop();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
  }

  @Override public Intent getIntent() {
    return super.getIntent();
  }




}

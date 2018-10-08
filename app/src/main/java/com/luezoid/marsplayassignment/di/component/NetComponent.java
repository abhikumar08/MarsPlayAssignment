package com.luezoid.marsplayassignment.di.component;

import com.luezoid.marsplayassignment.HomeActivity;
import com.luezoid.marsplayassignment.di.modules.AppModule;
import com.luezoid.marsplayassignment.di.modules.NetModule;
import com.luezoid.marsplayassignment.home.uploadImages.UploadImageFragmentViewHolder;
import com.luezoid.marsplayassignment.home.viewImages.ViewImagesFragmentViewHolder;
import dagger.Component;
import javax.inject.Singleton;

/**
 * Created by abhishek on 29/08/17.
 */

@Singleton @Component(modules = { AppModule.class, NetModule.class })
public interface NetComponent {

  void inject(UploadImageFragmentViewHolder uploadImageFragmentViewHolder);

  void inject(HomeActivity homeActivity);

  void inject(ViewImagesFragmentViewHolder viewImagesFragmentViewHolder);
}


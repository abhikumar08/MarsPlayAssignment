package com.luezoid.marsplayassignment.di.modules;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by abhishek on 29/08/17.
 */
@Module public class AppModule {

  Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides @Singleton Application providesApplication() {
    return application;
  }

  @Provides @Singleton Context providesContext(Application application){
    return application.getApplicationContext();
  }
}

package com.luezoid.marsplayassignment;

import android.app.Application;
import com.luezoid.marsplayassignment.di.ComponentFactory;

/**
 * Created by Abhishek Kumar (akabhikumar08@gmail.com) on 08/10/18.
 */
public class MarsPlayApp extends Application {

  @Override public void onCreate() {
    super.onCreate();
    ComponentFactory.getInstance().initializeComponents(this);
  }
}

package com.luezoid.marsplayassignment.di;

import com.luezoid.marsplayassignment.MarsPlayApp;
import com.luezoid.marsplayassignment.di.component.DaggerNetComponent;
import com.luezoid.marsplayassignment.di.component.NetComponent;
import com.luezoid.marsplayassignment.di.modules.AppModule;
import com.luezoid.marsplayassignment.di.modules.NetModule;

/**
 * Created by Abhishek Kumar (akabhikumar08@gmail.com) on 08/03/18.
 */

public class ComponentFactory {

  private static ComponentFactory componentFactory;

  private NetComponent netComponent;

  //Factory Initializer
  public static ComponentFactory getInstance() {
    if (componentFactory == null) {
      componentFactory = new ComponentFactory();
    }
    return componentFactory;
  }

  public void initializeComponents(MarsPlayApp application) {
    netComponent = DaggerNetComponent.builder()
        .appModule(new AppModule(application))
        .netModule(new NetModule())
        .build();
  }

  public NetComponent getNetComponent() {
    return netComponent;
  }

}

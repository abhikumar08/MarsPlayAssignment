package com.luezoid.marsplayassignment;

/**
 * Created by Abhishek Kumar (akabhikumar08@gmail.com) on 08/10/18.
 */
public class ViewOpenEvent {
  public enum ViewType {
    UPLOAD_IMAGE_FRAGMENT, VIEW_IMAGES_FRAGMENT
  }

  private ViewType viewType;

  public ViewOpenEvent(ViewType viewType) {
    this.viewType = viewType;
  }

  public ViewType getViewType() {
    return viewType;
  }
}

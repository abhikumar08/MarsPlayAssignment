package com.luezoid.marsplayassignment.home.viewImages.events;

import java.util.ArrayList;

/**
 * Created by Abhishek Kumar (akabhikumar08@gmail.com) on 08/10/18.
 */
public class ImagesFetchedEvent {
  private ArrayList<String> images;

  public ImagesFetchedEvent(ArrayList<String> images) {
    this.images = images;
  }

  public ArrayList<String> getImages() {
    return images;
  }
}

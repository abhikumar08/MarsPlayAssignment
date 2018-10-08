package com.luezoid.marsplayassignment.home.viewImages.events;

/**
 * Created by Abhishek Kumar (akabhikumar08@gmail.com) on 09/10/18.
 */
public class ImageSelectedEvent {
  private String selectedImageUrl;

  public ImageSelectedEvent(String selectedImageUrl) {
    this.selectedImageUrl = selectedImageUrl;
  }

  public String getSelectedImageUrl() {
    return selectedImageUrl;
  }
}

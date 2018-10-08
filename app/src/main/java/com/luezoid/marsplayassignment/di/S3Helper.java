package com.luezoid.marsplayassignment.di;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Created by Abhishek Kumar (akabhikumar08@gmail.com) on 08/10/18.
 */
public class S3Helper {

  @Inject AmazonS3Client s3client;

  @Inject public S3Helper() {
  }

  public ArrayList<String> getImages() {
    ArrayList<String> images = new ArrayList<>();

    ObjectListing objectListing =
        s3client.listObjects("marsplayassignmentad1e10183c214882a1b5c8d6f4a5a474");
    for (S3ObjectSummary os : objectListing.getObjectSummaries()) {
      if (!os.getKey().endsWith("/")) {
        images.add("https://marsplayassignmentad1e10183c214882a1b5c8d6f4a5a474.s3.amazonaws.com/"
            + os.getKey());
      }
    }

    return images;
  }
}

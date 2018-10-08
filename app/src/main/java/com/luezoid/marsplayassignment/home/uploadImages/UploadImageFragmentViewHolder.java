package com.luezoid.marsplayassignment.home.uploadImages;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.luezoid.marsplayassignment.Base.BaseViewHolder;
import com.luezoid.marsplayassignment.R;
import com.luezoid.marsplayassignment.di.ComponentFactory;
import com.luezoid.marsplayassignment.home.events.HomeActivityEvents;
import java.io.File;
import java.io.IOException;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by Abhishek Kumar (akabhikumar08@gmail.com) on 08/10/18.
 */
public class UploadImageFragmentViewHolder extends BaseViewHolder<Uri> {

  private ImageView imageView;
  private Button uploadButton;
  private ConstraintLayout progressView;
  private ProgressBar progressBar;

  @Inject TransferUtility transferUtility;

  public UploadImageFragmentViewHolder(View view, @Nullable Uri imageUri, Context context) {
    super(view, imageUri, context);
  }

  @Override protected void setUpComponent() {
    ComponentFactory.getInstance().getNetComponent().inject(this);
  }

  @Override protected void attachListeners(Uri uri) {
    uploadButton.setOnClickListener(view -> {
      onUploadClick();
    });
  }

  @Override protected void initializeView(@NonNull Uri uri) {

    imageView = view.findViewById(R.id.imageView);
    uploadButton  = view.findViewById(R.id.uploadButton);
    progressView = view.findViewById(R.id.progressView);
    progressBar = view.findViewById(R.id.progressBar);
    progressView.setVisibility(View.GONE);
    try {
      Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data);
      imageView.setImageBitmap(bitmap);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void onUploadClick() {
    if (data.getPath() != null) {
      File imageFile = new File(data.getPath());

      TransferObserver uploadObserver =
          transferUtility.upload("public/" + imageFile.getName(), imageFile);

      // Attach a listener to the observer to get state update and progress notifications
      uploadObserver.setTransferListener(new TransferListener() {

        @Override public void onStateChanged(int id, TransferState state) {
          if (TransferState.COMPLETED == state) {
            // Handle a completed upload.
            progressView.setVisibility(View.GONE);
            EventBus.getDefault().post(new HomeActivityEvents.ImageUploadedEvent());
          }else if (TransferState.IN_PROGRESS == state){
            progressView.setVisibility(View.VISIBLE);
          }
        }

        @Override public void onProgressChanged(int id, long bytesCurrent, long bytesTotal) {
          float percentDonef = ((float) bytesCurrent / (float) bytesTotal) * 100;
          int percentDone = (int) percentDonef;
          progressBar.setProgress(percentDone);
          Log.d("YourActivity", "ID:"
              + id
              + " bytesCurrent: "
              + bytesCurrent
              + " bytesTotal: "
              + bytesTotal
              + " "
              + percentDone
              + "%");
        }

        @Override public void onError(int id, Exception ex) {
          // Handle errors
        }
      });

      // If you prefer to poll for the data, instead of attaching a
      // listener, check for the state and progress in the observer.
      if (TransferState.COMPLETED == uploadObserver.getState()) {
        // Handle a completed upload.
      }

      Log.d("YourActivity", "Bytes Transferred: " + uploadObserver.getBytesTransferred());
      Log.d("YourActivity", "Bytes Total: " + uploadObserver.getBytesTotal());
    }
  }
}

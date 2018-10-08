package com.luezoid.marsplayassignment;

import android.content.Context;
import android.os.AsyncTask;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import java.util.List;

/**
 * Created by Abhishek Kumar (akabhikumar08@gmail.com) on 08/10/18.
 */
public class ListBucketAsyncTask extends AsyncTask<Void, Void, List<String>> {
  private Context context;

  public ListBucketAsyncTask(Context context) {
    this.context = context;
  }

  @Override protected void onPreExecute() {
    super.onPreExecute();
  }

  @Override protected List<String> doInBackground(Void... voids) {

    //AmazonS3 s3client = AWSHelper.getS3Client(context);
    //List<Bucket> buckets = s3client.listBuckets();
    //for(Bucket bucket : buckets) {
    //  System.out.println(bucket.getName());
    //}
    return null;
  }

  @Override protected void onPostExecute(List<String> strings) {
    super.onPostExecute(strings);
  }
}

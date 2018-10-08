package com.luezoid.marsplayassignment.di.modules;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobile.config.AWSConfiguration;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.services.s3.AmazonS3Client;
import com.squareup.picasso.Picasso;
import dagger.Module;
import dagger.Provides;
import java.text.SimpleDateFormat;
import javax.inject.Singleton;

/**
 * Created by abhishek on 29/08/17.
 */
@Module public class NetModule {

  //String mBaseUrl;

  public NetModule() {
  }

  @Provides @Singleton
    // Application reference must come from AppModule.class
  SharedPreferences providesSharedPreferences(Application application) {
    return PreferenceManager.getDefaultSharedPreferences(application);
  }

  @Provides @Singleton Picasso providePicassoInstance(Application application) {
    return Picasso.with(application);
  }

  @Provides @Singleton SimpleDateFormat provideDateFormatInstance() {
    return new SimpleDateFormat();
  }

  @Provides @Singleton CognitoCachingCredentialsProvider getCredProvider(Context context) {
    AWSConfiguration config = new AWSConfiguration(context);
    return new CognitoCachingCredentialsProvider(context, config);
  }

  @Provides @Singleton AmazonS3Client getS3Client(Context context) {

    AWSConfiguration config = new AWSConfiguration(context);
    return new AmazonS3Client(new CognitoCachingCredentialsProvider(context, config));
  }

  @Provides @Singleton TransferUtility getTransferUtility(Context context) {
    AWSConfiguration config = new AWSConfiguration(context);
    TransferUtility sTransferUtility = TransferUtility.builder()
        .context(context)
        .awsConfiguration(config)
        .s3Client(new AmazonS3Client(new CognitoCachingCredentialsProvider(context, config)))
        .build();

    return sTransferUtility;
  }

  //@Provides @Singleton Cache provideOkHttpCache(Application application) {
  //  int cacheSize = 10 * 1024 * 1024; // 10 MiB
  //  Cache cache = new Cache(application.getCacheDir(), cacheSize);
  //  return cache;
  //}

  //@Provides @Singleton OkHttpClient provideOkHttpClient(Cache cache, Application application) {
  //
  //  HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
  //  logging.setLevel(HttpLoggingInterceptor.Level.BODY);
  //
  //  OkHttpClient client = new OkHttpClient.Builder().cache(cache)
  //      .writeTimeout(2, TimeUnit.MINUTES)
  //      .readTimeout(2, TimeUnit.MINUTES)
  //      .connectTimeout(2, TimeUnit.MINUTES)
  //      //.addInterceptor(new HeaderInterceptor(application.getApplicationContext()))
  //      //.addNetworkInterceptor(new com.facebook.stetho.okhttp3.StethoInterceptor())
  //      .addInterceptor(logging)
  //      .retryOnConnectionFailure(true)
  //      .build();
  //
  //  return client;
  //}

  //@Provides @Singleton Retrofit provideRetrofit(OkHttpClient okHttpClient) {
  //  Retrofit retrofit = new Retrofit.Builder().baseUrl(mBaseUrl)
  //      .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
  //      .addConverterFactory(GsonConverterFactory.create())
  //      .client(okHttpClient)
  //      .build();
  //  return retrofit;
  //}

  //@Provides @Singleton RetrofitApiService provideRetrofitService(Retrofit retrofit) {
  //  RetrofitApiService retrofitApiService = retrofit.create(RetrofitApiService.class);
  //
  //  return retrofitApiService;
  //}

  //
}

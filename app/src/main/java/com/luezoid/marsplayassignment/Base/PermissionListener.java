package com.luezoid.marsplayassignment.Base;

public interface PermissionListener {

  void onGranted(int requestCode);

  void onRejected(int requestCode);
}

package com.example.terminal.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;

import com.example.terminal.BuildConfig;
import com.example.terminal.R;
import com.example.terminal.global.Constant;
import com.example.terminal.http.Url;
import com.example.terminal.util.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.text.DecimalFormat;

public class DownAPKService extends Service {

    private final int NotificationID = 0x10000;
    private NotificationManager mNotificationManager = null;
    private NotificationCompat.Builder builder;

    // 文件保存路径(如果有SD卡就保存SD卡,如果没有SD卡就保存到手机包名下的路径)
    private String APK_dir;
    private String destFileName;

    @Override
    public void onCreate() {
        super.onCreate();
        initApkDir();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void initApkDir() {
        APK_dir = Environment.getExternalStorageDirectory() + "/hzxDownload/";
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 接收Intent传来的参数:
        // 文件下载路径
        if(intent != null) {
            Bundle bundle = intent.getExtras();
            String APK_URL = bundle.getString(Constant.INFO);
            String versionName = bundle.getString(Constant.VersionName);

            destFileName = "hzx_" + versionName + ".apk";

            DownFile(APK_URL);
        }

        return super.onStartCommand(intent, flags, startId);
    }

    /**
     * @param download_url 下载链接
     */
    private void DownFile(String download_url) {

        if(!download_url.contains(Constant.Http))
            download_url = Url.DOWN_APK_URL + download_url ;

        OkGo.<File>get(download_url)
                .execute(new FileCallback(APK_dir, destFileName) {
                    @Override
                    public void onSuccess(Response<File> response) {
                        installApk();

                        // 震动提示
                        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                        assert vibrator != null;
                        vibrator.vibrate(250L);// 参数是震动时间(long类型)
                        stopSelf();

                        mNotificationManager.cancel(NotificationID);
                    }

                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        super.onStart(request);
                        ToastUtils.showToast(getString(R.string.toast_download_start));
                        startDownload();
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        // 给Handler发送信息更新进度条
                        int x = (int) (progress.fraction * 100);
                        int totalS = 100;
                        builder.setProgress(totalS, x, false);
                        builder.setContentInfo(getPercent(x, totalS));
                        mNotificationManager.notify(NotificationID, builder.build());
                    }

                    @Override
                    public void onError(Response<File> response) {
                        mNotificationManager.cancel(NotificationID);
                        ToastUtils.showToast(getString(R.string.toast_download_error));
                    }
                });
    }

    /**
     * 开始下载Apk
     */
    private void startDownload() {
        String id = "1";
        String name = "1";
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // 针对Android 8.0版本对于消息栏的限制，需要加入channel渠道这一概念
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {  //Android 8.0以上
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            mNotificationManager.createNotificationChannel(mChannel);
            builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setSmallIcon(R.mipmap.icon_logo);
            builder.setTicker(getString(R.string.text_download_ing));
            builder.setContentTitle(getApplicationName());
            builder.setContentText(getString(R.string.text_download_later));
            builder.setNumber(0);
            builder.setChannelId(id);
            builder.setAutoCancel(true);
        } else {    //Android 8.0以下
            builder = new NotificationCompat.Builder(getApplicationContext());
            builder.setSmallIcon(R.mipmap.icon_logo);
            builder.setTicker(getString(R.string.text_download_ing));
            builder.setContentTitle(getApplicationName());
            builder.setContentText(getString(R.string.text_download_later));
            builder.setNumber(0);
            builder.setAutoCancel(true);
        }

        mNotificationManager.notify(NotificationID, builder.build());
    }

    /**
     * 安装Apk
     */
    private void installApk() {
        // 如果已经下载完毕跳出循环
        Intent installIntent = new Intent(Intent.ACTION_VIEW);
        File file = new File(APK_dir + destFileName);
        installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 24) {
            //大于7.0使用此方法
            Uri apkUri = FileProvider.getUriForFile(DownAPKService.this, "com.flight.trans.fileprovider", file);
            //添加这一句表示对目标应用临时授权该Uri所代表的文件
            installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            installIntent.setDataAndType(apkUri, "application/vnd.android.package-archive");
        } else {
            //小于7.0就简单了
            // 由于没有在Activity环境下启动Activity,设置下面的标签
            installIntent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        }

        startActivity(installIntent);// 下载完成之后自动弹出安装界面
    }

    /**
     * @param x     当前值
     * @param total 总值
     *              [url=home.php?mod=space&uid=7300]@return[/url] 当前百分比
     * @Description 返回百分之值
     */
    private String getPercent(int x, int total) {
        String result;// 接受百分比的值
        double x_double = x * 1.0;
        double tempResult = x_double / total;
        // 百分比格式，后面不足2位的用0补齐 ##.00%
        DecimalFormat df1 = new DecimalFormat("0.00%");
        result = df1.format(tempResult);
        return result;
    }

    /**
     * @return
     * @Description 获取当前应用的名称
     */
    private String getApplicationName() {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo;
        try {
            packageManager = getApplicationContext().getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        String applicationName = (String) packageManager.getApplicationLabel(applicationInfo);
        return applicationName;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }
}

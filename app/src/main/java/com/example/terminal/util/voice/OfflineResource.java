package com.example.terminal.util.voice;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static android.content.ContentValues.TAG;

/**
 * 离线文件
 *
 * @author liub
 */
public class OfflineResource {

    private AssetManager assets;
    private String destPath;

    private String backFilename;
    private String modelFilename;

    public OfflineResource(Context context) {
        this.assets = context.getAssets();
        this.destPath = createTmpDir(context);
        setOfflineVoiceType();
    }

    public String getModelFilename() {
        return modelFilename;
    }

    public String getBackFilename() {
        return backFilename;
    }

    public void setOfflineVoiceType() {
        String back = "backend_lzl";
        String model = "frontend_model";
        backFilename = copyAssetsFile(back);
        modelFilename = copyAssetsFile(model);
    }


    private String copyAssetsFile(String sourceFilename)  {
        String destFilename = destPath + "/" + sourceFilename;
        copyFromAssets(assets, sourceFilename, destFilename);
        Log.i(TAG, "Assets to sdcard successed：" + destFilename);
        return destFilename;
    }


    /**
     * 创建一个临时目录，用于复制临时文件，如assets目录下的离线资源文件
     * @param context
     * @return
     */

    private String createTmpDir(Context context) {
        String sampleDir = "/ing/tts";
        String tmpDir = Environment.getExternalStorageDirectory().toString() + sampleDir;
        if (!makeDir(tmpDir)) {
            tmpDir = context.getExternalFilesDir(sampleDir).getAbsolutePath();
            if (!makeDir(sampleDir)) {
                throw new RuntimeException("create model resources dir failed :" + tmpDir);
            }
        }
        return tmpDir;
    }

    private boolean makeDir(String dirPath) {
        File file = new File(dirPath);
        if (!file.exists()) {
            return file.mkdirs();
        } else {
            return true;
        }
    }

    /**
     * assets文件2 sdcard
     * @param assets
     * @param source
     * @param dest
     * @throws IOException
     */
    private void copyFromAssets(AssetManager assets, String source, String dest)  {
        File file = new File(dest);
        if (!file.exists()) {
            InputStream is ;
            FileOutputStream fos = null;
            try {
                is = assets.open(source);
                String path = dest;
                fos = new FileOutputStream(path);
                byte[] buffer = new byte[1024];
                int size ;
                while ((size = is.read(buffer, 0, 1024)) >= 0) {
                    fos.write(buffer, 0, size);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

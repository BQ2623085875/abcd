package com.example.terminal.listener;

import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.listener.OnResultCallbackListener;

import java.util.List;

/**
 * 拍照回传
 */
public abstract class ResultCallbackListener implements OnResultCallbackListener<LocalMedia> {

    @Override
    public void onResult(List<LocalMedia> result) {
        onResult(result.get(0).getPath());
    }

    @Override
    public void onCancel() {

    }

    public abstract void onResult(String localPath);
}

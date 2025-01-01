package com.example.terminal.util.voice;

import android.content.Context;
import android.text.TextUtils;

import com.example.terminal.util.CommonUtils;
import com.example.terminal.util.LogUtils;
import com.unisound.client.SpeechConstants;
import com.unisound.client.SpeechSynthesizer;
import com.unisound.client.SpeechSynthesizerListener;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.regex.Pattern;


/**
 * 离线语音合成工具类
 *
 * @author liub
 */
public class SpeakUti {

    /**
     * appKey
     */
    public static final String appKey = "x6z7w2qundwtayyzwidvzaslilugerav2xwdlbih";

    /**
     * secret
     */
    public static final String secret = "07cee99ca4dbf0511f420197cd7936f6";

    private static SpeakUti instance;

    private SpeechSynthesizer mTTSPlayer;

    /**
     * 存储待播放的文字信息
     */
    private BlockingDeque<String> speechQueue = new LinkedBlockingDeque<>();

    private boolean released = false;

    protected OfflineResource offlineResource;

    /**
     * 判断当前语音是否播放完成
     */
    private boolean isSpeaking = false;

    /**
     * 用来判断初始化是否成功
     */
    private boolean isInitSuccess = false;

    private SpeakUti() {

    }

    public static SpeakUti getInstance() {
        if (instance == null) {
            synchronized (SpeakUti.class) {
                if (instance == null) {
                    instance = new SpeakUti();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化引擎
     *
     * @author JPH
     */
    public void init(Context context) {
        initSpeech(context, 50, 52);
    }

    /**
     * 初始化引擎
     *
     * @author JPH
     */
    public void init(Context context, int pitch) {
        initSpeech(context, pitch, 52);
    }


    /**
     * 初始化引擎
     *
     * @author JPH
     */
    public void init(Context context, int pitch, int speed) {
        initSpeech(context, pitch, speed);
    }


    private void initSpeech(Context context, int pitch, int speed) {

        offlineResource = new OfflineResource(context);

        // 初始化语音合成对象
        mTTSPlayer = new SpeechSynthesizer(context, appKey, secret);
        // 设置本地合成
        mTTSPlayer.setOption(SpeechConstants.TTS_SERVICE_MODE, SpeechConstants.TTS_SERVICE_MODE_LOCAL);
        // 音调
        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_PITCH, pitch);
        // 语速
        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_SPEED, speed);
        // 音量
        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_VOLUME, 100);

        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_IS_READ_ENLISH_IN_PINYIN, false);

        // 设置前端模型
        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_FRONTEND_MODEL_PATH, offlineResource.getModelFilename());
        // 设置后端模型
        mTTSPlayer.setOption(SpeechConstants.TTS_KEY_BACKEND_MODEL_PATH, offlineResource.getBackFilename());
        // 设置回调监听
        mTTSPlayer.setTTSListener(new SpeechSynthesizerListener() {
            @Override
            public void onEvent(int type) {
                switch (type) {
                    case SpeechConstants.TTS_EVENT_INIT:
                        // 初始化成功回调
                        isInitSuccess = true;
                        // 初始化完成之后更新下队列
                        try {
                            updateSpeech();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case SpeechConstants.TTS_EVENT_SYNTHESIZER_START:
                        // 开始合成回调
                        break;
                    case SpeechConstants.TTS_EVENT_SYNTHESIZER_END:
                        // 合成结束回调
                        break;
                    case SpeechConstants.TTS_EVENT_BUFFER_BEGIN:
                        // 开始缓存回调
                        break;
                    case SpeechConstants.TTS_EVENT_BUFFER_READY:
                        // 缓存完毕回调
                        break;
                    case SpeechConstants.TTS_EVENT_PLAYING_START:
                        // 开始播放回调
                        isSpeaking = true;
                        break;
                    case SpeechConstants.TTS_EVENT_PLAYING_END:
                        // 播放完成回调
                        isSpeaking = false;
                        try {
                            updateSpeech();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case SpeechConstants.TTS_EVENT_PAUSE:
                        // 暂停回调
                        break;
                    case SpeechConstants.TTS_EVENT_RESUME:
                        // 恢复回调
                        break;
                    case SpeechConstants.TTS_EVENT_STOP:
                        // 停止回调
                        break;
                    case SpeechConstants.TTS_EVENT_RELEASE:
                        // 释放资源回调
                        break;
                    default:
                        break;
                }

            }

            @Override
            public void onError(int type, String errorMSG) {
                isSpeaking = false;
                // 语音合成错误回调
                CommonUtils.writeCrashLog("语音合成错误回调TTS:" + "onError __ type : " + type + " errorMsg : " + errorMSG);
                LogUtils.e("语音合成错误回调TTS", "onError __ type : " + type + " errorMsg : " + errorMSG);
            }
        });
        // 初始化合成引擎
        try {
            mTTSPlayer.init("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止播放
     *
     * @author JPH
     */
    public void stop() {
        mTTSPlayer.stop();
    }

    /**
     * 播放
     *
     * @author JPH
     */
    public void play(String content) {
        play(content, PLAY_MODE.QUEUED);
    }

    private static String returnResultMultiple(String str) {
        if (TextUtils.isEmpty(str))
            return "";

        String value = str;

        Pattern compile = Pattern.compile("[a-zA-Z]");

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (compile.matcher(String.valueOf(ch)).matches()) {
                value = value.replace(ch + "", ch + "-");
            }
        }
        return value;
    }

    public void play(String content, PLAY_MODE playMode) {
        switch (playMode) {
            case QUEUED:
                playQueued(content);
                break;
            case IMMEDIATELY:
                playImmediately(content);
                break;
        }
    }

    private void updateSpeech() {
        // 如果云知声引擎初始化失败，直接结束
        if (!isInitSuccess) {
            LogUtils.e("语音合成", "未初始化");
            return;
        }
        // 其他队列（包括越界、限高、停止点、停车位、导航）
        if (!speechQueue.isEmpty()) {
            try {
                String message = speechQueue.takeLast();
                speechQueue.clear();
                speak(message);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void speak(String content) {
        try {
            mTTSPlayer.playText(content);
        } catch (IllegalThreadStateException e) {
            e.getMessage();
        }
    }

    public void playQueued(String content) {
        try {
            speechQueue.putLast(content);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (!isSpeaking) {
            updateSpeech();
            isSpeaking = true;
        }
    }

    public void playImmediately(String content) {
        speak(content);
    }

    /**
     * 释放资源
     */
    public void release() {
        // 主动释放离线引擎
        if (released) {
            return;
        }
        if (mTTSPlayer != null) {
            mTTSPlayer.stop();
            mTTSPlayer.release(SpeechConstants.TTS_RELEASE_ENGINE, null);
        }
        instance = null;
        released = true;
    }


    public enum PLAY_MODE {
        QUEUED,
        IMMEDIATELY
    }
}


//public class TTSUtils implements SpeechSynthesizerListener {
//    private static final String TAG = "TTSUtils";
//    private static volatile TTSUtils instance = null;
//    private boolean isInitSuccess = false;
//    private SpeechSynthesizer mTTSPlayer;
//    public static final String SAMPLE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/unisound/tts/";
//    public static final String FRONTEND_MODEL = "frontend_model";
//    public static final String BACKEND_MODEL = "backend_lzl";
//    private static final String APPKEY = "wiouzjcsmxvqes7ibqqm5bcgqrzrvddsvtceiwa5";//这里换成你的key和secret
//    private static final String SECRET = "3e20d7aff586ffe7dce62b302e7cc378";
//    private TTSUtils() {
//    }
//    public static TTSUtils getInstance() {
//        if (instance == null) {
//            synchronized (TTSUtils.class) {
//                if (instance == null) {
//                    instance = new TTSUtils();
//                }
//            }
//        }
//        return instance;
//    }
//    public void init() {
//        try {
//            Context context = BaseApplication.getContext();
//            //判断文件是否完整
//            File _FrontendModelFile = new File(SAMPLE_DIR + FRONTEND_MODEL);
//            File _BackendModelFile = new File(SAMPLE_DIR + BACKEND_MODEL);
//            //校验文件的完整性
//            String file1 = getFileMD5(_FrontendModelFile);
//            String file2 = getFileMD5(_BackendModelFile);
////            if(!file1.equals("27ce3b75c2784353e33840c5e63b5f0c")||!file2.equals("cfcdd50077ee5a6c5d673b728a8d6f5")){
//            if(!file1.equals("27ce3b75c2784353e33840c5e63b5f0c")||!file2.equals("57c9e96801d1173186407193e26a5ecf")){
//                _FrontendModelFile.delete();
//                _BackendModelFile.delete();
//                Toast.makeText(context, "下载离线包后即可使用语音合成功能！", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            mTTSPlayer = new SpeechSynthesizer(context, APPKEY, SECRET);
//            mTTSPlayer.setOption(SpeechConstants.TTS_SERVICE_MODE, SpeechConstants.TTS_SERVICE_MODE_LOCAL); // 设置本地合成
//            File file = new File(SAMPLE_DIR);
//            if (!file.exists()) {
//                file.mkdirs();
//            }
//            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_FRONTEND_MODEL_PATH, SAMPLE_DIR + FRONTEND_MODEL);// 设置前端模型
//            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_BACKEND_MODEL_PATH, SAMPLE_DIR + BACKEND_MODEL);// 设置后端模型
////            setOption(int key, java.lang.Object value)
////            设置合成相关参数
////            示例：
////            设置合成语速 SpeechConstants.TTS_KEY_VOICE_SPEED 范围 0 ~ 100 int
////            设置合成音高 SpeechConstants.TTS_KEY_VOICE_PITCH 范围 0 ~ 100 int
////            设置合成音量 SpeechConstants.TTS_KEY_VOICE_VOLUME 范围 0 ~ 100 int
////            设置是否将英文按拼音读 SpeechConstants.TTS_KEY_IS_READ_ENLISH_IN_PINYIN 如：wang->王 boolean
////            设置语音结尾段的静音时长 SpeechConstants.TTS_KEY_BACK_SILENCE 0 ~ 1000 单位ms int
////            设置语音开始段的静音时长 SpeechConstants.TTS_KEY_FRONT_SILENCE 0 ~ 1000 单位ms int
//            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_SPEED,85);
//            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_PITCH,55);
////            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_VOICE_VOLUME,200);
//            //文字加数字，“玩啊”+106  106会读一百零六    “H”+106   会一个一个读出来 H 1 0 6
////            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_IS_READ_ENLISH_IN_PINYIN,false);
//            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_BACK_SILENCE,300);//设置尾音
////            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_FRONT_SILENCE,1000);//设置开始音
//            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_PLAY_START_BUFFER_TIME,250);//语音开始缓冲时间
////            mTTSPlayer.setOption(SpeechConstants.TTS_KEY_STREAM_TYPE, AudioManager.STREAM_SYSTEM);
//            mTTSPlayer.setTTSListener(this);// 设置回调监听
//            mTTSPlayer.init(null);// 初始化合成引擎
//        } catch (Exception e) {
////            e.printStackTrace();
//            Toast.makeText(BaseApplication.getContext(), "下载离线包后即可使用语音合成功能！", Toast.LENGTH_SHORT).show();
//            return;
//        }
//    }
//    *获取文件的md5码，判断文件的完整性
//    private static String getFileMD5(File file) throws NoSuchAlgorithmException, IOException {
//        if (!file.exists()||!file.isFile()) {
////            不是文件,或者不存在
//            return "";
//        }
//        MessageDigest digest;
//        FileInputStream in;
//        byte buffer[] = new byte[1024];
//        int len;
//        digest = MessageDigest.getInstance("MD5");
//        in = new FileInputStream(file);
//        while ((len = in.read(buffer, 0, 1024)) != -1) {
//            digest.update(buffer, 0, len);
//        }
//        in.close();
//        BigInteger bigInt = new BigInteger(1, digest.digest());
//        return bigInt.toString(16);
//    }
//    *一个字节一个字节读
//    public static void speckText(String msg){
//        TTSUtils.getInstance().speak(getS(msg));
//    }
//    *语义识别朗读
//    public static void speeckTrueText(String msg){
//        if(msg==null||"".equals(msg)){
//            msg="";
//        }
//        if(msg.length()>25){
//            msg=msg.substring(0,15);
//        }
//        TTSUtils.getInstance().speak(msg);
//    }
//    public static String getS(String msg){
//        if(msg==null||"".equals(msg)){
//            return "";
//        }
//        char[] chars = msg.toCharArray();
//        StringBuilder sb=new StringBuilder();
//        for (char aChar : chars) {
//            sb.append(aChar+"\"");
//        }
//        return sb.toString();
//    }
//    public void speak(String msg) {
//        try {
//            if (isInitSuccess) {
//                mTTSPlayer.playText(msg);
//            }else {
//                init();
//            }
//        } catch (Exception e) {
//            Toast.makeText(BaseApplication.getContext(), "下载离线包后即可使用语音合成功能！", Toast.LENGTH_SHORT).show();
//        }
//    }
//    public void stop() {
//        mTTSPlayer.stop();
//    }
//    public void pause() {
//        mTTSPlayer.pause();
//    }
//    public void resume() {
//        mTTSPlayer.resume();
//    }
//    public void release() {
//        if (null != mTTSPlayer) {
//            // 释放离线引擎
//            mTTSPlayer.release(SpeechConstants.TTS_RELEASE_ENGINE, null);
//        }
//    }
//    @Override
//    public void onEvent(int type) {
//        switch (type) {
//            case SpeechConstants.TTS_EVENT_INIT:
//                isInitSuccess = true;
//                break;
//            case SpeechConstants.TTS_EVENT_SYNTHESIZER_START:
//                // 开始合成回调
//                Log.i(TAG, "beginSynthesizer");
//                break;
//            case SpeechConstants.TTS_EVENT_SYNTHESIZER_END:
//                // 合成结束回调
//                Log.i(TAG, "endSynthesizer");
//                break;
//            case SpeechConstants.TTS_EVENT_BUFFER_BEGIN:
//                // 开始缓存回调
//                Log.i(TAG, "beginBuffer");
//                break;
//            case SpeechConstants.TTS_EVENT_BUFFER_READY:
//                // 缓存完毕回调
//                Log.i(TAG, "bufferReady");
//                break;
//            case SpeechConstants.TTS_EVENT_PLAYING_START:
//                // 开始播放回调
//                Log.i(TAG, "onPlayBegin");
//                break;
//            case SpeechConstants.TTS_EVENT_PLAYING_END:
//                // 播放完成回调
//                Log.i(TAG, "onPlayEnd");
//                break;
//            case SpeechConstants.TTS_EVENT_PAUSE:
//                // 暂停回调
//                Log.i(TAG, "pause");
//                break;
//            case SpeechConstants.TTS_EVENT_RESUME:
//                // 恢复回调
//                Log.i(TAG, "resume");
//                break;
//            case SpeechConstants.TTS_EVENT_STOP:
//                // 停止回调
//                Log.i(TAG, "stop");
//                break;
//            case SpeechConstants.TTS_EVENT_RELEASE:
//                // 释放资源回调
//                Log.i(TAG, "release");
//                break;
//            default:
//                break;
//        }
//    }
//    @Override
//    public void onError(int type, String errorMSG) {
//        Log.e(TAG, "语音合成错误回调: " + errorMSG);
//    }
//    *如果将assets里面的文件放在你自己的assets下，就需要用到下面的方法
////    public static void copyAssetsFile2SDCard(Context context, String fileName, String path) {
////        InputStream is=null;
////        FileOutputStream fos=null;
////        try {
////            is= context.getAssets().open(fileName);
////            fos= new FileOutputStream(new File(path));
////            byte[] buffer = new byte[1024];
////            int byteCount = 0;
////            while ((byteCount = is.read(buffer)) != -1) {// 循环从输入流读取buffer字节
////                fos.write(buffer, 0, byteCount);// 将读取的输入流写入到输出流
////            }
////           fos.flush();
////        } catch (IOException e) {
////            Log.e(TAG, "copyAssetsFile2SDCard: " + e.toString());
////        } finally {
////            if(fos!=null){
////                try {
////                    fos.close();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////            if(is!=null){
////                try {
////                    is.close();
////                } catch (IOException e) {
////                    e.printStackTrace();
////                }
////            }
////        }
////    }
//}

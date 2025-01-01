package com.example.terminal.util;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.example.terminal.R;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.global.Constant;
import com.example.terminal.global.ConstantInfo;
import com.example.terminal.global.MainApplication;
import com.example.terminal.listener.OnFinishTouchListener;
import com.example.terminal.util.voice.SpeakUti;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

    private Date date;

    /**
     * 通过设置全屏，设置状态栏透明
     *
     * @param activity
     */
    public static void changeSystemUi(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     * 获取当天
     *
     * @return
     */
    public static String getDate() {
        Calendar calendar = Calendar.getInstance();

        return calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取时间字符串
     *
     * @return
     */
    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return simpleDateFormat.format(date);
    }

    /**
     * 转换时间
     *
     * @param date
     * @return
     */
    public static String getDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }

    /**
     * 转换时间
     *
     * @param date
     * @return
     */
    public static String getTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        return simpleDateFormat.format(date);
    }

    /**
     * 转换时间
     *
     * @param date
     * @return
     */
    public static String getTimeSecond(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public static String getTimeStamp() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddhhmmssS");
        return simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }

    /**
     * 字符串转时间
     *
     * @param date
     * @return
     */
    public static Calendar dateToCalender(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(format.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return calendar;
    }

    /**
     * 字符串转时间
     *
     * @param date
     * @return
     */
    public static Calendar dateToCalenderTime(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Calendar calendar = Calendar.getInstance();

        try {
            calendar.setTime(format.parse(date));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return calendar;
    }

    public static Date itStringToDateTime(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转时间戳
     *
     * @param timeString
     * @return
     */
    public static long getTimeLong(String timeString) {
        if (TextUtils.isEmpty(timeString))
            return 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(timeString).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Day:日期字符串例如  Num:需要减少的天数例如 7
     *
     * @param day
     * @param Num
     * @return
     */
    public static String getDateCut(String day, int Num) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = null;
        try {
            nowDate = df.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //如果需要向后计算日期 -改为+
        Date newDate2 = new Date(nowDate.getTime() - (long) Num * 24 * 60 * 60 * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOk = simpleDateFormat.format(newDate2);
        return dateOk;
    }

    /**
     * Day:日期字符串例如 Num:需要减少的天数例如 7
     *
     * @param day
     * @param Num
     * @return
     */
    public static String getDateAdd(String day, int Num) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date nowDate = null;
        try {
            nowDate = df.parse(day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //如果需要向后计算日期 -改为+
        Date newDate2 = new Date(nowDate.getTime() + (long) Num * 24 * 60 * 60 * 1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateOk = simpleDateFormat.format(newDate2);
        return dateOk;
    }

    public static String getDateDay(String data) {
        return data.split("-")[2];
    }

    public static boolean compareDate(String data) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dateNow = sdf.parse(getDate());
            Date date = sdf.parse(data);
            //判断日期
            if (((date.getTime() - dateNow.getTime()) / (24 * 60 * 60 * 1000)) == 7) {
                return false;
            } else {
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static int getVersionCode(Context context) {
        PackageManager manager = context.getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 获取版本号
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        PackageManager manager = context.getPackageManager();
        String code = "";
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            code = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 获取权限
     *
     * @param mActivity
     */
    public static void getPermissions(BaseActivity mActivity) {
        if (!XXPermissions.isHasPermission(mActivity, ConstantInfo.PermissionList)) {
            XXPermissions.with(mActivity).permission(ConstantInfo.PermissionList).request(new OnPermission() {
                @Override
                public void hasPermission(List<String> granted, boolean isAll) {
                    if (isAll) {
                        SpeakUti.getInstance().init(mActivity);
                    } else {
                        ToastUtils.showLongToast(R.string.no_permission);
                    }
                }

                @Override
                public void noPermission(List<String> denied, boolean quick) {
                }
            });
        } else {
            try {
                SpeakUti.getInstance().init(mActivity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 留取两位数
     *
     * @param num
     * @return
     */
    public static double getDouble(double num) {
        DecimalFormat df = new DecimalFormat("#.00");

        return Double.parseDouble(df.format(num));
    }

    /**
     * 留取三位数 不四舍五入
     *
     * @param num
     * @return
     */
    public static double getDoubles(double num) {
        DecimalFormat df = new DecimalFormat("0.000");
        df.setRoundingMode(RoundingMode.DOWN);
        return Double.parseDouble(df.format(num));
    }

    /**
     * 判空获取字段
     *
     * @param name
     * @return
     */
    public static String getEmptyName(String name) {
        if (TextUtils.isEmpty(name)) {
            return "";
        }

        return name;
    }

    /**
     * 留取4位数
     *
     * @param num
     * @return
     */
    public static String getDoubleFour(double num) {
        return String.format(Locale.ENGLISH, "%.4f", num);
    }

    /**
     * 图片转File
     *
     * @param originBitmap
     */
    public static File bitmapChangeFile(Bitmap originBitmap) {
        if (originBitmap == null)
            return null;

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        Bitmap.Config config = originBitmap.getConfig();
        Bitmap bitmap = Bitmap.createBitmap(originBitmap.getWidth(), originBitmap.getHeight(), config);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(0, 0, originBitmap.getWidth(), originBitmap.getHeight(), paint);
        canvas.drawBitmap(originBitmap, 0, 0, paint);

        //将要保存图片的路径
        String imagePath = "/saveFile.jpg";
        File file = new File(MainApplication.getContext().getExternalCacheDir(), imagePath);

        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    /**
     * 双击
     */
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }

        lastClickTime = time;
        return false;
    }


    /**
     * 双击  3秒以上
     */
    private static long lastClickTimeThreeSeconds;

    public static boolean isFastDoubleClickThreeSeconds() {
        long time = System.currentTimeMillis();
        if (time - lastClickTimeThreeSeconds < 2000) {
            return true;
        }
        lastClickTimeThreeSeconds = time;
        return false;
    }


    /**
     * 连续点击6次
     */
    private static int clickPosition = 1;

    public static boolean isSixClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 500) {
            lastClickTime = time;
            ++clickPosition;
            if (clickPosition == 5) {
                clickPosition = 0;
                return true;
            } else {
                return false;
            }
        }
        clickPosition = 0;
        lastClickTime = time;
        return false;
    }

    /**
     * 防止快速点击
     */
    public static boolean isFastClick() {
        boolean flag = false;
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastClickTime >= 500) {
            flag = true;
        }
        lastClickTime = currentTime;
        return !flag;
    }

    /**
     * 测试登录
     */
    public static boolean isTestLoginClick() {
        long time = System.currentTimeMillis();
        long timeGap = time - lastClickTime;
        if (timeGap < 1200) {
            if (clickPosition == 1 || clickPosition == 4 || clickPosition == 5) {
                if (timeGap > 400) {
                    lastClickTime = time;
                    ++clickPosition;
                    return false;
                } else {
                }
            } else {
                lastClickTime = time;
                ++clickPosition;
                if (clickPosition == 9) {
                    clickPosition = 0;
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            clickPosition = 1;
            lastClickTime = time;
            return false;
        }

        clickPosition = 0;
        lastClickTime = 0;
        return false;
    }


    public static String getEmptyContent(String content) {
        return !TextUtils.isEmpty(content) ? content : "无";
    }

    /**
     * 根据屏幕大小设置是否隐藏
     *
     * @param view
     */
    public static void setScreenViewVisible(View view) {
        view.setVisibility(ConstantInfo.notNW ? View.VISIBLE : View.GONE);
    }

    /**
     * 右滑退出
     *
     * @param view
     */
    public static void onFinishTouch(BaseActivity mActivity, View view) {
        view.setOnTouchListener(new OnFinishTouchListener(mActivity));
    }

    /**
     * 根据屏幕大小设置是否隐藏
     *
     * @param view
     */
    public static void setScreenViewGone(View view) {
        view.setVisibility(ConstantInfo.notNW ? View.GONE : View.VISIBLE);
    }

    /**
     * 根据屏幕大小设置是顶部间距
     *
     * @param view
     */
    public static void setScreenContainerPaddingTop(View view) {
        view.setPadding(0, DensityUtils.dip2px(ConstantInfo.notNW ? 20 : 5), 0, 0);
    }

    /**
     * 大小写切换
     *
     * @param content
     */
    public static String toLowerCase(String content) {
        byte[] data = content.getBytes();

        int end = 0 + data.length;
        int dist = 'A' - 'a';

        for (int i = 0; i < end; i++) {
            if (data[i] >= 'a' && data[i] <= 'z') {
                data[i] += dist;
            }
        }

        return new String(data);
    }


    public static String getTimeNorm(String time) {

        try {
            if (TextUtils.isEmpty(time))
                return "";

            String[] times = time.split(" ");
            if (times == null)
                return time;

            String timeStr = "";

            if (times.length == 1)
                timeStr = times[0];

            if (times.length == 2) {
                String[] dates = times[0].split("-");
                String[] hours = times[1].split(":");
                timeStr = hours[0] + hours[1] + "(" + dates[2] + ")";
            }

            return timeStr;
        } catch (Exception e) {
            return time;
        }
    }

    public static boolean isNetworkAvailable() {

        ConnectivityManager connectivity = (ConnectivityManager) MainApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {

            NetworkInfo info = connectivity.getActiveNetworkInfo();

            if (info != null && info.isConnected()) {
                //这里可以得到网络状态网络类型等网络相关信息
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 获取电量百分比
     *
     * @param mActivity
     * @return
     */
    public static String powerPercentAge(BaseActivity mActivity) {
        BatteryManager manager = (BatteryManager) mActivity.getSystemService(mActivity.BATTERY_SERVICE);
        return manager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY) + "%";
    }

    public static String writeNetStatusFile(String fileContent) {

        String filePath = Constant.Net_File_Path + getDate() + ".txt";

        try {
            File dictionaryFile = new File(Constant.Net_File_Path);//这是目录路径
            if (!dictionaryFile.exists()) {
                dictionaryFile.mkdirs();
            }

            File textFile = new File(filePath);//这是文件路径

            if (!textFile.exists()) {
                textFile.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(filePath, "rwd");
            raf.seek(textFile.length());

            raf.write((getTime() + " : " + fileContent + "\n").getBytes());
            raf.close();
            return filePath;
        } catch (Exception e) {
            return "";
        }
    }

    public static String writeCrashLog(String fileContent) {

        String filePath = Constant.Crash_File_Path + getDate() + ".txt";

        try {
            File dictionaryFile = new File(Constant.Crash_File_Path);//这是目录路径
            if (!dictionaryFile.exists()) {
                dictionaryFile.mkdirs();
            }

            File textFile = new File(filePath);//这是文件路径

            if (!textFile.exists()) {
                textFile.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(filePath, "rwd");
            raf.seek(textFile.length());

            raf.write((getTime() + " : " + fileContent + "\n").getBytes());
            raf.close();
            return filePath;
        } catch (Exception e) {
            return "";
        }
    }

    public static void deleteLog(String path) {

        File dictionaryFile = new File(path);//这是目录路径

        if (!dictionaryFile.exists())
            return;

        if (dictionaryFile.listFiles() == null || dictionaryFile.listFiles().length == 0)
            return;

        for (File file : dictionaryFile.listFiles()) {
            if (file != null) {
                if (((Calendar.getInstance().getTimeInMillis() - getCalendarFromDate(file.getName()).getTimeInMillis()) / (24 * 60 * 60 * 1000)) >= Constant.LOG_FILE_SAVE_DAYS) {
                    file.delete();
                }
            }
        }
    }


    public static String uriToPath(String contentUriPath, Context context) {
        Uri contentUri = Uri.parse(contentUriPath);

        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            ;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    private static Calendar getCalendarFromDate(final String dateTime) {

        String date = dateTime.split("\\.")[0];

        int year = 0;
        int month = 0;
        int day = 0;

        try {
            String[] array = date.split("-");

            int[] arrayInt = new int[array.length];

            for (int i = 0; i < array.length; i++) {
                arrayInt[i] = Integer.parseInt(array[i]);
                if (i == 0) {
                    year = arrayInt[0];
                } else if (i == 1) {
                    month = arrayInt[1];
                } else if (i == 2) {
                    day = arrayInt[2];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        if (year > 0 && month >= 0 && day >= 0) {
            cal.set(year, month - 1, day, 0, 0, 0);
        }
        return cal;
    }

    /**
     * 隐藏底部按钮
     *
     * @param mActivity
     */
    public static void hideBottomUIMenu2(BaseActivity mActivity) {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) {
            View view = mActivity.getWindow().getDecorView();
            view.setSystemUiVisibility(View.GONE);
        } else {
            Window mWindow = mActivity.getWindow();
            WindowManager.LayoutParams params = mWindow.getAttributes();
            params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
            mWindow.setAttributes(params);
        }
    }

    public static void deletePhoto(Context context, String filePath) {
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver mContentResolver = context.getContentResolver();
        String where = MediaStore.Images.Media.DATA + "='" + filePath + "'";
        mContentResolver.delete(uri, where, null);

        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())));
    }

    /**
     * 隐藏输入法
     */
    public static void itHideInputMethod(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            // 隐藏输入法
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Json数据特殊标识
     */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

    public static void printJson(String tag, String msg, String headString) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(4);//最重要的方法，就一行，返回格式化的json字符串，其中的数字4是缩进字符数
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(4);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        printLine(tag, true);
        message = headString + LINE_SEPARATOR + message;
        String[] lines = message.split(LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        printLine(tag, false);
    }

    //判断字符串是否包含数字
    public static boolean containsDigit(String input) {
        if (input == null)
            return false;
        try {
            Pattern pattern = Pattern.compile("\\d");
            Matcher matcher = pattern.matcher(input);
            return matcher.find();
        } catch (Exception e) {
            return false;
        }
    }

    //判断字符串是否以数字开头
    public static boolean isFirstCharDigit(String str) {
        if (str != null && !str.isEmpty()) {
            return Character.isDigit(str.charAt(0));
        }
        return false;
    }

    /**
     * 字符串按逗号隔开换行显示
     */
    public static String getStringReplace(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }

        String text = "";

        try {
            text = str.replace(",", "\n");
        } catch (Exception e) {
            e.getMessage().toString();
            return "";
        }

        return text;
    }


    /**
     * 加载缩略图片
     */
    public static Bitmap createThumbnail(String imagePath, int width, int height) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, width, height);
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(imagePath, options);
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) >= reqHeight
                    && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }


/*   # 由于日志的tag, priority(assert, debug, ...), etc. 会占用一些字节，这里4096是一个理想值。
     # 建议这里设置一个小于4096 的值，例如一个比较保守的值 4000
     # private static final int MAX_LOG_BYTES = 4096;*/
    private static final int MAX_LOG_BYTES = 4000;
    public static void largeLog(String tag, String str) {
        String[] logs = SplitStringByByteLength(str, "UTF-8", MAX_LOG_BYTES);
        for (String log : logs) {
            Log.i(tag, log);
        }
    }

    public static String[] SplitStringByByteLength(String src, String encoding, int maxsize) {
        Charset cs = Charset.forName(encoding);
        CharsetEncoder coder = cs.newEncoder();
        ByteBuffer out = ByteBuffer.allocate(maxsize);  // output buffer of required size
        CharBuffer in = CharBuffer.wrap(src);
        List<String> ss = new ArrayList<>();            // a list to store the chunks
        int pos = 0;
        while (true) {
            CoderResult cr = coder.encode(in, out, true); // try to encode as much as possible
            int newpos = src.length() - in.length();
            String s = src.substring(pos, newpos);
            ss.add(s);                                  // add what has been encoded to the list
            pos = newpos;                               // store new input position
            out.rewind();                               // and rewind output buffer
            if (!cr.isOverflow()) {
                break;                                  // everything has been encoded
            }
        }
        return ss.toArray(new String[0]);
    }
}

package com.example.terminal.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.TextUtils;

import com.example.terminal.R;
import com.example.terminal.base.BaseActivity;
import com.example.terminal.global.Constant;

import java.io.UnsupportedEncodingException;

public class NfcUtils {

    private BaseActivity activity ;

    //nfc
    public NfcAdapter mNfcAdapter;
    public static IntentFilter[] mIntentFilter = null;
    public static PendingIntent mPendingIntent = null;
    public static String[][] mTechList = null;

    public NfcUtils(BaseActivity activity) {
        NfcInit(activity);
    }

    /**
     * 检查NFC是否打开
     */
    public boolean NfcCheck(BaseActivity activity) {
        this.activity = activity ;
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(activity);
        if (nfcAdapter == null) {
            ToastUtils.showToast(R.string.text_device_not_nfc);
            return false;
        } else {
            if (!nfcAdapter.isEnabled()) {
                ToastUtils.showToast(R.string.text_nfc_close);
                Intent setNfc = new Intent(Settings.ACTION_NFC_SETTINGS);
                activity.startActivity(setNfc);
                return false ;
            }else {
                mNfcAdapter = nfcAdapter;
                return true;
            }
        }
    }

    /**
     * 初始化nfc设置
     */
    public void NfcInit(BaseActivity activity) {
        mPendingIntent = PendingIntent.getActivity(activity, 0, new Intent(activity, activity.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter filter = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        IntentFilter filter2 = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        try {
            filter.addDataType("*/*");
        } catch (IntentFilter.MalformedMimeTypeException e) {
            e.printStackTrace();
        }
        mIntentFilter = new IntentFilter[]{filter, filter2};
        mTechList = null;
    }

    /**
     * 读取NFC的数据
     */
    public String readNFCFromTag(Intent intent) {
        Parcelable[] rawArray = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
        String readResult = null;
        if (rawArray != null) {
            NdefMessage mNdefMsg = (NdefMessage) rawArray[0];
            NdefRecord mNdefRecord = mNdefMsg.getRecords()[0];
            if (mNdefRecord != null) {
                try {
                    readResult = new String(mNdefRecord.getPayload(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }

        if(!TextUtils.isEmpty(readResult) && readResult.contains("\u0002zh")){
            readResult = readResult.split("\u0002zh")[1];
        }

        return readResult;
    }


    /**
     * 往nfc写入数据
     */
    public boolean writeNFCToTag(String data, Intent intent) {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        Ndef ndef = Ndef.get(tag);
        try {
            ndef.connect();
            NdefRecord ndefRecord = NdefRecord.createTextRecord(null, data);
            NdefRecord[] records = {ndefRecord};
            NdefMessage ndefMessage = new NdefMessage(records);
            ndef.writeNdefMessage(ndefMessage);
            return true ;
        }catch (Exception e){
            e.printStackTrace();
            return false ;
        }
    }

    /**
     * 读取nfcID
     */
    public String readNFCId(Intent intent) throws UnsupportedEncodingException {
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        String id = ByteArrayToHexString(tag.getId());
        return id;
    }

    /**
     * 将字节数组转换为字符串
     */
    private String ByteArrayToHexString(byte[] inarray) {
        int i, j, in;
        String[] hex = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        String out = "";

        for (j = 0; j < inarray.length; ++j) {
            in = (int) inarray[j] & 0xff;
            i = (in >> 4) & 0x0f;
            out += hex[i];
            i = in & 0x0f;
            out += hex[i];
        }
        return out;
    }

    /**
     * 获取对应名字
     * @param type
     * @return
     */
    public static String getNfcTypeName(Context context , int type){
        String value = context.getString(R.string.text_nfc_read);
        switch (type){
            case Constant.Port_Tug:
                value = context.getString(R.string.text_tug);
                break;
            case Constant.Port_Pallet:
                value = context.getString(R.string.text_pallet);
                break;
            case Constant.Leave_Port_Warehousing_Channel:
                value = context.getString(R.string.text_leave_in_channel);
                break;
            case Constant.Leave_Port_Duplicate_Channel:
                value = context.getString(R.string.text_leave_out_channel);
                break;
            case Constant.Leave_Port_Area:
                value = context.getString(R.string.text_leave_storage);
                break;
            case Constant.Enter_Port_Take_Channel:
                value = context.getString(R.string.text_enter_out_channel);
                break;
            case Constant.Enter_Port_Area:
                value = context.getString(R.string.text_enter_storage);
                break;
        }
        return value;
    }
}
package com.example.terminal.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BluetoothBootBroadcastReceiver extends BroadcastReceiver {

    private static final boolean DBG = true;
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            if (DBG) {
                Intent toIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                context.startActivity(toIntent);
            }
        }
    }
}

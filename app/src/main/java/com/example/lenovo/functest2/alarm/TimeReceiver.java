package com.example.lenovo.functest2.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class TimeReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        System.out.println("recevice");
        context.startService(new Intent(context,NotifyService.class));
    }
}
package com.example.tiktokapp.utils;

import android.content.Context;
import android.content.Intent;

public class IntentUtil {
    public static void changeActivity(Context context, Class<?> toActivity) {
        Intent intent = new Intent(context, toActivity);
        context.startActivity(intent);
    }
}

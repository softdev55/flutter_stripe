package com.facebook.react.bridge;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.PluginRegistry;

import java.util.ArrayList;

public class ReactContextBaseJavaModule implements PluginRegistry.ActivityResultListener {
    protected final Activity activity;

    private final ArrayList<ActivityEventListener> eventListeners = new ArrayList<>();

    protected ReactContextBaseJavaModule(Activity activity) {
        this.activity = activity;
    }

    protected Context getReactApplicationContext() {
        return activity;
    }

    protected Activity getCurrentActivity() {
        return activity;
    }

    protected void addActivityEventListener(ActivityEventListener listener) {
        eventListeners.add(listener);
    }

    protected void removeActivityEventListener(ActivityEventListener listener) {
        eventListeners.remove(listener);
    }

    public String getName() {
        return "StripeSdk";
    }

    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        for (ActivityEventListener eventListener : eventListeners) {
            eventListener.onActivityResult(activity, requestCode, resultCode, data);
        }
        return false;
    }
}

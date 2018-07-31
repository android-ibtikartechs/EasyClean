package com.ibtikar.app.easyclean.utilities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import java.util.Locale;

import static android.content.Context.MODE_MULTI_PROCESS;

public class LanguageHelper {
    /**
     * Updates default language with forced one
     */
    @SuppressLint("ApplySharedPref")
    public static Context updateLanguage(Context ctx, String lang) {
        Locale locale = null;
        locale = getLocale(lang);
        return setLocale(ctx, locale);
    }

    private static Context setLocale(Context context, Locale locale) {
        Configuration configuration = context.getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
            context.createConfigurationContext(configuration);

        } else {
            configuration.locale = locale;
            context.getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());
        }
        return context;
    }

    /**
     * Checks country AND region
     */
    public static Locale getLocale(String lang) {
        if (lang.contains("_")) {
            return new Locale(lang.split("_")[0], lang.split("_")[1]);
        } else {
            return new Locale(lang);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @NonNull
    public static String getLocalizedString(Context context, String desiredLocale, int resourceId) {
        Configuration conf = context.getResources().getConfiguration();
        conf = new Configuration(conf);
        conf.setLocale(getLocale(desiredLocale));
        Context localizedContext = context.createConfigurationContext(conf);
        return localizedContext.getResources().getString(resourceId);
    }

}

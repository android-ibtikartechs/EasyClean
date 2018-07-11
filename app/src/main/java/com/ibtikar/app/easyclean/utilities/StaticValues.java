package com.ibtikar.app.easyclean.utilities;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;

import java.util.Locale;

public class StaticValues {
    public static String URL_AUOTHORITY = "easyclean.ibtikarprojects.com";
    public static String KEY_CLEANER_ID = "KEY_CLEANER_ID";
    public static String KEY_CATEGORIES = "KEY_CATEGORIES";
    public static int FLAG_NORMAL_SERVICE_TYPE = 100;
    public static int FLAG_Fast_SERVICE_TYPE = 101;







    public static ContextWrapper changeLang(Context context, String lang_code) {
        Locale sysLocale;

        Resources rs = context.getResources();
        Configuration config = rs.getConfiguration();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sysLocale = config.getLocales().get(0);
        } else {
            sysLocale = config.locale;
        }
        if (!lang_code.equals("") && !sysLocale.getLanguage().equals(lang_code)) {
            String country = "ARE";
            Locale locale = new Locale(lang_code,country);
            Locale.setDefault(locale);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                config.setLocale(locale);
            } else {
                config.locale = locale;
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                config.setLayoutDirection(locale);
                context = context.createConfigurationContext(config);
            } else {
                context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
            }

        }

        return new ContextWrapper(context);
    }
}

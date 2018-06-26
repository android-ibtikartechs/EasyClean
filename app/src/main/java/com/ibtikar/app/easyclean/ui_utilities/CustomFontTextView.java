package com.ibtikar.app.easyclean.ui_utilities;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.ibtikar.app.easyclean.R;

public class CustomFontTextView extends android.support.v7.widget.AppCompatTextView {
    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        applyCustomFont(context, attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        applyCustomFont(context, attrs);
    }

    private void applyCustomFont(Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(
                attrs,
                R.styleable.CustomFontTextView);

        String fontName = attributeArray.getString(R.styleable.CustomFontTextView_custom_font);
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL);

        Typeface customFont = selectTypeface(context, fontName, textStyle);
        setTypeface(customFont);

        attributeArray.recycle();
    }

    private Typeface selectTypeface(Context context, String fontName, int textStyle) {
        if (fontName.contentEquals(context.getString(R.string.font_name_SFUIDisplay_Medium))) {
            return FontCache.getTypeface("fonts/SFUIDisplay_Medium.ttf", context);
        } else if (fontName.contentEquals(context.getString(R.string.font_name_SFUIDisplay_Light))) {
            return FontCache.getTypeface("fonts/SFUIDisplay-Light.ttf", context);
        }
        else if (fontName.contentEquals(context.getString(R.string.font_name_SFUIDisplay_Bold))) {
            return FontCache.getTypeface("fonts/SFUIDisplay-Bold.ttf", context);
        }
        else if (fontName.contentEquals(context.getString(R.string.font_name_SFUIDisplay_Regular))) {
            return FontCache.getTypeface("fonts/SFUIDisplay-Regular.ttf", context);
        }
        else if (fontName.contentEquals(context.getString(R.string.font_name_Cairo_Black))) {
            return FontCache.getTypeface("fonts/Cairo-Black.ttf", context);
        }
        else if (fontName.contentEquals(context.getString(R.string.font_name_Cairo_Bold))) {
            return FontCache.getTypeface("fonts/Cairo-Bold.ttf", context);
        }
        else if (fontName.contentEquals(context.getString(R.string.font_name_Cairo_ExtraLight))) {
            return FontCache.getTypeface("fonts/Cairo-ExtraLight.ttf", context);
        }

        else if (fontName.contentEquals(context.getString(R.string.font_name_Cairo_Light))) {
            return FontCache.getTypeface("fonts/Cairo-Light.ttf", context);
        }
        else if (fontName.contentEquals(context.getString(R.string.font_name_Cairo_Regular))) {
            return FontCache.getTypeface("fonts/Cairo-Regular.ttf", context);
        }
        else if (fontName.contentEquals(context.getString(R.string.font_name_Cairo_SemiBold))) {
            return FontCache.getTypeface("fonts/Cairo-SemiBold.ttf", context);
        }
        else {
            // no matching font found
            // return null so Android just uses the standard font (Roboto)
            return null;
        }
    }

}

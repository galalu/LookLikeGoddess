package com.anddev.ndg.looklikegoddess.ui;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.widget.TextView;

public class StringUtils {
    public static String getFullFio(String firstName, String middleName, String lastName) {
        String fio = "";

        if (lastName == null || lastName.length() == 0) {
            lastName = " ";
        }

        if (firstName == null || firstName.length() == 0) {
            firstName = " ";
        }

        if (middleName == null || middleName.length() == 0) {
            middleName = " ";
        }

        fio = String.format("%1$s %2$s %3$s", lastName, firstName, middleName).trim();
        return fio;
    }

    public static String getFioAndSpec(String firstName, String middleName, String lastName, String specialization) {
        return getShortFio(firstName, middleName, lastName) + ", " + specialization.toLowerCase();
    }

    public static String getShortFio(String firstName, String middleName, String lastName) {
        String fio = "";

        if (lastName == null || lastName.length() == 0) {
            lastName = " ";
        }

        if (firstName == null || firstName.length() == 0) {
            firstName = " ";
        } else {
            firstName = firstName.substring(0, 1).toUpperCase() + ".";
        }

        if (middleName == null || middleName.length() == 0) {
            middleName = " ";
        } else {
            middleName = middleName.substring(0, 1).toUpperCase() + ".";
        }

        fio = String.format("%1$s %2$s %3$s", lastName, firstName, middleName);
        return fio;
    }

    public static void setTitleStyle(TextView textViewTitle) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            textViewTitle.setTypeface(textViewTitle.getTypeface(), Typeface.BOLD);
        }
    }

    /**
     * each word with a capital letter
     *
     * @param text
     * @return
     */
    public static String setInitCap(String text) {
        return setInitCap(text, true);
    }

    /**
     * each word with a capital letter or first word
     *
     * @param text
     * @param AllWords false - initCap only first word
     * @return
     */
    public static String setInitCap(String text, boolean AllWords) {
        String res = "";

        if (text == null) {
            return res;
        }

        switch (text.length()) {
            case 0:
                res = text;
                break;
            case 1:
                res = text.toUpperCase();
                break;
            default:
                if (AllWords) {
                    String[] arr = text.split(" ");
                    for (String s : arr) {
                        if (s != null && !s.isEmpty()) {
                            res = res + s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase() + " ";
                        }
                    }
                } else {
                    res = text.trim().substring(0, 1).toUpperCase() + text.trim().substring(1).toLowerCase();
                }
                break;
        }
        return res.trim();
    }

    public static String getNVLString(String value, String ifNull) {
        return (value == null) ? ifNull : value;
    }

    public static boolean isEmail(String entry) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(entry).matches();
    }

    public static boolean isPhone(String entry) {
        return android.util.Patterns.PHONE.matcher(entry).matches();
    }

    public static String getEllipsis() {
        return "...";
    }

    public static String getUnbreakableSpace() {
        return "\u00A0";
    }

    public static String getUpTriangle() {
        return "\u25B4";
    }

    public static String getDownTriangle() {
        return "\u25BE";
    }

    public static String getAsterix() {
        return "\u002A";
    }

    /**
     * Makes phone number valid for back-end.
     * Calling this method is necessary before invoking api methods in Login,
     * Registration and Forgot password activities.
     * Example: 88005553535 converts to 78005553535
     *
     * @param phone phone number to transform
     * @return valid phone number
     */
    public static String transformPhoneNumber(String phone) {
        StringBuilder phoneBuilder = new StringBuilder(phone);
        if (phoneBuilder.length() == 11) {
            if (phone.charAt(0) == '8') {
                phoneBuilder.replace(0, 1, "+7");
            }
        }
        return phoneBuilder.toString();
    }

    public static String setPhoneNumberMask(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            if (phoneNumber.length() == 11 && phoneNumber.charAt(0) == '8') {
                phoneNumber = transformPhoneNumber(phoneNumber);
            }
            StringBuilder phoneBuilder = new StringBuilder(phoneNumber);
            if (phoneBuilder.length() == 12 && phoneNumber.charAt(0) == '+') {
                phoneBuilder.insert(2, " (");
                phoneBuilder.insert(7, ") ");
                phoneBuilder.insert(12, "-");
                phoneBuilder.insert(15, "-");
            }
            return phoneBuilder.toString();
        }
        return null;
    }

    public static float getTextWidth(String text, int textSize, Typeface typeface) {
        Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setTextSize(textSize);
        p.setStyle(Paint.Style.STROKE);
        p.setTypeface(typeface);
        return p.measureText(text);
    }

    //clear begin and end symbols \n \r
    public static String trimN(String requestBody) {
        if (requestBody == null || requestBody.isEmpty()) {
            return requestBody;
        }

        String result = requestBody;

        String n = "\n";
        String r = "\r";

        while (result.startsWith(n) || result.startsWith(r)) {
            result = result.substring(n.length());
        }

        while (result.endsWith(n) || result.startsWith(r)) {
            result = result.substring(0, result.length() - n.length());
        }

        return result;
    }

}

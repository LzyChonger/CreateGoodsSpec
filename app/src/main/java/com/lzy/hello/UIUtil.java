package com.lzy.hello;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * Created by lzy.
 */

public class UIUtil {

    private static WeakReference<Toast> sToastRef = null;
    private static WeakReference<Dialog> sDialogRef;
    /**
     * 弹出短时间的吐司
     * toastShort
     * @param context
     * @param msg
     * @since 1.0
     */
    public static void toastShort(Context context, String msg) {
        toast(context, msg, Toast.LENGTH_SHORT);
    }

    /**
     * 弹出短时间的吐司
     * toastShort
     * @param context
     * @param resId
     * @since 1.0
     */
    public static void toastShort(Context context, int resId) {
        toast(context, context.getString(resId), Toast.LENGTH_SHORT);
    }


    private static void toast(Context context, String msg, int duration) {
        if (TextUtils.isEmpty(msg)) {
            return;
        }
        Toast t = null;
        if (sToastRef == null || sToastRef.get() == null) {
            t = Toast.makeText(context, msg, duration);
            sToastRef = new WeakReference<Toast>(t);
        } else {
            t = sToastRef.get();
            t.setText(msg);
            t.setDuration(duration);
        }
        t.show();
    }

    /**
     * 弹出长时间的吐司
     * toastLong
     * @param context
     * @param msg
     * @since 1.0
     */
    public static void toastLong(Context context, String msg) {
        toast(context, msg, Toast.LENGTH_LONG);
    }

    /**
     * 弹出长时间的吐司
     * toastLong
     * @param context
     * @param resId
     * @since 1.0
     */
    public static void toastLong(Context context, int resId) {
        toast(context, context.getString(resId), Toast.LENGTH_LONG);
    }

    /**
     * dip转换为px
     * dip2px
     * @param dpValue
     * @return
     * @since 1.0
     */

    public static int dip2px(int dpValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转换为dip
     * px2dip
     * @param pxValue
     * @return
     * @since 1.0
     */
    public static int px2dip(int pxValue) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 弹出confirm对话框
     * showConfirm
     * @param context
     * @param msg 内容
     * @param okListener
     * @since 1.0
     */
    public static Dialog showConfirm(Context context, String msg, final View.OnClickListener okListener) {
        return showConfirm(context, null, msg, null, okListener, null, null);
    }

    /**
     * 弹出confirm对话框
     * showConfirm
     * @param context
     * @param title 标题
     * @param msg 内容
     * @param okStr 确定按钮字符串
     * @param okListener 确定点击事件监听
     * @param cancelStr 取消按钮字符串
     * @param cancelListener 取消点击事件监听
     * @since 1.0
     */
    public static Dialog showConfirm(final Context context, String title, String msg, String okStr,
                                     final View.OnClickListener okListener, String cancelStr, final View.OnClickListener cancelListener) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_confirm, null);
        final Dialog dialog = showAlert(context, view);
        TextView titleText = (TextView) view.findViewById(R.id.tv_confirm_title);
        TextView msgText = (TextView) view.findViewById(R.id.tv_confirm_msg);
        Button leftBtn = (Button) view.findViewById(R.id.btn_confirm_left);
        Button rightBtn = (Button) view.findViewById(R.id.btn_confirm_right);
        titleText.setText(TextUtils.isEmpty(title) ? "暂无相关信息" : title);
        msgText.setText(msg);
        leftBtn.setText(TextUtils.isEmpty(cancelStr) ?"取消" : cancelStr);
        rightBtn.setText(TextUtils.isEmpty(okStr) ? "确定" : okStr);
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (cancelListener != null) {
                    cancelListener.onClick(view);
                }
            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (okListener != null) {
                    okListener.onClick(view);
                }
            }
        });
        return dialog;
    }


    /**
     * 显示自定义弹窗
     * @param context
     * @param view
     * @return
     */
    public static Dialog showAlert(final Context context, View view) {
        return showAlert(context, view, 0);
    }

    public static Dialog showAlert(final Context context, View view, int width) {
        Dialog dialog = new Dialog(context, R.style.FullScreenDialog);
        // 显示宽度为屏幕的5/6
        if(width == 0){
            width = getScreenWidth() * 5 / 6;
        }
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setContentView(view, params);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);

        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER);

        if (context instanceof Activity) {// 判断状态
            Activity activity = (Activity) context;
            if (!activity.isFinishing()) {
                dialog.show();
            }
        }
        return dialog;
    }


    public static void hideSoftInput(Context context, View view) {
        //隐藏键盘时取消对etcommenter的监听;
        //  mEt_sendmessage.removeTextChangedListener(new MyTextWatcher(ivCommentSend));
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    public static int getScreenWidth() {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return metrics.widthPixels;
    }
}

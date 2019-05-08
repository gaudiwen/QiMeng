package com.cndatacom.qmhz.network.rxjava;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cndatacom.qmhz.R;

public class ProgressDialog extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_progress, null);
        Dialog dialog = new Dialog(getActivity(), R.style.pr_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);

        // 设置宽度为屏宽、位置靠近屏幕底部
        Window window = dialog.getWindow();
        window.setBackgroundDrawableResource(R.color.colorAccent);
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.dimAmount = 0;
        window.setAttributes(wlp);
        return dialog;
    }

    public interface OnDismissListener{
        void dismiss();
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {

        mOnDismissListener = onDismissListener;
    }

    private OnDismissListener mOnDismissListener;


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(mOnDismissListener != null){
            mOnDismissListener.dismiss();
        }
    }
}

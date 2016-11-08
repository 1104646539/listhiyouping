package com.lishi.baijiaxing.utils;


import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import com.lishi.baijiaxing.R;


/**
 * 通用dialog弹出层
 *
 * @author ouyangbin
 */
public class UniversalDialogFragment extends DialogFragment {
    private String details = null, title = null, strBtn1 = null, strBtn2 = null, strBtn3 = null;
    private OnClickChooseListen chooseListen;

    public static UniversalDialogFragment getInstance() {
        UniversalDialogFragment dialogFragment = new UniversalDialogFragment();
        return dialogFragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Builder builder = new Builder(getActivity(), R.style.AppTheme);
        if (details==null) {
            builder.setMessage("请设置提示内容!");
        } else {
            builder.setMessage(details);
        }
        if (title==null) {
            builder.setTitle("系统提示");
        } else {
            builder.setTitle(title);
        }
        if (strBtn1!=null) {
            builder.setPositiveButton(strBtn1,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 告诉给activity
                            setWhich(strBtn1);
                            dismiss();
                        }
                    });
        }
        if (strBtn2!=null) {
            builder.setNeutralButton(strBtn2,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 告诉给activity
                            setWhich(strBtn2);
                            dismiss();
                        }
                    });
        }
        if (strBtn3!=null) {
            builder.setNegativeButton(strBtn3,
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 告诉给activity
                            setWhich(strBtn3);
                            dismiss();
                        }
                    });
        }
        return builder.create();
    }

    public void setStrBtn1(String strBtn1) {
        this.strBtn1 = strBtn1;
    }

    public void setStrBtn2(String strBtn2) {
        this.strBtn2 = strBtn2;
    }

    public void setStrBtn3(String strBtn3) {
        this.strBtn3 = strBtn3;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private void setWhich(String which) {
        if (chooseListen != null) {
            chooseListen.onCall(UniversalDialogFragment.this, which);
        } else {
            T.ShowToastForShort(getActivity(),
                    "activity未继承接口,chooseListen为null");
        }
    }

    /**
     * 回调接口
     */
    public interface OnClickChooseListen {
        void onCall(DialogFragment dialog, String which);
    }

    public void setOnClickChooseListen(OnClickChooseListen chooseListen) {
        this.chooseListen = chooseListen;
    }
}

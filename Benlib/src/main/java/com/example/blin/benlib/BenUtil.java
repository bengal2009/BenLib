package com.example.blin.benlib;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Lin on 2015/3/7.
 */
public class BenUtil {
    String TAG = "BenUtil";
    public void Bendialog1(Context context,String PrmptStr) {
         final  Context mcontext;
        mcontext=context;
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
        builder.setMessage(PrmptStr);
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG,"Yes");
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }
    public void Dialog_Input(Context context){
        final  Context mcontext;
        mcontext=context;
        new AlertDialog.Builder(mcontext).setTitle("请输入").setIcon(
                android.R.drawable.ic_dialog_info).setView(
                new EditText(mcontext)).setPositiveButton("确定", null)
                .setNegativeButton("取消", null).show();

    }

    public void Dialog_3B(Context context){
        final  Context mcontext;
        mcontext=context;
        Dialog dialog = new AlertDialog.Builder(mcontext).setIcon(
                android.R.drawable.btn_star).setTitle("喜好调查").setMessage(
                "你喜欢李连杰的电影吗？").setPositiveButton("很喜欢",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO Auto-generated method stub
                        Toast.makeText(mcontext, "我很喜欢他的电影。",
                                Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("不喜欢", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(mcontext, "我不喜欢他的电影。", Toast.LENGTH_LONG)
                        .show();
            }
        }).setNeutralButton("一般", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                Toast.makeText(mcontext, "谈不上喜欢不喜欢。", Toast.LENGTH_LONG)
                        .show();
            }
        }).create();
        dialog.show();

    }


}

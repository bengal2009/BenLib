package com.example.blin.benlib;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

//http://javatechig.com/android/android-dialog-example/
//http://javatechig.com/android/android-input-dialog-example
/**
 * Created by Lin on 2015/3/7.
 */
public class BenUtil {
    String TAG = "BenUtil";
//    Dial Declare
    public void Bendialog1(Context context,String PrmptStr) {
        final  Context mcontext;
        mcontext=context;
        AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
        builder.setMessage(PrmptStr);
        builder.setTitle("提示");
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Log.i(TAG, "Yes");
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
    public void Dialog_Input(Context context){
        final  Context mcontext=context;
/*
LayoutInflater inflater = getLayoutInflater();
　　   View layout = inflater.inflate(R.layout.dialog,
　　     (ViewGroup) findViewById(R.id.dialog));
　　   new AlertDialog.Builder(this).setTitle("自定义布局").setView(layout)

 */
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Alert Dialog With EditText"); //Set Alert dialog title here
        alert.setMessage("Enter Your Name Here"); //Message here

        // Set an EditText view to get user input
        final EditText input = new EditText(context);
        alert.setView(input);

        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int whichButton) {
                //You will get as string input data in this variable.
                // here we convert the input to a string and show in a toast.
                String srt = input.getEditableText().toString();
                Toast.makeText(mcontext, srt, Toast.LENGTH_LONG).show();
            } // End of onClick(DialogInterface dialog, int whichButton)
        }); //End of alert.setPositiveButton
        alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
                dialog.cancel();
            }
        }); //End of alert.setNegativeButton
        AlertDialog alertDialog = alert.create();
        alertDialog.show();

    }
    public  void BenAnimateText(Context context,TextSwitcher mTextSwitcher)
    {
//        final TextSwitcher mTextSwitcher;
//        http://javatechig.com/android/textswitcher-and-imageswitcher-example-in-android
        final  Context mcontext=context;
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(mcontext);
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });

        mTextSwitcher.setInAnimation(mcontext ,android.R.anim.fade_in);
        mTextSwitcher.setOutAnimation(mcontext ,android.R.anim.fade_out);
    }


    //getting unique id for device
    public String SystetemID(Context mcontext) {

        String id = Settings.Secure.getString(mcontext.getContentResolver(), Settings.Secure.ANDROID_ID);
        return id;
    }
}

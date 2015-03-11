package com.example.blin.benlib;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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

    //添加通知到顶部任务栏
    public void AddNotification(Context mcontext,int ID){
        //添加通知到顶部任务栏
        //获得NotificationManager实例
        String service = mcontext.NOTIFICATION_SERVICE;
        NotificationManager nm = (NotificationManager) mcontext.getSystemService(service);
        //实例化Notification
        Notification n = new Notification();
        //设置显示图标
        //设置提示信息
        String tickerText ="我的程序";
        //显示时间
        long when = System.currentTimeMillis();

        n.tickerText = tickerText;
        n.when = when;
        //显示在“正在进行中”
        //  n.flags = Notification.FLAG_ONGOING_EVENT;
        n.flags|=Notification.FLAG_AUTO_CANCEL; //自动终止
        //实例化Intent
//        Intent it = new Intent(mcontext,mcontext.getClass());
        Intent it = new Intent(mcontext,mcontext.getClass());
//        it.putExtra(KEY_COUNT, count);
        /*********************
         *获得PendingIntent
         *FLAG_CANCEL_CURRENT:
         *		如果当前系统中已经存在一个相同的PendingIntent对象，
         *		那么就将先将已有的PendingIntent取消，然后重新生成一个PendingIntent对象。
         *FLAG_NO_CREATE:
         *		如果当前系统中不存在相同的PendingIntent对象，
         *		系统将不会创建该PendingIntent对象而是直接返回null。
         *FLAG_ONE_SHOT:
         *		该PendingIntent只作用一次，
         *		如果该PendingIntent对象已经触发过一次，
         *		那么下次再获取该PendingIntent并且再触发时，
         *		系统将会返回一个SendIntentException，在使用这个标志的时候一定要注意哦。
         *FLAG_UPDATE_CURRENT:
         *		如果系统中已存在该PendingIntent对象，
         *		那么系统将保留该PendingIntent对象，
         *		但是会使用新的Intent来更新之前PendingIntent中的Intent对象数据，
         *		例如更新Intent中的Extras。这个非常有用，
         *		例如之前提到的，我们需要在每次更新之后更新Intent中的Extras数据，
         *		达到在不同时机传递给MainActivity不同的参数，实现不同的效果。
         *********************/

        PendingIntent pi = PendingIntent.getActivity(mcontext, 0, it, PendingIntent.FLAG_UPDATE_CURRENT);

        //设置事件信息，显示在拉开的里面
        n.setLatestEventInfo(mcontext,"我的软件", "我的软件正在运行……", pi);
        //发出通知
        nm.notify(ID,n);
    }

    //getting unique id for device
    public String SystetemID(Context mcontext) {

        String id = Settings.Secure.getString(mcontext.getContentResolver(), Settings.Secure.ANDROID_ID);
        return id;
    }
}

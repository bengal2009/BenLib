package com.example.blin.benlib;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

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
    public void ResolutionInfo(Activity Act1)
    {
        DisplayMetrics metrics = new DisplayMetrics();
        Act1.getWindowManager().getDefaultDisplay()
                .getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        Log.i(TAG,"width:"+Integer.toString(width)+",Height:"+Integer.toString(height));

    }

    public void ShareIntent(Context mcontext) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        mcontext.startActivity(sendIntent);
    }

    //添加通知到顶部任务栏
    public void AddNotification(Context mcontext,int ID1){

        // Use NotificationCompat.Builder to set up our notification.
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mcontext);

        //icon appears in device notification bar and right hand corner of notification
//        builder.setSmallIcon(R.drawable.ic_stat_notification);

        // This intent is fired when notification is clicked
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://javatechig.com/"));
        PendingIntent pendingIntent = PendingIntent.getActivity(mcontext, 0, intent, 0);

        // Set the intent that will fire when the user taps the notification.
        builder.setContentIntent(pendingIntent);

        // Large icon appears on the left of the notification
//        builder.setLargeIcon(BitmapFactory.decodeResource(mcontext.getResources(), R.drawable.ic_launcher));

        // Content title, which appears in large type at the top of the notification
        builder.setContentTitle("Notifications Title");

        // Content text, which appears in smaller text below the title
        builder.setContentText("Your notification content here.");

        // The subtext, which appears under the text on newer devices.
        // This will show-up in the devices with Android 4.2 and above only
        builder.setSubText("Tap to view documentation about notifications.");

        NotificationManager notificationManager = (NotificationManager) mcontext.getSystemService(mcontext.NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(ID1, builder.build());
        Log.i(TAG, "Finish");
    }

    //getting unique id for device
    public String SystetemID(Context mcontext) {

        String id = Settings.Secure.getString(mcontext.getContentResolver(), Settings.Secure.ANDROID_ID);
        return id;
    }

    private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
        ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
        PackageManager packageManager = null;
        for (ApplicationInfo info : list) {
            try {
                if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                    applist.add(info);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return applist;
    }

}

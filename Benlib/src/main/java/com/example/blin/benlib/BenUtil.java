package com.example.blin.benlib;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Environment;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

//http://javatechig.com/android/android-dialog-example/
//http://javatechig.com/android/android-input-dialog-example
//http://www.tutorialspoint.com/android/android_notifications.htm
//http://www.tutorialspoint.com/android/android_push_notification.htm
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
        int DPI = metrics.densityDpi;
        Log.i(TAG,"width:"+Integer.toString(width)+",Height:"+Integer.toString(height)+",DPI:"+Integer.toString(DPI));

    }

    private void saveImagetoSDCard(Bitmap bitmap)
    {

        try
        {
            String file_path = Environment.getExternalStorageDirectory().getAbsolutePath();
            File storagePath = new File(Environment.getExternalStorageDirectory() + "/MyCameraApp/");
            File file = new File(storagePath, "Yudiz_krrish.png");

            Log.d("TAG","File path after editing :"+file.getPath().toString());
            FileOutputStream fOut = null;

            fOut = new FileOutputStream(file);
//            bit.compress(Bitmap.CompressFormat.PNG, 85, fOut);

            fOut.flush();
            fOut.close();
//            btn_changeSettiong.setVisibility(View.VISIBLE);
//            btn_saveImage.setVisibility(View.VISIBLE);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public static Bitmap captureScreenshot(Activity activity) {
        View view = activity.getWindow().getDecorView();
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        Bitmap bitmap = view.getDrawingCache();
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        int statusBarHeight = rect.top;
        @SuppressWarnings("deprecation")
        int width = activity.getWindowManager().getDefaultDisplay().getWidth();
        @SuppressWarnings("deprecation")
        int height = activity.getWindowManager().getDefaultDisplay().getHeight();
        Bitmap bitmap2 = Bitmap.createBitmap(bitmap, 0, statusBarHeight, width, height - statusBarHeight);
        view.destroyDrawingCache();
        return bitmap2;
    }
    public void ShareIntent(Context mcontext) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
        sendIntent.setType("text/plain");
        mcontext.startActivity(sendIntent);
    }

    //添加通知到顶部任务栏
    public void AddNotification(Context mcontext,
                                String title,
                                String subject,
                                String body,
                                int ID1){
        NotificationManager NM;
        NM=(NotificationManager) mcontext.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification(android.R.drawable.
                stat_notify_more,title,System.currentTimeMillis());
        PendingIntent pending=PendingIntent.getActivity(
                mcontext,0, new Intent(),0);
        notify.setLatestEventInfo(mcontext,subject,body,pending);
        NM.notify(ID1, notify);
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

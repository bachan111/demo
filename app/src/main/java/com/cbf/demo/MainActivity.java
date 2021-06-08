package com.cbf.demo;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivityAAA";
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.aaaaaa);

//        Guid guid = new Guid(false);

        getRealScreenRelatedInformation(this);
        getScreenRelatedInformation(this);
    }

    private void getSize(Context activity){
        float density = activity.getResources().getDisplayMetrics().density;
        int densityDpi = activity.getResources().getDisplayMetrics().densityDpi;
        float scaledDensity = activity.getResources().getDisplayMetrics().scaledDensity;

        float xdpi = activity.getResources().getDisplayMetrics().xdpi;
        float ydpi = activity.getResources().getDisplayMetrics().ydpi;
        int width = activity.getResources().getDisplayMetrics().widthPixels;
        int height = activity.getResources().getDisplayMetrics().heightPixels;

        // 这样可以计算屏幕的物理尺寸
        float width2 = (width / xdpi) * (width / xdpi);
        float height2 = (height / ydpi) * (width / xdpi);
        float screenSize = (float) Math.sqrt(width2 + height2);
//        resuleVaule =  (value/height2)*;///(height2)

        Log.e("screenSize", "densityDpi { " + densityDpi + " } " +
                "scaledDensity { " + scaledDensity + " } " +
                "屏幕密度（0.75 / 1.0 / 1.5） { " + density + " } " +
                "xdpi { " + xdpi + " } " +
                "ydpi { " + ydpi + " } " +
                "屏幕宽度（像素） { " + width + " } " +
                "屏幕高度（像素） { " + height + " } " +
                "物理尺寸高（厘米） { " + height2 * 2.54 + " } " +
                "物理尺寸宽（厘米） { " + width2 * 2.54 + " } " +
                "screenSize { " + screenSize + " }");

    }

    public static void getScreenRelatedInformation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(outMetrics);
            int widthPixels = outMetrics.widthPixels;
            int heightPixels = outMetrics.heightPixels;
            int densityDpi = outMetrics.densityDpi;
            float density = outMetrics.density;
            float scaledDensity = outMetrics.scaledDensity;
            //可用显示大小的绝对宽度（以像素为单位）。
            //可用显示大小的绝对高度（以像素为单位）。
            //屏幕密度表示为每英寸点数。
            //显示器的逻辑密度。
            //显示屏上显示的字体缩放系数。
            Log.d("display", "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels + "\n" +
                    ",densityDpi = " + densityDpi + "\n" +
                    ",density = " + density + ",scaledDensity = " + scaledDensity);
        }
    }

    public  void getRealScreenRelatedInformation(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (windowManager != null) {
            DisplayMetrics outMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getRealMetrics(outMetrics);
            int widthPixels = outMetrics.widthPixels;
            int heightPixels = outMetrics.heightPixels;
            int densityDpi = outMetrics.densityDpi;
            float density = outMetrics.density;
            float scaledDensity = outMetrics.scaledDensity;

            float xdpi = outMetrics.xdpi;
            float ydpi = outMetrics.ydpi;

            float width2 = (widthPixels / xdpi) * (widthPixels / xdpi);
            float height2 = (heightPixels / ydpi) * (widthPixels / xdpi);

            float screenSize = (float) Math.sqrt(width2 + height2);
            //可用显示大小的绝对宽度（以像素为单位）。
            //可用显示大小的绝对高度（以像素为单位）。
            //屏幕密度表示为每英寸点数。
            //显示器的逻辑密度。
            //显示屏上显示的字体缩放系数。
            Log.e("display", "widthPixels = " + widthPixels + ",heightPixels = " + heightPixels + "\n" +
                    ",densityDpi = " + densityDpi + "\n" +
                    ",density = " + density + ",scaledDensity = " + scaledDensity+
                    "\n xdpi:"+xdpi+" ydpi:"+ydpi+
                    "\n width2:"+width2+" height2:"+height2+
                    "screenSize:"+screenSize);


//            final double resuleVaule = (Math.tan(Math.toRadians(2)) * 2 * 30 * densityDpi) / ( density);
            final double resuleVaule = densityDpi*(Math.tan(Math.toRadians(8)) * 2 * 30)/2.45;
            Glide.with(this)
                    .load(R.mipmap.a)
                    .asBitmap()//Glide返回一个Bitmap对象
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                            if (null != imageView) {
                                ViewGroup.LayoutParams params = imageView.getLayoutParams();
//                            params.width = Math.round(bitmap.getWidth() * multiple);
//                            params.height = Math.round(bitmap.getHeight() * multiple);
                                params.height = (int) resuleVaule;
                                params.width = (int) ((resuleVaule / bitmap.getHeight()) * bitmap.getWidth());
                                imageView.setImageBitmap(bitmap);
                                Log.e("ASASAS", "height: " + params.height + " ；width：" + params.width);
                            }
                        }
                    });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}

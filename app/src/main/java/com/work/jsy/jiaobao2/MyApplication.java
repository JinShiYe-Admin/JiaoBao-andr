package com.work.jsy.jiaobao2;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;



import java.io.File;

/**
 * 
* @ClassName: MyApplication 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yiw
* @date 2015-12-28 下午4:21:08 
*
 */
public class MyApplication extends Application {
	// 默认存放图片的路径
	public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory() + File.separator + "CircleDemo" + File.separator + "Images"
				+ File.separator;
	private static Context mContext;


	@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
		//mContext = getApplicationContext();
		Log.i("MyApplication",MyApplication.getContext()+"");
		//LeakCanary.install(this);
//        QPManager.getInstance(mContext).initRecord();
	}

	public static Context getContext(){
		return mContext;
	}
}

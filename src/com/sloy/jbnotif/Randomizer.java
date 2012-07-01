package com.sloy.jbnotif;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Randomizer {
	
	public static final String[] IMAGES = new String[]{"jelly.png","io.png","design.png","play.png","pitufina.png","bob.png"};
	
	public static final Random rand = new Random();
	
	private Context mContext;
	private List<Bitmap> mBitmaps;
	public Randomizer(Context ctx){
		mContext = ctx;
		mBitmaps = new ArrayList<Bitmap>();
		//fill it, baby
		for(String name : IMAGES){
			try{
				mBitmaps.add(BitmapFactory.decodeStream(ctx.getAssets().open(name)));
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public Bitmap getRandomImage(){
		return mBitmaps.get(rand.nextInt(mBitmaps.size()));
	}

}

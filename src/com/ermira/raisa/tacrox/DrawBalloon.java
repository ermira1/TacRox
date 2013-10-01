package com.ermira.raisa.tacrox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawBalloon extends SurfaceView {
	
	Bitmap balloon;
	SurfaceHolder myHolder;
	public DrawBalloon(Context context) {
		super(context);
		balloon = BitmapFactory.decodeResource(getResources(), R.drawable.balloon);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		//canvas.drawBitmap(balloon,150, 350,null);
	}
	
	

}

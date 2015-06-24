package com.androidlab.animationtricks;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.View;

public class PlainGeometryView  extends View{	
	Point mPoint1;
	Point mPoint2;
	Point mPoint3;
	
	Point mCircleCenter;
	int mCircleRadius;
	
	int mWidth;
	int mHeight;
	
	Paint mPaint;
	
	public PlainGeometryView(Context context){
		super(context);
		
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setColor(Color.BLACK);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		// Note: generate points only once
		if (mPoint1 == null &&
			mPoint2 == null &&
			mPoint3 == null) {
			calculateCoordinates();
		}  
		
		drawBackground(canvas);
		drawTriangle(canvas);
		drawExternalCircle(canvas);
	}
	
	private void drawBackground(Canvas canvas) {
		canvas.drawPaint(mPaint);
	}
	
	private void drawTriangle(Canvas canvas) {
		mPaint.setColor(Color.WHITE);
		
		canvas.drawPoint(mPoint1.x, mPoint1.y, mPaint);
		canvas.drawPoint(mPoint2.x, mPoint2.y, mPaint);
		canvas.drawPoint(mPoint3.x, mPoint3.y, mPaint);
		
		canvas.drawLine(mPoint1.x, mPoint1.y, mPoint2.x, mPoint2.y, mPaint);
		canvas.drawLine(mPoint2.x, mPoint2.y, mPoint3.x, mPoint3.y, mPaint);
		canvas.drawLine(mPoint3.x, mPoint3.y, mPoint1.x, mPoint1.y, mPaint);
	}
	
	private void drawExternalCircle(Canvas canvas) {
		
	}
	
	private void calculateCoordinates() {
		mWidth = getWidth();
		mHeight = getHeight();

		initPoints();
		calculateExternalCircle();
	}
	
	private void initPoints() {
		mPoint1 = generateRandomPoint();
		mPoint2 = generateRandomPoint();
		mPoint3 = generateRandomPoint();
	}
	
	private Point generateRandomPoint() {
		Random generator = new Random();
		int x = generator.nextInt(mWidth - 200) + 100;
		int y = generator.nextInt(mHeight - 200) + 100;
		
		return new Point(x, y);
	}
	
	private void calculateExternalCircle() {
		
		
	}
   
   

}

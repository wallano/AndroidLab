package com.androidlab.animationtricks;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.view.View;

public class PlainGeometryView  extends View{	
	PointF mPoint1;
	PointF mPoint2;
	PointF mPoint3;
	
	Circle mExternalCircle;
	Circle mCustomCircle;
	
	int mWidth;
	int mHeight;
	
	Paint mPaint;
	
	public PlainGeometryView(Context context){
		super(context);
		
		mPaint = new Paint();
		mPaint.setStyle(Paint.Style.FILL);
		mPaint.setStrokeWidth(2);
		mPaint.setColor(Color.BLUE);
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
		drawCustomCircle(canvas);
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
		mPaint.setStyle(Style.STROKE);		
		mPaint.setColor(Color.YELLOW);
		
		// Draw circle
		canvas.drawCircle(mExternalCircle.getCenter().x, 
						  mExternalCircle.getCenter().y, 
						  mExternalCircle.getRadius(), 
						  mPaint);

		// Draw center of circle
		canvas.drawCircle(mExternalCircle.getCenter().x, 
				  		  mExternalCircle.getCenter().y, 
				  		  2, 
				  		  mPaint);
	}
	
	void drawCustomCircle(Canvas canvas) {
		// Draw custom circle
		canvas.drawCircle(mCustomCircle.getCenter().x, 
						  mCustomCircle.getCenter().y, 
						  mCustomCircle.getRadius(), 
						  mPaint);
		
		mPaint.setColor(Color.RED);
		
		float startX = (float)(mCustomCircle.getCenter().x + mCustomCircle.getRadius()*Math.cos(0));
		float startY = (float)(mCustomCircle.getCenter().y + mCustomCircle.getRadius()*Math.sin(0));
		
		PointF startBoundaryPoint = new PointF(startX, startY);
		
		canvas.drawLine(mCustomCircle.getCenter().x, 
						mCustomCircle.getCenter().y,
						startBoundaryPoint.x, 
						startBoundaryPoint.y, 
						mPaint);

		mCustomCircle.FirstBoundaryPoint(startBoundaryPoint, 5);

		PointF nextPoint = mCustomCircle.NextBoundaryPoint();
		while (nextPoint != null) {
			canvas.drawLine(mCustomCircle.getCenter().x, 
							mCustomCircle.getCenter().y,
							nextPoint.x, 
							nextPoint.y, 
							mPaint);
			
			nextPoint = mCustomCircle.NextBoundaryPoint();
		}
	}
	
	private void calculateCoordinates() {
		mWidth = getWidth();
		mHeight = getHeight();

		initPoints();
		calculateExternalCircle();
		calculateCustomCircle();
	}
	
	private void initPoints() {
		mPoint1 = generateRandomPoint();
		mPoint2 = generateRandomPoint();
		mPoint3 = generateRandomPoint();
	}
	
	private PointF generateRandomPoint() {
		Random generator = new Random();
		float x = generator.nextInt(mWidth - 200) + 100;
		float y = generator.nextInt(mHeight - 200) + 100;
		
		return new PointF(x, y);
	}
	
	private void calculateExternalCircle() {
		mExternalCircle = new Circle(mPoint1, mPoint2, mPoint3);				
	}
	
	private void calculateCustomCircle() {
		mCustomCircle = new Circle(new PointF(50.0F, 50.0F), 40.0F);
	}
}

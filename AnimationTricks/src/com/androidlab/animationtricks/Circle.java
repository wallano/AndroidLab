package com.androidlab.animationtricks;

import android.graphics.PointF;

public class Circle {
	private PointF mCenter;
	private float mRadius;
	
	private float mPrevAngle;
	private float mAngleStep;
	
	public Circle(PointF center, float radius) {
		mCenter = center;
		mRadius = radius;
	}
	
	public Circle(PointF p1, PointF p2, PointF p3) {		
		Line line1 = new Line(p1, p2);
		Line line2 = new Line(p2, p3);
		
		float xCenter = calculateCenterX(line1, line2);
		float yCenter = calculateCenterY(line1, xCenter);
		
		mCenter = new PointF(xCenter, yCenter);
		mRadius = calculateRadius(mCenter, p1);
		
	}
	
	public PointF getCenter() {
		return mCenter;
	}
	
	public float getRadius() {
		return mRadius;
	}	
	
	public void FirstBoundaryPoint(PointF startBoundaryPoint, float angleStep) {
		// x = cx + r * cos(a)
		// y = cy + r * sin(a)
		mAngleStep = angleStep;
		
		float xa = (float)(Math.acos((startBoundaryPoint.x - mCenter.x)/mRadius)*180/Math.PI);
		float ya = (float)(Math.asin((startBoundaryPoint.y - mCenter.y)/mRadius)*180/Math.PI);
			
		mPrevAngle = xa;
	}
	
	public PointF NextBoundaryPoint() {
		if (mPrevAngle > 360)
			return null;

		float x = (float)(mCenter.x + mRadius * Math.cos((mPrevAngle + mAngleStep)* Math.PI/180F));
		float y = (float)(mCenter.y + mRadius * Math.sin((mPrevAngle + mAngleStep)* Math.PI/180F));
		
		mPrevAngle += mAngleStep;
		
		return new PointF(x, y);		
	}
	
	private float calculateCenterX(Line line1, Line line2) {
		float x1 = line1.getP1().x;
		float y1 = line1.getP1().y;
		float x2 = line1.getP2().x;
		float y2 = line1.getP2().y;
		float x3 = line2.getP2().x;
		float y3 = line2.getP2().y;
		
		float m1 = line1.getSlope();
		float m2 = line2.getSlope();
		
		return (m1*m2*(y3-y1)+m1*(x2+x3)-m2*(x1+x2))/(2*(m1-m2));
	}
	
	private float calculateCenterY(Line line1, float x) {
		float x1 = line1.getP1().x;
		float y1 = line1.getP1().y;
		float x2 = line1.getP2().x;
		float y2 = line1.getP2().y;
		
		float m1 = line1.getSlope();
		
		return -1/m1*(x-(x1+x2)/2)+(y1+y2)/2;
	}
	
	private float calculateRadius(PointF center, PointF p1) {
		return (float)Math.sqrt(Math.pow((p1.x - center.x), 2) + Math.pow((p1.y - center.y), 2));
	}
}

package com.androidlab.animationtricks;

import android.graphics.PointF;

/*
 * Line formula: Ax+By=C where
 * A = y2 - y1
 * B = x1 - x2
 * C = A*x1 + B*y1
 * 
 */	
public class Line {
	PointF mP1;
	PointF mP2;
	
	float mA;
	float mB;
	float mC;
	
	public Line(PointF p1, PointF p2) {
		mP1 = p1;
		mP2 = p2;
		
		calculateLineFormula();
	}
	
	public PointF getP1() {
		return mP1;
	}
	
	public PointF getP2() {
		return mP2;
	}	
		
	public PointF getMid() {
		PointF mid = new PointF();
		mid.x = (mP1.x + mP2.x)/2.0F;				
		mid.y = (mP1.y + mP2.y)/2.0F;
		
		return mid;
	}
	
	public float getSlope() {
		return (mP2.y - mP1.y)/(mP2.x - mP1.x);
	}
	
	static public PointF intersection(Line line1, Line line2) {
		float d = line1.mA * line2.mB - line2.mA * line1.mB;
		if(d == 0) {
			//Lines are parallel
			return null;
		} else {
			float x = (line2.mB * line1.mC - line1.mB * line2.mC) / d;
			float y = (line1.mA * line2.mC - line2.mA * line1.mC) / d;
			
			return new PointF(x, y);
		}
	}

	private void calculateLineFormula() {		
		mA = mP2.y - mP1.y;
		mB = mP1.x - mP2.x;
		mC = mA * mP1.x + mB * mP1.y;
	}	
}

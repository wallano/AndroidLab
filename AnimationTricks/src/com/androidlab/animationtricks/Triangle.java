package com.androidlab.animationtricks;

import android.graphics.Point;
import android.graphics.PointF;

public class Triangle {
	PointF mP1;
	PointF mP2;
	PointF mP3;

	public Triangle(PointF p1, PointF p2, PointF p3) {
		mP1 = p1;
		mP2 = p2;
		mP3 = p3;		
	}
	
	public PointF getApex1() {
		return mP1;
	}
	
	public PointF getApex2() {
		return mP2;
	}

	public PointF getApex3() {
		return mP3;
	}

	 
}

package it.moondroid.gestures.example;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

public class GestureAreaView extends View {

	private GestureDetector gestures;
	private Matrix translate;
	private Bitmap droid;

	public GestureAreaView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		init(context);
	}

	public GestureAreaView(Context context, AttributeSet attrs) {
		super(context, attrs);

		init(context);
	}

	public GestureAreaView(Context context) {
		super(context);

		init(context);
	}

	private void init(Context c) {

		myGestureListener listener = new myGestureListener(this);
		gestures = new GestureDetector(c, listener, null, true);

		droid = BitmapFactory.decodeResource(getResources(), R.drawable.droid);
		translate = new Matrix();
	}

	// The onTouchEvent() method must be overridden to pass the MotionEvent data
	// to the gesture detector for processing.
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		boolean retVal = false;
		retVal = gestures.onTouchEvent(event);
		return retVal;
	}

	// The onDraw() method must be overridden to draw the bitmap graphic in the
	// appropriate position at any time
	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawBitmap(droid, translate, null);
	}

	public void move(float dx, float dy) {
		translate.postTranslate(dx, dy);
		invalidate();
	}

	public void resetLocation() {
		translate.reset();
		invalidate();
	}

	// we need to implement our GestureListener class to interpret the
	// appropriate gestures
	private class myGestureListener extends
			GestureDetector.SimpleOnGestureListener {

		GestureAreaView view;

		public myGestureListener(GestureAreaView view) {
			this.view = view;
		}

		// onDown: Called when the user first presses on the touch screen.
		@Override
		public boolean onDown(MotionEvent e) {

			return true;
		}

		// onShowPress: Called after the user first presses the touch screen but
		// before he lifts his finger or moves it around on the screen;
		// used to visually or audibly indicate that the press has been detected
		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
			super.onShowPress(e);
		}

		// onSingleTapUp: Called when the user lifts up (using the up
		// MotionEvent) from the touch screen as part of a single-tap event.
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return super.onSingleTapUp(e);
		}

		// onSingleTapConfirmed: Called when a single-tap event occurs.
		@Override
		public boolean onSingleTapConfirmed(MotionEvent e) {
			// TODO Auto-generated method stub
			return super.onSingleTapConfirmed(e);
		}

		// onDoubleTap: Called when a double-tap event occurs.
		@Override
		public boolean onDoubleTap(MotionEvent e) {

			Toast.makeText(view.getContext(), "onDoubleTapEvent",
					Toast.LENGTH_SHORT).show();
			view.resetLocation();
			return true;
		}

		// onDoubleTapEvent: Called when an event within a double-tap gesture
		// occurs,
		// including any down, move, or up MotionEvent.
		@Override
		public boolean onDoubleTapEvent(MotionEvent e) {

			// TODO Auto-generated method stub
			return super.onDoubleTapEvent(e);
		}

		// onLongPress: Similar to onSingleTapUp, but called if the user holds
		// down his
		// finger long enough to not be a standard click but also without any
		// movement
		@Override
		public void onLongPress(MotionEvent e) {

			Toast.makeText(view.getContext(), "onLongPress", Toast.LENGTH_SHORT)
					.show();

		}

		// onScroll: Called after the user presses and then moves his finger in
		// a steady
		// motion before lifting his finger.This is commonly called dragging.
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {

			view.move(-distanceX, -distanceY);

			return true;
		}

		// onFling: Called after the user presses and then moves his finger in
		// an accelerating
		// motion before lifting it.This is commonly called a flick gesture and
		// usually results in
		// some motion continuing after the user lifts his finger
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {

			Toast.makeText(view.getContext(), "onFling", Toast.LENGTH_SHORT)
					.show();

			return true;
		}

	}
}
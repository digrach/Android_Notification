package rach.test.android_notification.utility;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DigSingleTouchListener implements OnTouchListener {

	private List<PropertyChangeListener> listener = new ArrayList<PropertyChangeListener>();


	public DigSingleTouchListener(View view) {
		view.setOnTouchListener(this);
	}

	private void notifyListeners(Object object, String property, String oldValue, String newValue) {
		for (PropertyChangeListener name : listener) {
			name.propertyChange(new PropertyChangeEvent(this, property, oldValue, newValue));
		}
	}

	public void addChangeListener(PropertyChangeListener newListener) {
		listener.add(newListener);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.d("---------------->","down");
			notifyListeners(null,"x",null,Float.toString(event.getX()));
			notifyListeners(null,"y",null,Float.toString(event.getY()));
			break;
		case MotionEvent.ACTION_MOVE:
			Log.d("---------------->","move");
			notifyListeners(null,"x",null,Float.toString(event.getX()));
			notifyListeners(null,"y",null,Float.toString(event.getY()));
			break;
		case MotionEvent.ACTION_CANCEL:
			Log.d("---------------->","cancle");
			notifyListeners(null,"x",null,Float.toString(event.getX()));
			notifyListeners(null,"y",null,Float.toString(event.getY()));
			break;
		case MotionEvent.ACTION_UP:
			Log.d("---------------->","up");
			notifyListeners(null,"x",null,Float.toString(event.getX()));
			notifyListeners(null,"y",null,Float.toString(event.getY()));
			break;
		}
		return true;
	}
	
	



}

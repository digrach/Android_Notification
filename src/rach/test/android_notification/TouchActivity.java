package rach.test.android_notification;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import rach.test.android_notification.utility.DigSingleTouchListener;
import rach.test.android_notification.utility.MenuMaker;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TouchActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}

	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		Intent i =	MenuMaker.getAct(this, item);
		if (i != null) {
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment implements PropertyChangeListener{

		TextView txttouchtextx;
		TextView txttouchtexty;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_touch,
					container, false);

			setRetainInstance(true);
			
			DigSingleTouchListener singleTouchListener = new DigSingleTouchListener(rootView);
			singleTouchListener.addChangeListener(this);

			txttouchtextx = (TextView)rootView.findViewById(R.id.txttouchtextx);
			txttouchtexty = (TextView)rootView.findViewById(R.id.txttouchtexty);

			return rootView;
		}

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			// TODO Auto-generated method stub
			if(event.getPropertyName().equals("x")) {
				txttouchtextx.setText("x: " + (CharSequence) event.getNewValue());
			}
			if(event.getPropertyName().equals("y")) {
				txttouchtexty.setText("y: " + (CharSequence) event.getNewValue());
			}

		}
	}

}

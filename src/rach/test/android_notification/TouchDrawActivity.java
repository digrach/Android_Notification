package rach.test.android_notification;

import rach.test.android_notification.utility.MenuMaker;
import rach.test.android_notification.utility.MyNewSurfaceClass;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class TouchDrawActivity extends Activity {
	
	PlaceholderFragment pf;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_touch_draw);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment(),"canvasfrag").commit();
		}
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(">>>>>>>>>>>>>>>>","TouchDrawActivity.onStart");
		pf = (PlaceholderFragment)getFragmentManager().findFragmentByTag("canvasfrag");
	}

	@Override
	public void onPause() {
		super.onPause();
		Log.d(">>>>>>>>>>>>>>>>","TouchDrawActivity.onPause");
		MyNewSurfaceClass c = (MyNewSurfaceClass)pf.getView();
		c.pause();
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(">>>>>>>>>>>>>>>>","TouchDrawActivity.onResume");
		MyNewSurfaceClass c = (MyNewSurfaceClass)pf.getView();
		//c.resume();
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
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			setRetainInstance(true);
			
			return new MyNewSurfaceClass(getActivity());
		}
	}

}

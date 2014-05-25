package rach.test.android_notification;

import rach.test.android_notification.DigNotification.Sounds;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
			.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		Context context;
		Activity activity;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);

			context = getActivity();
			activity = getActivity();

			Button btnCreateAlert1  = (Button)rootView.findViewById(R.id.btncreatealert1);
			btnCreateAlert1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					DigNotification digNotification = new DigNotification();
					try {
						digNotification.makeNotification(context, 
								"This is the title", 
								"This is the text", 
								Sounds.SHORT_SOUND, 
								Class.forName("rach.test.android_notification.ResultActivity"));
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}

			});

			Button btnPlaySound1 = (Button)rootView.findViewById(R.id.btnplaysound1);
			btnPlaySound1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					
					DigPlaySound digPlaySound = new DigPlaySound();
					digPlaySound.playSound(context,activity);
					

				}

			});

			return rootView;
		}

	}

}

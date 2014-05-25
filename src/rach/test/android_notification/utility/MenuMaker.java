package rach.test.android_notification.utility;

import rach.test.android_notification.R;
import rach.test.android_notification.ResultActivity;
import rach.test.android_notification.TouchActivity;
import rach.test.android_notification.TouchDrawActivity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

public class MenuMaker {

	public static Intent getAct(Context c, MenuItem mi) { // http://developer.android.com/guide/topics/resources/menu-resource.html
		int id = mi.getItemId();
		Intent i = null;
		if (id == R.id.action_test) {
			i = new Intent(c,ResultActivity.class);
		}
		if (id == R.id.action_touchactivity) {
			i = new Intent(c,TouchActivity.class);		
		}
		if (id == R.id.action_touchanddrawactivity) {
			i = new Intent(c,TouchDrawActivity.class);		
		}
//		if (id == R.id.menu_map_activity) {
//			i = new Intent(c, MapActivity.class);		
//		}
		if (id == R.id.action_settings) {
			i = null;
		}
		
		return i;

	}

}

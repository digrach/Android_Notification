package rach.test.android_notification.utility;

import rach.test.android_notification.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

public class DigNotification {

	private int notificationID;

	public enum Sounds {
		SHORT_SOUND, LONG_SOUND
	}

	private Uri getSound(Sounds sound) {
		Uri soundUri = null;

		switch(sound) {
		case SHORT_SOUND:
			soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			break;
		case LONG_SOUND:
			soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
			break;
		default:
			soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		}

		return soundUri;
	}

	public int makeNotificationID() {
		notificationID = (int) (Math.random() * 100);
		return notificationID;
	}

	public DigNotification() {
	}

	public void makeNotification(Context context, String title, String text, Sounds sound, Class className, String extraTagName, String extra) {

		Notification.Builder mBuilder =
				new Notification.Builder(context)
		.setSmallIcon(R.drawable.icon)
		.setContentTitle(title)
		.setContentText(text)
		.setAutoCancel(true)
		.setSound(getSound(sound));
		// Creates an explicit intent for an Activity in your app
		Intent resultIntent = new Intent(context, className);

		resultIntent.putExtra(extraTagName, extra);
		
		resultIntent.getSelector();

		// The stack builder object will contain an artificial back stack for the
		// started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(className);
		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);
		PendingIntent resultPendingIntent =
				stackBuilder.getPendingIntent(
						0,
						PendingIntent.FLAG_UPDATE_CURRENT
						);
		mBuilder.setContentIntent(resultPendingIntent);
		NotificationManager mNotificationManager =
				(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		// mId allows you to update the notification later on.
		mNotificationManager.notify(makeNotificationID(), mBuilder.build());
	}

}

package rach.test.android_notification.utility;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;

public class DigPlaySound {

	AssetManager assets;
	SoundPool soundPool;
	MediaPlayer mediaPlayer;
	boolean isPrepared = false;

	public DigPlaySound() {
	}

	public void playSound(Context c, Activity a) {

		a.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = a.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
		AssetFileDescriptor assetDescriptor = null;

		try {
			assetDescriptor = assets.openFd("explosion.ogg");
		} catch (IOException e) {
			e.printStackTrace();
		}

		int soundId = soundPool.load(assetDescriptor, 0);

		MediaPlayer mediaPlayer = new MediaPlayer();
		try {
			mediaPlayer.setDataSource(assetDescriptor.getFileDescriptor(),
					assetDescriptor.getStartOffset(),
					assetDescriptor.getLength());
			mediaPlayer.prepare();

		} catch (Exception e) {
			throw new RuntimeException("Couldn't load music");
		}

		mediaPlayer.start();
	}


}

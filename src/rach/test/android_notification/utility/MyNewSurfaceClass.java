package rach.test.android_notification.utility;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyNewSurfaceClass extends SurfaceView implements Runnable, PropertyChangeListener {

	Thread renderThread = null;
	SurfaceHolder holder;
	volatile boolean running = false;
	DigSingleTouchListener touchListener;

	List<Float> xc = new ArrayList<Float>();
	List<Float> yc = new ArrayList<Float>();


	public MyNewSurfaceClass(Context context) {
		super(context);
		Log.d(">>>>>>>>>>>>>>>>","MyNewSurfaceClass Construct");
		this.holder = getHolder();
		touchListener = new DigSingleTouchListener(this);
		touchListener.addChangeListener(this);
	}

	public void resume() { 
		Log.d(">>>>>>>>>>>>>>>>","MyNewSurfaceClass.resume");
		running = true;
		renderThread = new Thread(this);
		renderThread.start();         
	}      

	public void run() {
		long startTime = System.nanoTime();
		while(running) {  
			if(!holder.getSurface().isValid())
				continue;           

			float deltaTime = (System.nanoTime()-startTime) / 1000000000.0f;
			startTime = System.nanoTime();

			//            game.getCurrentScreen().update(deltaTime);
			//            game.getCurrentScreen().present(deltaTime);

			Canvas canvas = holder.lockCanvas();
			// canvas.drawRGB(255, 0, 0);
			//            canvas.getClipBounds(dstRect);
			//            canvas.drawBitmap(framebuffer, null, dstRect, null);        
			//drawStuff(canvas);
			holder.unlockCanvasAndPost(canvas);
		}
	}



	private void drawStuff()  {  
		SurfaceHolder holder = getHolder();
		Canvas canvas = holder.lockCanvas();
		canvas.drawColor(Color.WHITE);  
		
		Paint p = new Paint(Color.BLACK);
		p.setStyle(Style.FILL_AND_STROKE);
		for (int j = 0; j < yc.size(); j ++) {
			try {
				if(xc.get(j) > 0 && yc.get(j) > 0) {
					canvas.drawCircle(xc.get(j),yc.get(j),10,p);
				}
			} catch (IndexOutOfBoundsException ex) {
				ex.printStackTrace();
			}
		}

		holder.unlockCanvasAndPost(canvas);

	}
	//	private void drawStuff(Canvas canvas)  {  
	
	//		
	//		canvas.drawColor(Color.WHITE);  
	//
	//		//		Random r = new Random();
	//		//		int rx = r.nextInt(this.getWidth());
	//		//		int ry = r.nextInt(this.getHeight());
	//		//		int rad = r.nextInt(this.getWidth() / 4);
	//		//
	//		//		Paint p = new Paint(Color.BLACK);
	//		//		p.setStyle(Style.FILL_AND_STROKE);
	//		//		canvas.drawCircle(rx, ry, rad, p);
	//		Paint p = new Paint(Color.BLACK);
	//		p.setStyle(Style.FILL_AND_STROKE);
	//		for (int j = 0; j < yc.size(); j ++) {
	//			try {
	//				if(xc.get(j) > 0 && yc.get(j) > 0) {
	//					canvas.drawCircle(xc.get(j),yc.get(j),10,p);
	//				}
	//			} catch (IndexOutOfBoundsException ex) {
	//				ex.printStackTrace();
	//			}
	//		}
	//	}


	public void pause() {      
		Log.d(">>>>>>>>>>>>>>>>","MyNewSurfaceClass.pause");
		running = false;                        
		while(true) {
			try {
				renderThread.join();
				break;
			} catch (InterruptedException e) {
				// retry
			}
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if(event.getPropertyName().equals("x")) {
			String xs = event.getNewValue().toString();
			double dx = Double.parseDouble(xs);
			float fx = (float) dx;
			xc.add(fx);

		}

		if(event.getPropertyName().equals("y")) {
			String ys = event.getNewValue().toString();
			double dy = Double.parseDouble(ys);
			float fy = (float) dy;
			yc.add(fy);
		}

		drawStuff();

		Log.d("++> Adding to list",Integer.toString(xc.size()));
		if (xc.size() == 1000) {
			xc.remove(0);
			yc.remove(0);
			Log.d("--> removing from list",Integer.toString(xc.size()));
		}
	}        
}
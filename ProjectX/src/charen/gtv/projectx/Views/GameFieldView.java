package charen.gtv.projectx.Views;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import charen.gtv.projectx.Controllers.GameFieldController;
import charen.gtv.projectx.Threads.GameFieldThread;

public class GameFieldView extends SurfaceView implements SurfaceHolder.Callback {
	
	private Paint paint = new Paint();

	public GameFieldView(Context context) {
		super(context);
		getHolder().addCallback(this);
	}

	public GameFieldView(Context context, AttributeSet attrs) {
		super(context, attrs);
		getHolder().addCallback(this);
	}

	public GameFieldView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		getHolder().addCallback(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		GameFieldController.Instance().gameFieldThread = new GameFieldThread(getHolder(), getResources());
		GameFieldController.Instance().gameFieldThread.setRunning(true);
		GameFieldController.Instance().gameFieldThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}

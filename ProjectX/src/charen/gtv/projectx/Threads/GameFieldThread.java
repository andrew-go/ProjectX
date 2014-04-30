package charen.gtv.projectx.Threads;

import charen.gtv.projectx.Controllers.GameFieldController;
import charen.gtv.projectx.Objects.BaseObject;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class GameFieldThread extends Thread {

	private boolean runFlag = false;
	private SurfaceHolder surfaceHolder;
	private Bitmap picture;
	private Matrix matrix;
	private long prevTime;
	private Paint paint;
	private Paint paint2;

	public GameFieldThread(SurfaceHolder surfaceHolder, Resources resources){
	    this.surfaceHolder = surfaceHolder;
	
	    picture = BitmapFactory.decodeResource(resources, charen.gtv.projectx.R.drawable.smile);
	
	    matrix = new Matrix();
	    matrix.postScale(1.0f, 1.0f);
	    matrix.postTranslate(100.0f, 100.0f);
	
	    prevTime = System.currentTimeMillis();
	    
	    paint = new Paint();
	    paint2 = new Paint();
	}
	
	public void setRunning(boolean run) {
		runFlag = run;
    }
	
    @Override
    public void run() {
        Canvas canvas;
        while (runFlag) {
//            long now = System.currentTimeMillis();
//            long elapsedTime = now - prevTime;
//            if (elapsedTime > 20) {
//                prevTime = now;
//                matrix.preRotate(2.0f, picture.getWidth() / 2, picture.getHeight() / 2);
//            }
//            canvas = null;
//            try {
//                canvas = surfaceHolder.lockCanvas(null);
//                synchronized (surfaceHolder) {
//                    canvas.drawBitmap(picture, matrix, null);
//                }
//            } 
//            finally {
//                if (canvas != null) {
//                    surfaceHolder.unlockCanvasAndPost(canvas);
//                }
//            }

            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas(null);
                
                synchronized (surfaceHolder) {
                    for (int i = 0; i < GameFieldController.Instance().getFieldRowsCount(); i++)
                    	for (int j = 0; j < GameFieldController.Instance().getFieldColumsCount(); j++){
                    		if (GameFieldController.Instance().gameField[i][j] == 0)
                 				paint.setColor(Color.GRAY);
                    		else if (GameFieldController.Instance().gameField[i][j] == -1)
                    			paint.setColor(Color.DKGRAY);
                    			canvas.drawRect(GameFieldController.Instance().leftTopPoint.x + j * GameFieldController.Instance().getFieldRectSize() + 4, 
                    					GameFieldController.Instance().leftTopPoint.y + i * GameFieldController.Instance().getFieldRectSize() + 4, 
                    					GameFieldController.Instance().leftTopPoint.x + j * GameFieldController.Instance().getFieldRectSize() + GameFieldController.Instance().getFieldRectSize() - 4, 
                    					GameFieldController.Instance().leftTopPoint.y + i * GameFieldController.Instance().getFieldRectSize() + GameFieldController.Instance().getFieldRectSize() - 4, 
                    					paint);
                    	}
                    
                    
                    for (BaseObject baseObject : GameFieldController.Instance().objectList) {
                    	paint.setColor(Color.GREEN);
                    	if (baseObject == GameFieldController.Instance().selectedUnit) {
                    		drawMoveRadius(canvas);
                    		paint.setAlpha(255);
                    	}
                    	else                    		
                    		paint.setAlpha(100);

            			canvas.drawRect(GameFieldController.Instance().leftTopPoint.x + baseObject.x * GameFieldController.Instance().getFieldRectSize() + 4, 
            					GameFieldController.Instance().leftTopPoint.y + baseObject.y * GameFieldController.Instance().getFieldRectSize() + 4, 
            					GameFieldController.Instance().leftTopPoint.x + baseObject.x * GameFieldController.Instance().getFieldRectSize() + GameFieldController.Instance().getFieldRectSize() - 4, 
            					GameFieldController.Instance().leftTopPoint.y + baseObject.y * GameFieldController.Instance().getFieldRectSize() + GameFieldController.Instance().getFieldRectSize() - 4, 
            					paint);
                    }
                }
            } 
            finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
        
    }
    
    public void drawMoveRadius(Canvas canvas) {
    	paint2.setColor(Color.WHITE);
    	paint2.setAlpha(100);
    	for (int i = GameFieldController.Instance().selectedUnit.y-1; i < GameFieldController.Instance().selectedUnit.moveRadius*2+GameFieldController.Instance().selectedUnit.y; i++)
    		for (int j = GameFieldController.Instance().selectedUnit.x-1; j < GameFieldController.Instance().selectedUnit.moveRadius*2+GameFieldController.Instance().selectedUnit.x; j++) {
    			if (i < 0  || i >= GameFieldController.Instance().getFieldRowsCount() || j < 0  || j >= GameFieldController.Instance().getFieldColumsCount() || GameFieldController.Instance().gameField[i][j] != 0)
    				continue;
    			if (GameFieldController.Instance().gameField[i][j] == 0)
	    			canvas.drawRect(GameFieldController.Instance().leftTopPoint.x + j * GameFieldController.Instance().getFieldRectSize() + 4, 
	    					GameFieldController.Instance().leftTopPoint.y + i * GameFieldController.Instance().getFieldRectSize() + 4, 
	    					GameFieldController.Instance().leftTopPoint.x + j * GameFieldController.Instance().getFieldRectSize() + GameFieldController.Instance().getFieldRectSize() - 4, 
	    					GameFieldController.Instance().leftTopPoint.y + i * GameFieldController.Instance().getFieldRectSize() + GameFieldController.Instance().getFieldRectSize() - 4, 
	    					paint2);
    		}
    			    		
    }
	
}

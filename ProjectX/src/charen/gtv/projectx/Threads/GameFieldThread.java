package charen.gtv.projectx.Threads;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import charen.gtv.projectx.PictureContainer;
import charen.gtv.projectx.Controllers.GameFieldController;
import charen.gtv.projectx.Objects.BaseObject;
import charen.gtv.projectx.Objects.Defender;
import charen.gtv.projectx.Objects.Soldier;
import charen.gtv.projectx.Objects.Unit;

public class GameFieldThread extends Thread {

	private boolean runFlag = false;
	private SurfaceHolder surfaceHolder;
	private Matrix matrix;
	private long prevTime;
	private Paint paint;
	private Paint paint2;
	private PictureContainer pictureContainer;

	public GameFieldThread(SurfaceHolder surfaceHolder, Resources resources){
	    this.surfaceHolder = surfaceHolder;
	
	    matrix = new Matrix();
	    matrix.postScale(1.0f, 1.0f);
	    matrix.postTranslate(100.0f, 100.0f);
	
	    prevTime = System.currentTimeMillis();
	    
	    paint = new Paint();
	    paint2 = new Paint();
	    pictureContainer = new PictureContainer(resources);
	}
	
	public void setRunning(boolean run) {
		runFlag = run;
    }
	
    @Override
    public void run() {
        Canvas canvas;
        while (runFlag) {
            canvas = null;
            try {
                canvas = surfaceHolder.lockCanvas(null);
                
                synchronized (surfaceHolder) {
//                	canvas.drawColor(Color.LTGRAY);
//                    for (int i = 0; i < GameFieldController.Instance().getFieldRowsCount(); i++)
//                    	for (int j = 0; j < GameFieldController.Instance().getFieldColumsCount(); j++){
//             				paint.setColor(Color.GRAY);
//                    		if (GameFieldController.Instance().gameField[i][j] == -1)
//                    			paint.setColor(Color.DKGRAY);
//                			canvas.drawRect(GameFieldController.Instance().leftTopPoint.x + j * GameFieldController.Instance().getFieldRectSize() + 4, 
//                					GameFieldController.Instance().leftTopPoint.y + i * GameFieldController.Instance().getFieldRectSize() + 4, 
//                					GameFieldController.Instance().leftTopPoint.x + j * GameFieldController.Instance().getFieldRectSize() + GameFieldController.Instance().getFieldRectSize() - 4, 
//                					GameFieldController.Instance().leftTopPoint.y + i * GameFieldController.Instance().getFieldRectSize() + GameFieldController.Instance().getFieldRectSize() - 4, 
//                					paint);
//                    	}
//                    
//                    
//                    for (BaseObject baseObject : GameFieldController.Instance().objectList) {
//                    	if (baseObject instanceof Soldier)
//                    		canvas.drawBitmap(pictureContainer.soldierPicture, GameFieldController.Instance().leftTopPoint.x + baseObject.x * GameFieldController.Instance().getFieldRectSize(), GameFieldController.Instance().leftTopPoint.y + baseObject.y * GameFieldController.Instance().getFieldRectSize(), paint);
//                    	else if (baseObject instanceof Defender)
//                    		canvas.drawBitmap(pictureContainer.defenderPicture, GameFieldController.Instance().leftTopPoint.x + baseObject.x * GameFieldController.Instance().getFieldRectSize(), GameFieldController.Instance().leftTopPoint.y + baseObject.y * GameFieldController.Instance().getFieldRectSize(), paint);
//                    }
//                	if (GameFieldController.Instance().selectedUnit != null)
//                		drawTacticalRadius(canvas);
                	
                	//test
                	
            		canvas.drawColor(Color.LTGRAY);
            		for (int i = 0; i < GameFieldController.Instance().getFieldRowsCount(); i++)
            			for (int j = 0; j < GameFieldController.Instance().getFieldColumsCount(); j++) {
            				paint.setColor(Color.GRAY);
            				if (GameFieldController.Instance().gameField[i][j] == -1)
            					paint.setColor(Color.DKGRAY);
	              			canvas.drawRect(GameFieldController.Instance().leftTopPoint.x + j * GameFieldController.Instance().getFieldRectSize() + 4, 
	              					GameFieldController.Instance().leftTopPoint.y + i * GameFieldController.Instance().getFieldRectSize() + 4, 
	              					GameFieldController.Instance().leftTopPoint.x + j * GameFieldController.Instance().getFieldRectSize() + GameFieldController.Instance().getFieldRectSize() - 4, 
	              					GameFieldController.Instance().leftTopPoint.y + i * GameFieldController.Instance().getFieldRectSize() + GameFieldController.Instance().getFieldRectSize() - 4, 
	              					paint);
                  	}                
	              	if (GameFieldController.Instance().selectedUnit != null)
	              		drawTacticalRadius(canvas);
                 	for (BaseObject baseObject : GameFieldController.Instance().objectList) {
	                  	if (baseObject instanceof Soldier)
	                  		canvas.drawBitmap(((Soldier) baseObject).team == 0 ? pictureContainer.soldierPicture1 : pictureContainer.soldierPicture2, GameFieldController.Instance().leftTopPoint.x + baseObject.x * GameFieldController.Instance().getFieldRectSize(), GameFieldController.Instance().leftTopPoint.y + baseObject.y * GameFieldController.Instance().getFieldRectSize(), paint);
	                  	else if (baseObject instanceof Defender)
	                  		canvas.drawBitmap(((Defender) baseObject).team == 0 ? pictureContainer.defenderPicture1 : pictureContainer.defenderPicture2, GameFieldController.Instance().leftTopPoint.x + baseObject.x * GameFieldController.Instance().getFieldRectSize(), GameFieldController.Instance().leftTopPoint.y + baseObject.y * GameFieldController.Instance().getFieldRectSize(), paint);
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
    
    public void drawTacticalRadius(Canvas canvas) {
    	for (int i = GameFieldController.Instance().selectedUnit.y-GameFieldController.Instance().selectedUnit.moveRadius; i < GameFieldController.Instance().selectedUnit.y + GameFieldController.Instance().selectedUnit.moveRadius+1; i++)
    		for (int j = GameFieldController.Instance().selectedUnit.x-GameFieldController.Instance().selectedUnit.moveRadius; j < GameFieldController.Instance().selectedUnit.x + GameFieldController.Instance().selectedUnit.moveRadius + 1; j++) {
    			if (i < 0  || i >= GameFieldController.Instance().getFieldRowsCount() || j < 0  || j >= GameFieldController.Instance().getFieldColumsCount())
    				continue;
    			if (GameFieldController.Instance().gameField[i][j] == 0 && GameFieldController.Instance().gameObjectField[i][j] == null) {
    		    	paint2.setColor(Color.GREEN);
    		    	paint2.setAlpha(50);
	    			canvas.drawRect(GameFieldController.Instance().leftTopPoint.x + j * GameFieldController.Instance().getFieldRectSize() + 4, 
	    					GameFieldController.Instance().leftTopPoint.y + i * GameFieldController.Instance().getFieldRectSize() + 4, 
	    					GameFieldController.Instance().leftTopPoint.x + j * GameFieldController.Instance().getFieldRectSize() + GameFieldController.Instance().getFieldRectSize() - 4, 
	    					GameFieldController.Instance().leftTopPoint.y + i * GameFieldController.Instance().getFieldRectSize() + GameFieldController.Instance().getFieldRectSize() - 4, 
	    					paint2);
    			}
    		}
    	for (int i = GameFieldController.Instance().selectedUnit.y-GameFieldController.Instance().selectedUnit.attackRadius; i < GameFieldController.Instance().selectedUnit.y + GameFieldController.Instance().selectedUnit.attackRadius + 1; i++)
    		for (int j = GameFieldController.Instance().selectedUnit.x-GameFieldController.Instance().selectedUnit.attackRadius; j < GameFieldController.Instance().selectedUnit.x + GameFieldController.Instance().selectedUnit.attackRadius + 1; j++) {
    			if (i < 0  || i >= GameFieldController.Instance().getFieldRowsCount() || j < 0  || j >= GameFieldController.Instance().getFieldColumsCount())
    				continue;
    			if (GameFieldController.Instance().gameObjectField[i][j] instanceof Unit && ((Unit)GameFieldController.Instance().gameObjectField[i][j]).team != GameFieldController.Instance().selectedUnit.team) {
    		    	paint2.setColor(Color.RED);
    		    	paint2.setAlpha(50);
	    			canvas.drawRect(GameFieldController.Instance().leftTopPoint.x + j * GameFieldController.Instance().getFieldRectSize() + 4, 
	    					GameFieldController.Instance().leftTopPoint.y + i * GameFieldController.Instance().getFieldRectSize() + 4, 
	    					GameFieldController.Instance().leftTopPoint.x + j * GameFieldController.Instance().getFieldRectSize() + GameFieldController.Instance().getFieldRectSize() - 4, 
	    					GameFieldController.Instance().leftTopPoint.y + i * GameFieldController.Instance().getFieldRectSize() + GameFieldController.Instance().getFieldRectSize() - 4, 
	    					paint2);
    			}
    		}
    }
	
}

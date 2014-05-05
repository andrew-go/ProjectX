package charen.gtv.projectx.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.graphics.Point;

import charen.gtv.projectx.DeviceSettings;
import charen.gtv.projectx.Objects.BaseObject;
import charen.gtv.projectx.Objects.Defender;
import charen.gtv.projectx.Objects.Soldier;
import charen.gtv.projectx.Objects.Unit;
import charen.gtv.projectx.Threads.GameFieldThread;

public class GameFieldController {
	
	private static GameFieldController instance;
	
	public static GameFieldController Instance() {
		return instance == null ? instance = new GameFieldController() : instance;
	}
	
	public GameFieldThread gameFieldThread;
	
	public int gameField[][];
	public Object gameObjectField[][];
	
	private int fieldRectSize;
	
	public int getFieldRectSize() {
		return fieldRectSize;
	}

	private int fieldColumsCount;
	
	public int getFieldColumsCount() {
		return fieldColumsCount;
	}

	private int fieldRowsCount;
	
	public int getFieldRowsCount() {
		return fieldRowsCount;
	}
	
	public Point leftTopPoint;
	
	public List<BaseObject> objectList;
	
	public Unit selectedUnit;
	
	public int selectedColumnIndex = -1;
	public int selectedRowIndex = -1;
	
	public GameFieldController() {
		leftTopPoint = new Point(0, 0);
		fieldColumsCount = 9;
		fieldRectSize = 10 * (int) (DeviceSettings.Instance().width / (fieldColumsCount * 10));
		fieldRowsCount = (int) DeviceSettings.Instance().height / fieldRectSize;
		gameField = new int[fieldRowsCount][fieldColumsCount];
		gameObjectField = new Object[fieldRowsCount][fieldColumsCount];
		initArray();
		initObjects();
		fillGameField();
	}
	
	private void initArray() {
		Random random = new Random();
        for (int i = 0; i < getFieldRowsCount(); i++)
        	for (int j = 0; j < getFieldColumsCount(); j++)
        		gameField[i][j] = random.nextInt(8) == 1 && i != 0 && i != 13? -1 : 0;
	}
	
	private void initObjects() {
		objectList = new ArrayList<BaseObject>();
		objectList.add(new Soldier(3,0,0));
		objectList.add(new Defender(5,0,0));
		objectList.add(new Soldier(5,13,1));
		objectList.add(new Defender(3,13,1));
		
	}
	
	public int getFieldWidth() {
		return fieldRectSize * fieldColumsCount;
	}
	
	public int getFieldHeight() {
		return fieldRectSize * fieldRowsCount;
	}
	
	public boolean isEmptySpace(int rowIndex, int columnIndex) {
		return GameFieldController.Instance().gameField[rowIndex][columnIndex] == -1;
	}
	
	public void fillGameField() {
		for (BaseObject baseObject : objectList)
			gameObjectField[baseObject.y][baseObject.x] = baseObject;
	}
}

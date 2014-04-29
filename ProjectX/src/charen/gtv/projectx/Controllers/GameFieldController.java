package charen.gtv.projectx.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.graphics.Point;

import charen.gtv.projectx.DeviceSettings;
import charen.gtv.projectx.Objects.BaseObject;
import charen.gtv.projectx.Objects.Soldier;
import charen.gtv.projectx.Objects.Unit;
import charen.gtv.projectx.Threads.GameFieldThread;

public class GameFieldController {
	
	private static GameFieldController instance;
	
	public static GameFieldController Instance() {
		return instance == null ? instance = new GameFieldController() : instance;
	}
	
	public GameFieldThread gameFieldThread;
	
	public int GameField[][];
	
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
		GameField = new int[fieldRowsCount][fieldColumsCount];
		initArray();
		initObjects();
	}
	
	private void initArray() {
		Random random = new Random();
        for (int i = 0; i < getFieldRowsCount(); i++)
        	for (int j = 0; j < getFieldColumsCount(); j++)
        		GameField[i][j] = random.nextInt(8) == 1 ? -1 : 0;
	}
	
	private void initObjects() {
		Soldier soldier1 = new Soldier(2,2);
		Soldier soldier2 = new Soldier(2,6);
		objectList = new ArrayList<BaseObject>();
		objectList.add(soldier1);
		objectList.add(soldier2);
	}
	
	public int getFieldWidth() {
		return fieldRectSize * fieldColumsCount;
	}
	
	public int getFieldHeight() {
		return fieldRectSize * fieldRowsCount;
	}
	
	public boolean isEmptySpace(int rowIndex, int columnIndex) {
		return GameFieldController.Instance().GameField[rowIndex][columnIndex] == -1;
	}
}

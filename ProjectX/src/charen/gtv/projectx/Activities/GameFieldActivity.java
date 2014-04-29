package charen.gtv.projectx.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import charen.gtv.projectx.DeviceSettings;
import charen.gtv.projectx.R;
import charen.gtv.projectx.Controllers.GameFieldController;
import charen.gtv.projectx.Objects.BaseObject;
import charen.gtv.projectx.Objects.Unit;
import charen.gtv.projectx.Views.GameFieldView;

public class GameFieldActivity extends Activity {
	
	private GameFieldView gameFieldView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_field);
		initComponents();
		hideSystemUi();
		setDeviceSettings();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return false;
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		GameFieldController.Instance().gameFieldThread.setRunning(false);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		GameFieldController.Instance().gameFieldThread.setRunning(false);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if (GameFieldController.Instance().gameFieldThread != null)
			GameFieldController.Instance().gameFieldThread.setRunning(true);
	}
	
	
	private void initComponents() {
		gameFieldView = (GameFieldView) findViewById(R.id.viewGameField);
		gameFieldView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				GameFieldController.Instance().selectedColumnIndex = (int) (event.getX() - GameFieldController.Instance().leftTopPoint.x) / GameFieldController.Instance().getFieldRectSize();
				GameFieldController.Instance().selectedRowIndex = (int) (event.getY() - GameFieldController.Instance().leftTopPoint.y) / GameFieldController.Instance().getFieldRectSize();
				if (selectUnit() || GameFieldController.Instance().selectedUnit == null || GameFieldController.Instance().isEmptySpace(GameFieldController.Instance().selectedRowIndex, GameFieldController.Instance().selectedColumnIndex))
					return false;
				GameFieldController.Instance().selectedUnit.x = GameFieldController.Instance().selectedColumnIndex;
				GameFieldController.Instance().selectedUnit.y = GameFieldController.Instance().selectedRowIndex;
				return false;
			}
		});
	}
	
	private boolean selectUnit() {
		for (BaseObject baseObject : GameFieldController.Instance().objectList)
			if (baseObject instanceof Unit && baseObject.x == GameFieldController.Instance().selectedColumnIndex && baseObject.y == GameFieldController.Instance().selectedRowIndex) {
				GameFieldController.Instance().selectedUnit = (Unit) baseObject;
				return true;
			}
		return false;
	}
	
	@SuppressLint("NewApi")
	private void hideSystemUi() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH)
			gameFieldView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
	}
	
	private void setDeviceSettings() {
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		DeviceSettings.Instance().width = displaymetrics.widthPixels;
		DeviceSettings.Instance().height = displaymetrics.heightPixels;
	}

}

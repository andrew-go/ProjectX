package charen.gtv.projectx.Activities;

import charen.gtv.projectx.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class GameFieldActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_field);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.game_field, menu);
		return true;
	}

}

package charen.gtv.projectx;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class PictureContainer {
	
	public Bitmap soldierPicture1;
	public Bitmap soldierPicture2;
	public Bitmap defenderPicture1;
	public Bitmap defenderPicture2;
	public Bitmap grass;

	public PictureContainer(Resources resources) {
		soldierPicture1 = BitmapFactory.decodeResource(resources, charen.gtv.projectx.R.drawable.soldier_1);
		soldierPicture2 = BitmapFactory.decodeResource(resources, charen.gtv.projectx.R.drawable.soldier_2);
		defenderPicture1 = BitmapFactory.decodeResource(resources, charen.gtv.projectx.R.drawable.defender_1);
		defenderPicture2 = BitmapFactory.decodeResource(resources, charen.gtv.projectx.R.drawable.defender_2);
		grass = BitmapFactory.decodeResource(resources, charen.gtv.projectx.R.drawable.grass);
	}

}

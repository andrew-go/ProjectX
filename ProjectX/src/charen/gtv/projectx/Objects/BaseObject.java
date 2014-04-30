package charen.gtv.projectx.Objects;

public class BaseObject {
	
	public int type;
	
	public int x;
	public int y;
	
	public BaseObject(int type) {
		this.type = type;
	}
	
	public BaseObject(int type, int x, int y) {
		this.type = type;
		this.x = x;
		this.y = y;
	}
	
}

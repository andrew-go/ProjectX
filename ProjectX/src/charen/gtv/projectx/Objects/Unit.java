package charen.gtv.projectx.Objects;

public class Unit extends BaseObject {
	
	public int health;
	
	public int damage;
	
	public int moveRadius;
	
	public int attackRadius;
	
	public Unit(int type) {
		super(type);
	}
	
	public Unit(int type, int x, int y) {
		super(type, x, y);
	}

}

package charen.gtv.projectx.Objects;

public class Soldier extends Unit {

	public Soldier(int x, int y, int team) {
		super(1, x, y, team);
		moveRadius = 2;
		attackRadius = 1;
	}

}

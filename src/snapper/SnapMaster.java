package snapper;

import models.Environment;

import java.util.ArrayList;
import java.util.List;

public class SnapMaster
{
	private List<StateSnapper> states;
	private StateSnapper mainSnapper;

	public SnapMaster(Environment earth, Environment spaceship, Environment mars) {
		states = new ArrayList<>();
		mainSnapper = new StateSnapper(earth, spaceship, mars);
	}

	public void snap() {
		states.add(mainSnapper.snap());
	}

	public void undo() {
		int last = states.size()-1;
		mainSnapper = states.get(last);
		states.remove(last);
		snap();
	}
}

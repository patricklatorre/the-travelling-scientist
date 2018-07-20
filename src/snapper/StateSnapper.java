package snapper;

import models.Earth;
import models.Environment;
import models.Mars;
import models.Spaceship;

public class StateSnapper
{
	private Environment earth;
	private Environment spaceship;
	private Environment mars;

	public StateSnapper(Environment earth, Environment spaceship, Environment mars) {
		this.earth = earth;
		this.spaceship = spaceship;
		this.mars = mars;
	}

	public StateSnapper snap() {
		return new StateSnapper(new Earth(earth), new Spaceship(spaceship), new Mars(mars));
	}
}

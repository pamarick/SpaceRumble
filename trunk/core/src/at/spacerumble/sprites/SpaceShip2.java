package at.spacerumble.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;

import at.spacerumble.objects.GameObject;

public class SpaceShip2 extends GameObject {

	public SpaceShip2(World world) {
		super(new Texture("yellowship.png"), world);
		sprite.setPosition(400, 400);
		sprite.setSize(15, 30);
	}

}

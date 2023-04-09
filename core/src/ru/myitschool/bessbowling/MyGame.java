package ru.myitschool.bessbowling;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJoint;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.FrictionJoint;
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;

public class MyGame extends ApplicationAdapter {
	public static final float WIDTH = 7.2f, HEIGHT = 12.8f;

	SpriteBatch batch;
	OrthographicCamera camera;
	Vector3 touch;
	World world;
	Box2DDebugRenderer debugRenderer;

	//Texture img;

	StaticBody wallLeft, wallRight, floor, roof;
	KinematicBody platform;
	ArrayList<DynamicBodyBall> balls = new ArrayList<>();
	DynamicBodyBall ball;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, WIDTH, HEIGHT);
		touch = new Vector3();
		world = new World(new Vector2(0, 0), true);
		debugRenderer = new Box2DDebugRenderer();

		//img = new Texture("badlogic.jpg");
		floor = new StaticBody(world, WIDTH/2, 0.2f, WIDTH, 0.2f);
		wallLeft = new StaticBody(world, 0.5f, HEIGHT/2, 0.2f, HEIGHT);
		wallRight = new StaticBody(world, WIDTH-0.5f, HEIGHT/2, 0.2f, HEIGHT);
		//roof = new StaticBody(world, WIDTH/2, HEIGHT-0.2f, WIDTH, 0.2f);
		//platform = new KinematicBody(world, WIDTH/2, 2, 2, 1);
		for (int i = 0; i < 5; i++) {
			balls.add(new DynamicBodyBall(world, 1+i, HEIGHT-2, 0.5f));
		}
		ball = new DynamicBodyBall(world, 3, 2, 0.6f);
	}


	@Override
	public void render () {
		if(Gdx.input.justTouched()){
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);
			for (int i = 0; i < balls.size(); i++) {
				balls.get(i).hit(touch.x, touch.y);
			}
			ball.hit(touch.x, touch.y);
		}
		//platform.move();
		ScreenUtils.clear(0, 0, 0, 1);
		debugRenderer.render(world, camera.combined);
		/*camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.end();*/
		world.step(1/60f, 6, 2);
	}

	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}

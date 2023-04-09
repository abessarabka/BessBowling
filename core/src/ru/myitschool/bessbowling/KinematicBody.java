package ru.myitschool.bessbowling;

import static ru.myitschool.bessbowling.MyGame.WIDTH;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class KinematicBody {
    Body body;

    KinematicBody(World world, float x, float y, float width, float height){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(x, y));

        body = world.createBody(bodyDef);

        PolygonShape box = new PolygonShape();
        box.setAsBox(width/2, height/2);
        body.createFixture(box, 0.0f);
        box.dispose();
        body.setLinearVelocity(1, 0);
        body.setAngularVelocity(-2);
    }

    void move() {
        if(body.getPosition().x < 1){
            body.setLinearVelocity(1, 0);
        }
        if(body.getPosition().x > WIDTH-1){
            body.setLinearVelocity(-1, 0);
        }
    }
}

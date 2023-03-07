package ru.pavlenty.surfacegame2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class Obstacle {
    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed = 0;
    private boolean boosting;
    private final int GRAVITY = -10;
    private int maxY;
    private int minY;

    private final int MIN_SPEED = 1;
    private final int MAX_SPEED = 20;

    public static Rect detectCollision;
    public static boolean isCollision;
    public Obstacle(Context context, int screenX, int screenY) {
        x = 100;
        y = 800;
        speed = 1;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);
        maxY = screenY - bitmap.getHeight();
        minY = 0;
        boosting = false;


        detectCollision =  new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void setBoosting() {
        boosting = true;
    }

    public void stopBoosting() {
        boosting = false;
    }

    public void update() {
        if (boosting) {
            speed += 2;
        } else {
            speed -= 5;
        }

        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }

        if (speed < MIN_SPEED) {
            speed = MIN_SPEED;
        }

        y -= speed + GRAVITY;

        if (y < minY) {
            y = minY;
        }
        if (y > maxY) {
            y = maxY;
        }


        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();

    }


    public static Rect getDetectCollision() {
        return detectCollision;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public static boolean getIsCollision(Rect rect1, Rect rect2) {
        // get the collision rectangles for each object
        Rect collisionRect1 = Obstacle.getDetectCollision();
        Rect collisionRect2 = Player.getDetectCollisiona();
        // check for intersection of the collision rectangles
        boolean isCollision = collisionRect1.intersect(collisionRect2);
        return isCollision;
    }

    public int getY() {
        return y;
    }
}
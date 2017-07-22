package McGregorysCrypt.window;

import McGregorysCrypt.frameWorks.GameObject;

public class Camera {
	
	private float x, y;
	
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void tick(GameObject player){
		
		float xTarg = (-player.getX() + Game.WIDTH/2 - 75);
		x += (xTarg - x) * (2/4f);
		
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}

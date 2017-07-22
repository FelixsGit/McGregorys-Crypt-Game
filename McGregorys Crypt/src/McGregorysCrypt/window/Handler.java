package McGregorysCrypt.window;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import McGregorysCrypt.frameWorks.GameObject;
import McGregorysCrypt.frameWorks.ObjectId;
import McGregorysCrypt.objects.PurpleSkeleton;

public class Handler {
	
	public LinkedList <GameObject> object  = new LinkedList<GameObject>();
	private GameObject tempObject;
	private Handler handler;
	
	public void tick(){
		for(int i = 0; i<object.size(); i++){
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	public void render(Graphics g){
		for(int i = 0; i<object.size(); i++){
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	public void addObject(GameObject object){
		this.object.add(object);
	}
	public void removeObject(GameObject object){
		this.object.remove(object);
	}

 }
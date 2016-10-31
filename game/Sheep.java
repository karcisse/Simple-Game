/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Karol
 */
public class Sheep extends Animal{
    public Sheep(int x, int y, World world, boolean canMultiply){
    _world=world;
    _strength=4;
    _initiative=4;
    _age=0;
    _x=x;
    _y=y;
    _canMultiply=canMultiply;
    _iconFileName="sheep.png";
    world.setCreature(this);
    ImageIcon ii = new ImageIcon(this.getClass().getResource(_iconFileName));
    _image = ii.getImage();
    }
    
    public Sheep(int x, int y, World world, boolean canMultiply, int age, int strength){
    _world=world;
    _strength=strength;
    _initiative=4;
    _age=age;
    _x=x;
    _y=y;
    _canMultiply=canMultiply;
    _iconFileName="sheep.png";
    world.setCreature(this);
    ImageIcon ii = new ImageIcon(this.getClass().getResource(_iconFileName));
    _image = ii.getImage();
    }
    
    protected void multiply(int x, int y){
     Random generator = new Random();
    int direction = generator.nextInt(4);
        if (direction==Global.UP && _y>0) { //gora
            _y+=-Global.fieldSize;
        }
         if (direction==Global.UP && _y==0) { //gora
            _y+=Global.fieldSize;
        }
        if (direction==Global.DOWN && _y<Global.mapSize*Global.fieldSize) {
            _y+=Global.fieldSize;
        }
         if (direction==Global.DOWN && _y==Global.mapSize*Global.fieldSize) {
            _y+=-Global.fieldSize;
        }
        if (direction==Global.LEFT && _x>0) {
            _x+=-Global.fieldSize;
        }
         if (direction==Global.LEFT && _x==0) {
            _x+=Global.fieldSize;
        }
        if (direction==Global.RIGHT && _x<Global.mapSize*Global.fieldSize) {
            _x+=Global.fieldSize;
        }
        if (direction==Global.RIGHT && _x==Global.mapSize*Global.fieldSize) {
            _x+=-Global.fieldSize;
        }
        this._world.move(_x, _y, this);
        this._canMultiply=false;
        this._world.createCreature(x, y, new Sheep(x,y,this._world,true));
    }
}

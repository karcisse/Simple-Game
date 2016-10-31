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
public class WolfBerries extends Plant{
    public WolfBerries(int x, int y, World world, boolean canMultiply){
        _world=world;
        _strength=99;
        _initiative=0;
        _age=0;
        _x=x;
        _y=y;
        _canMultiply=canMultiply;
        _iconFileName="wolfberries.png";
        world.setCreature(this);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(_iconFileName));
        _image = ii.getImage();
    }
    
    public WolfBerries(int x, int y, World world, boolean canMultiply, int age, int strength){
        _world=world;
        _strength=strength;
        _initiative=0;
        _age=age;
        _x=x;
        _y=y;
        _canMultiply=canMultiply;
        _iconFileName="wolfberries.png";
        world.setCreature(this);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(_iconFileName));
        _image = ii.getImage();
    }
    
    public void action(){
        _age++;
        Random generator = new Random();
        if (generator.nextInt(8)==6) {
            int actualX = _x;
            int actualY=_y;
            int newX = _x, newY = _y;            
            int direction = generator.nextInt(4);
            if (direction==Global.UP && _y>0) { //gora
                newY+=-Global.fieldSize;
            }
            if (direction==Global.DOWN && _y<Global.fieldSize*Global.mapSize-Global.fieldSize) {
                newY+=Global.fieldSize;
            }
            if (direction==Global.LEFT && _x>0) {
                newX+=-Global.fieldSize;
            }
            if (direction==Global.RIGHT && _x<Global.fieldSize*Global.mapSize-Global.fieldSize) {
                newX+=Global.fieldSize;
            }
            
            if (!this._world.checkIfSomethingIsThere(newX, newY)) {
                    if (_canMultiply) {
                    this._canMultiply=false;
                    this._world.createCreature(newX, newY, new WolfBerries(newX, newY, this._world, false));
                }
            }
            
            
        }
    }
}

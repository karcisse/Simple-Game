package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Human extends Animal {
	protected int roundCounter;
        private boolean isPowerActive;
	public int tmpX, tmpY;
        
        
	public Human(World world) {
            _x=1*Global.fieldSize;
            _y=1*Global.fieldSize;
            _age=1;
            _world=world;
            _strength=5;
            _initiative=4;
            _iconFileName="human.png";
            isPowerActive=false;
            roundCounter =5;
            world.setCreature(this);
            ImageIcon ii = new ImageIcon(this.getClass().getResource(_iconFileName));
            _image = ii.getImage();         
	}
        
        public Human(int x, int y, World world, int age, int strength) {
            _x=x;
            _y=y;
            _age=age;
            _world=world;
            _strength=strength;
            _initiative=4;
            _iconFileName="human.png";
            isPowerActive=false;
            roundCounter =5;
            world.setCreature(this);
            ImageIcon ii = new ImageIcon(this.getClass().getResource(_iconFileName));
            _image = ii.getImage();
	}
        
        public void move(int direction){
            tmpX=0;
            tmpY=0;
            if (direction == Global.LEFT && _x>0) {
                 tmpX= -Global.fieldSize;
            }
            if (direction == Global.RIGHT&& _x<Global.fieldSize*Global.mapSize-Global.fieldSize) {
                tmpX= Global.fieldSize;
            }
            if (direction == Global.UP && _y>0) {
                tmpY= -Global.fieldSize;
            }

            if (direction == Global.DOWN && _y<Global.fieldSize*Global.mapSize-Global.fieldSize) {
                tmpY= Global.fieldSize;
            }
        }
        
        public void action(){
          System.out.println("sila czlowieka "+this._strength);
            //setNewXY(newX,newY);
            int newX=_x, newY=_y;
           int actualX=_x, actualY=_y;
           newX+=tmpX;
           newY+=tmpY;
            if (newX!=actualX || newY!=actualY) {
            if (!this._world.checkIfSomethingIsThere(newX, newY)) {
                // this._world.move(newX, newY, this);
                      setNewXY(tmpX,tmpY);    
            }
            else {
                this._world.makeThemFight(actualX, actualY, this, newX, newY);
                 
                
            }
        }
            if (isPowerActive) {
                _strength--;
                if (_strength==5) {
                    deactivatePower();
                }
            }
            tmpX=0;
            tmpY=0;
            roundCounter++;
        }
        
        private void setNewXY(int x, int y){
           _x+=x;
           _y+=y;
           // System.out.println(_x+"   "+_y);
       }
        
        protected void activatePower(){
            isPowerActive=true;
            _strength=10;
            roundCounter=0;
        }
        
        protected void deactivatePower(){
            isPowerActive = false;
            roundCounter=0;
        }

   
        
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author Karol
 */
public class Plant extends Organism {
    public void collision(Organism attacker, int x, int y, int actualX, int actualY){
       
        if (this._world.specifyStrength(actualX, actualY)>=this._strength) {
            for (int i = 0; i < this._world.countCreatures(); i++) {
                if (this._world.specifyX(i)==_x && this._world.specifyY(i)==_y) {
                    this._world.kill(i);
                    this._world.move(x, y, attacker);
                }
            }
        }
        else{
            for (int i = 0; i < this._world.countCreatures(); i++) {
                if (this._world.specifyX(i)==actualX && this._world.specifyY(i)==actualY) {
                    this._world.kill(i);
                    this._world.move(x, y, this);
                }
            }
            for (int i = 0; i < this._world.countCreatures(); i++) {
                if (this._world.specifyX(i)==_x && this._world.specifyY(i)==_y) {
                    this._world.kill(i);
                   
                }
            }
        }
    }
}

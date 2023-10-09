package org.example.creatures;

public class Player extends Creature{
    private byte countOfHealings = 4;

    public Player(int health) {
        super(health);
    }
    public void setCountOfHealings(){
        this.countOfHealings--;
    }

    public byte getCountOfHealings() {
        return countOfHealings;
    }
}

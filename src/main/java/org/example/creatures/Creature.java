package org.example.creatures;

public class Creature {
    private byte attack;
    private int health;

    private int protection;
    private int damage;

    public Creature(int health) {
        this.health = health;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getProtection() {
        return protection;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public byte getAttack() {
        return attack;
    }

    public void setAttack(byte attack) {
        this.attack = attack;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}

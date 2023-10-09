package org.example.model;

public abstract class Creature {
    protected byte attack;
    protected int health;
    protected int damage;
    protected byte protection;
    public Creature(int health) {
        this.health = health;
    }
    public byte getProtection() {
        return protection;
    }

    public void setProtection(byte protection) {
        this.protection = protection;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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

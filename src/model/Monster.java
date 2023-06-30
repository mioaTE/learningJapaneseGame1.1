package model;

public class Monster {
    private String mosterName;
    private int monsterHP;
    private int monsterAttack;


    //getter
    public String getMosterName(){
        return mosterName;
    }

    public int getMonsterHP(){
        return monsterHP;
    }
    public int getMonsterAttack(){
        return monsterAttack;
    }

    public void setMonsterHP(int monsterHP) {
        this.monsterHP = monsterHP;
    }

    //setter

    //constructor
    public Monster(String monsterName, int HP, int attack){
        this.mosterName = monsterName;
        this.monsterHP = HP;
        this.monsterAttack = attack;

    }

    public void damagedMonster(int attack){
        monsterHP = monsterHP - attack;
    }


}

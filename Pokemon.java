public class Pokemon {
    private int id, total, hp, attack, defense, specialAttack, specialDefense, speed;
    private String name, type1, type2;
    
    public Pokemon (int id, String name, String type1, String type2, int total, int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.id = id;
        this.name = name;
        this.type1 = t1;
        this.type2 = t2;
        this.total = bst;
        this.hp = hp;
        this.attack = atk;
        this.defense = def;
        this.specialAttack = spAtk;
        this.specialDefense = spDef;
        this.speed = spe;
    }
    public int getID() { return id; }
    public String getName() { return name; }
    public String getType1() { return t1; }
    public String getType2() { return t2; }
    public int getHealth() { return hp; }
    public int getAttack() { return atk; }
    public int getDefense() { return def; }
    public int getSp.Atk() { return spAtk; }
    public int getSp.Def() { return spDef; }
    public int getSpeed() { return spe; }
    }
}

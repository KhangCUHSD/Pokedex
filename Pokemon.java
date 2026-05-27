public class Pokemon {
    private int id, total, hp, attack, defense, specialAttack, specialDefense, speed;
    private String name, type1, type2;

    public Pokemon(int id, String name, String type1, String type2, int total, int hp, int attack, int defense, int specialAttack, int specialDefense, int speed) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.total = total;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.specialAttack = specialAttack;
        this.specialDefense = specialDefense;
        this.speed = speed;
    }

    public int getID() { return id; }
    public String getName() { return name; }
    public String getType1() { return type1; }
    public String getType2() { return type2; }
    public int getHealth() { return hp; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpAtk() { return specialAttack; }
    public int getSpDef() { return specialDefense; }
    public int getSpeed() { return speed; }
}

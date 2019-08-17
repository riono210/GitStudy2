
public class Player extends CharacterTmp {
    // コンストラクタ
    Player() {
        name = "ああああ";
        hp = 20;
        attack = 3;
        speed = 3;
        isLive = true;
    }

    Player(String name, float hp, float attack, float speed) {
        this.name= name;
        this.hp = hp;
        this.attack = attack;
        this.speed = speed;
        isLive = true;
    }

    // 攻撃コマンド
    public void AttackCommand(CharacterTmp opponent) {
        System.out.printf("%sのこうげき！%n", name);
        opponent.ReceiveDamage(attack);
    }
    
    // ダメージ処理
    public void ReceiveDamage(float damage) {
        hp -= damage;
        System.out.printf("%sは%1.0fのダメージを受けた！%n%n", name, damage);
        if (hp <= 0) {
            isLive = false;
            System.out.printf("%sは死んでしまった！%n%n", name);
        }
    }
}

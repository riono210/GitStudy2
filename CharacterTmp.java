import java.util.Random;

public class CharacterTmp {
    public String name;
    public float hp;
    public float attack;
    public float speed;
    public float chSpeed;
    public boolean isLive;

    CharacterTmp(){
        name = "null";
        hp = 1;
        attack = 1;
        speed = 1;
        isLive = true;
    }

    // 攻撃時の速度変化
    public void ChangeSpeed() {
        Random random = new Random();
        chSpeed = speed + (random.nextInt(5) - 2);
    }

    // 攻撃コマンド
    public void AttackCommand() {
        System.out.printf("%sのこうげき！", name);

    }

    // ダメージ処理
    public void ReceiveDamage(float damage) {
        hp -= damage;
        System.out.printf("%sは%1.0fのダメージを受けた！", name, damage);

    }

    public boolean GetIsLive() {
        return isLive;
    }
}
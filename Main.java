import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static CharacterTmp[] characterTmps = new CharacterTmp[2]; // キャラクターの配列
    public static CharacterTmp[] speedReplace; // スピードで並び替える時の配列
    public static ArrayList<CharacterTmp> players = new ArrayList<CharacterTmp>(); // プレイヤーの配列
    public static ArrayList<CharacterTmp> enemies = new ArrayList<CharacterTmp>(); // エネミーの配列

    public static boolean isInBattle = true;

    public static void main(String[] args) {
        Player player = new Player();
        Enemy enemy = new Enemy();

        characterTmps[0] = enemy;
        characterTmps[1] = player;

        ArrayCreate();

        // メインループ
        while (isInBattle) {
            SpeedComparison();
            AttackProcess();

        }

    }

    // 配列の生成
    public static void ArrayCreate() {
        speedReplace = characterTmps;
        // プレイヤーとエネミーの振り分け
        for (CharacterTmp var : characterTmps) {
            if (var.getClass().getName() == "Player") {
                players.add(var);
            } else if (var.getClass().getName() == "Enemy") {
                enemies.add(var);
            }
        }

        System.out.printf("pl%d en%d", players.size(), enemies.size());
    }

    // 素早さの比較
    public static void SpeedComparison() {
        for (int index = 0; index < speedReplace.length; index++) {
            speedReplace[index].ChangeSpeed();
        }

        // バブルソート
        for (int index = 0; index < speedReplace.length; index++) {
            for (int reversIndex = speedReplace.length - 1; reversIndex > 0; reversIndex--) {
                // 速度の比較
                if (speedReplace[reversIndex].chSpeed < speedReplace[reversIndex - 1].chSpeed) {
                    Swap(speedReplace, reversIndex);
                }

            }
        }
        // デバッグ
        for (CharacterTmp var : speedReplace) {
            System.out.printf("%s speed%f%n", var.name, var.speed);
        }
    }

    // 要素の交代
    public static void Swap(CharacterTmp[] array, int index) {
        CharacterTmp tmp = array[index - 1];
        array[index - 1] = array[index];
        array[index] = tmp;
    }

    // 攻撃処理
    public static void AttackProcess() {
        Random random = new Random();
        for (CharacterTmp var : speedReplace) {
            if (var.getClass().getName() == "Player") { // プレイヤー側の攻撃
                int attackTraget = random.nextInt(enemies.size()); // 攻撃対象をランダムで選択
                System.out.println(attackTraget);

                Player playerTmp = (Player) var; // コマンド実行
                playerTmp.AttackCommand(enemies.get(attackTraget));
                // hp判定
                if (!enemies.get(attackTraget).GetIsLive()) {
                    isInBattle = false;
                    break;
                }

            } else if (var.getClass().getName() == "Enemy") { // エネミー側の攻撃
                int attackTraget = random.nextInt(players.size()); // 攻撃対象をランダムで選択
                System.out.println(attackTraget);

                Enemy enemyTmp = (Enemy) var; // コマンド実行
                enemyTmp.AttackCommand(players.get(attackTraget));
                // hp判定
                if (!players.get(attackTraget).GetIsLive()) {
                    isInBattle = false;
                    break;
                }
            }
        }
    }
}
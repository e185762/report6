package jp.ac.uryukyu.ie.e185762;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * エネミーのステータス作成、また戦闘パートでの攻撃行動を処理するクラス
 */
public class Enemy {

    /**
     * エネミーの名前
     */
    String enemy_name;
    /**
     * エネミーの体力
     */
    int enemy_physical;
    /**
     * エネミーの攻撃力
     */
    int enemy_attack;
    /**
     * エネミーの防御力
     */
    int enemy_defense;
    /**
     * エネミーのスピード
     */
    int enemy_speed;

    /**
     * ユーザー入力を受け取るためのScannerのコンストラクタ
     */
    Scanner in = new Scanner(System.in);

    /**
     * エネミーのステータス値が書かれたテキストファイルを読み込み、
     * フィールド関数「enemy_name」「enemy_physical」「enemy_attack」
     * 「enemy_defense」「enemy_speed」に当てはめるメソッド
     *
     * @param filename 作成したいエネミーのステータステキストの場所　例：「./sentence/enemy_list/~」
     * @throws IOException ファイル入出力時に起こる例外
     */
    public void MakeAvilityList(String filename) throws IOException {
        Tools tools = new Tools();
        String sentence = tools.fileToString(new File(filename));

        String[] word_list = sentence.split(",", 0);
        String[] name_split = word_list[0].split("=",0);
        this.enemy_name = name_split[1];

        String[] status_wordlist = {"attack", "defense", "physical", "speed"};

        for (int num = 1; num < word_list.length; num++) {

            for (int count = 0; count < word_list.length-1; count++) {
                if (word_list[num].contains(status_wordlist[count])) {
                    String[] serect_sentence = word_list[count+1].split("=", 0);
                    int avility = Integer.parseInt(serect_sentence[1]);

                    if (word_list[num].contains(status_wordlist[0])) {
                        this.enemy_attack = avility;
                    } else if (word_list[num].contains(status_wordlist[1])) {
                        this.enemy_defense = avility;
                    }else if (word_list[num].contains(status_wordlist[2])) {
                        this.enemy_physical = avility;
                    } else if (word_list[num].contains(status_wordlist[3])) {
                        this.enemy_speed = avility;
                    }

                }
            }
        }
    }

    /**
     * どのエネミーを出現させるかをrandomを使って決めるメソッド
     * randomで求めた数値が一定値の範囲であれば、その範囲に応じたエネミーのステータスが書かれたテキストファイルの場所を
     * MakeAvilityListに入力する
     *
     * @throws IOException ファイル入出力時に起こる例外
     */
    public void EnemyDicision() throws IOException {
        Random random = new Random();
        int num = random.nextInt(40);
        if (num<10){
            MakeAvilityList("./sentence/enemy_list/fairy.txt");
        }else if (num<20){
            MakeAvilityList("./sentence/enemy_list/livingdead.txt");
        }else if (num<30){
            MakeAvilityList("./sentence/enemy_list/slime.txt");
        }else if (num<40){
            MakeAvilityList("./sentence/enemy_list/undeadknight.txt");
        }

    }

    /**
     * 戦闘パートでの攻撃行動を行うメソッドです
     * 「キャラクタのHP」から「エネミーの攻撃力」＋「キャラクタの防御力」を引いた値を返します。
     *
     * @param character_hp　キャラクタのHP
     * @param character_defence　キャラクタの防御力
     * @param enemy_physical　エネミーの体力
     * @return　「キャラクタのHP」から「エネミーの攻撃力」＋「キャラクタの防御力」を引いたint値
     */
    public int attack(int character_hp, int character_defence, int enemy_physical){

        int attack = this.enemy_attack-character_defence;
        if (attack<0){
            attack=0;
        }
        int attack_result = character_hp-attack;
        if (enemy_physical>0){

            System.out.println(this.enemy_name+"のこうげき！");
            System.out.println("あいてに"+attack+"のだめーじ");
            in.nextLine();

        }

        return attack_result;
    }


}

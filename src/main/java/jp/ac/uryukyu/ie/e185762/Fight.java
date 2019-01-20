package jp.ac.uryukyu.ie.e185762;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * 戦闘パートを実行するクラス
 */
public class Fight {

    /**
     * ユーザー入力を受け取るためのScannerのコンストラクタ
     */
    Scanner in = new Scanner(System.in);


    /**
     * 戦闘パートでの行動画面を表示するメソッド
     *
     * @throws IOException ファイル入出力時に起こる例外
     */
    public void MakeSerectMenu() throws IOException {
        Tools tool = new Tools();
        System.out.println(tool.fileToString(new File("./sentence/menu.txt")));

    }


    /**
     * 戦闘行動を処理するメソッド
     *
     * @param character　Characterクラスのコンストラクタ
     * @param enemy　Enemyクラスのコンストラクタ
     * @throws IOException ファイル入出力時に起こる例外
     */
    public void Fight(Character character, Enemy enemy) throws IOException{

        System.out.println(enemy.enemy_name+"をはっけん");
        System.out.println(character.character_name+"はどうしますか？");

        int character_hitpoint = character.character_physical;
        int enemy_hitpoint = enemy.enemy_physical;

        int c_result = 0;
        int e_result = 0;

        boolean success = false;
        while (success==false){
            MakeSerectMenu();

            System.out.println(enemy.enemy_name+"のげんざいのHP:"+enemy_hitpoint);
            System.out.println(character.character_name+"のげんざいのHP:"+character_hitpoint);
            System.out.print("こまんどをどうぞ　＞＞＞");
            String input_comand = in.nextLine();

            if (input_comand.equals("a")){
                int[] result = attack_turn(character,enemy,character_hitpoint,enemy_hitpoint);
                c_result=result[0];
                e_result=result[1];

            }else if (input_comand.equals("s")){
                System.out.println(character.character_name+"は回避体制にはいった！");
                in.nextLine();
                System.out.println("このターンのダメージは０");
                e_result=enemy_hitpoint;
                c_result=character_hitpoint;

            }else if (input_comand.equals("d")){
                int[] result =Magic(character_hitpoint,character,enemy_hitpoint,enemy);
                c_result=result[0];
                e_result=result[1];

            }else{
                continue;
            }

            enemy_hitpoint=e_result;
            character_hitpoint=c_result;

            if (enemy_hitpoint<=0||character_hitpoint<=0){
                success=true;
                System.out.println("しゅうりょう！");
            }
        }
    }


    /**
     * キャラクタとエネミーのスピードを比べて攻撃順を変えるメソッド
     * 死体蹴りをしないように設定
     *
     * @param character　Characterクラスのコンストラクタ
     * @param enemy　Enemyクラスのコンストラクタ
     * @param character_hitpoint　このターンのキャラクタの残りHP
     * @param enemy_hitpoint　このターンのエネミーの残りHP
     * @return　攻撃処理をした「キャラクタの残りHP」と「エネミーの残りHP」のint配列
     */
    public int[] attack_turn(Character character, Enemy enemy, int character_hitpoint, int enemy_hitpoint){

        int c_result = 0;
        int e_result = 0;

        if (character.character_speed>enemy.enemy_speed){
                    e_result=character.attack(enemy_hitpoint,enemy.enemy_defense,character_hitpoint);
                    if (e_result>0){
                        c_result=enemy.attack(character_hitpoint,character.character_defense,enemy_hitpoint);
                    }
                }else{
                    c_result=enemy.attack(character_hitpoint,character.character_defense,enemy_hitpoint);
                    if (c_result>0){
                        e_result=character.attack(enemy_hitpoint,enemy.enemy_defense,character_hitpoint);
                    }
        }
         int[] results = {c_result,e_result};
        return results;
    }


    /**
     * Fight()でのコマンド選択画面で「じゅつ」のコマンドを選択した場合の
     * 「じゅつ」の効果処理とその後の攻撃処理を行うメソッド
     *
     * @param character_hp　このターンのキャラクタの残りHP
     * @param character　Characterクラスのコンストラクタ
     * @param enemy_hp　このターンのエネミーの残りHP
     * @param enemy　Enemyクラスのコンストラクタ
     * @return　効果処理と攻撃処理を行なった「キャラクタの残りHP」と「エネミーの残りHP」のint配列
     * @throws IOException ファイル入出力時に起こる例外
     */
    public int[] Magic(int character_hp, Character character, int enemy_hp, Enemy enemy) throws IOException {
        Tools tool = new Tools();
        System.out.println(tool.fileToString(new File("./sentence/magic_serect.txt")));

        System.out.print("こまんどをどうぞ　＞＞＞");
        String input_comand = in.nextLine();


        int c_hp = character_hp;
        int e_hp = enemy_hp;
        if (input_comand.equals("a")){
            int[] result =HpCure(character,enemy,c_hp,e_hp);

            c_hp=result[0];
            e_hp=result[1];

        }else if (input_comand.equals("s")){

            int[] result = AttackUp(character,enemy,c_hp,e_hp);

            c_hp=result[0];
            e_hp=result[1];


        }else if(input_comand.equals("d")){

            int[] result = DefenseUp(character,enemy,c_hp,e_hp);

            c_hp=result[0];
            e_hp=result[1];


        }else if (input_comand.equals("f")){

            int[] result = SpeedUp(character,enemy,c_hp,e_hp);

            c_hp=result[0];
            e_hp=result[1];


        }else{
            c_hp=character_hp;
            e_hp=enemy_hp;

        }

        int[] results = {c_hp,e_hp};
        return results;

    }


    /**
     * 「じゅつ」で「かいふく」を選択した場合の効果処理と攻撃処理を行うメソッド
     * MaxHPを超える「かいふく」にも対応
     *
     * @param character　Characterクラスのコンストラクタ
     * @param enemy　Enemyクラスのコンストラクタ
     * @param character_hp　Characterクラスのコンストラクタ
     * @param enemy_hp　このターンのエネミーの残りHP
     * @return　効果処理と攻撃処理を行なった「キャラクタの残りHP」と「エネミーの残りHP」のint配列
     */
    public int[] HpCure(Character character,Enemy enemy,int character_hp,int enemy_hp){
        System.out.println("『あなた』は「かいふく」をつかった");
        character_hp=character_hp+(character_hp/2);
        System.out.println("HPが"+character_hp/2+"かいふく");
        in.nextLine();
        if (character_hp>character.character_physical){
            character_hp=character.character_physical;
        }
        int[] result = attack_turn(character,enemy,character_hp,enemy_hp);

        int[] return_result={result[0],result[1]};
        return return_result;
    }

    /**
     * 「じゅつ」で「がんがんいこうぜ」を選択した場合の効果処理と攻撃処理を行うメソッド
     *
     * @param character　Characterクラスのコンストラクタ
     * @param enemy　Enemyクラスのコンストラクタ
     * @param character_hp　Characterクラスのコンストラクタ
     * @param enemy_hp　このターンのエネミーの残りHP
     * @return　効果処理と攻撃処理を行なった「キャラクタの残りHP」と「エネミーの残りHP」のint配列
     */
    public int[] AttackUp(Character character,Enemy enemy,int character_hp,int enemy_hp){
        System.out.println("『あなた』は「がんがんいこうぜ」をつかった");
        System.out.println("このターンのみ攻撃力が1.5倍");
        in.nextLine();
        int return_attack = character.character_attack;
        int add_attack = (int) (character.character_attack*(1.5));

        character.character_attack=add_attack;

        int[] result = attack_turn(character,enemy,character_hp,enemy_hp);

        character.character_attack=return_attack;

        int[] return_result={result[0],result[1]};
        return return_result;
    }

    /**
     * 「じゅつ」で「かちこち」を選択した場合の効果処理と攻撃処理を行うメソッド
     *
     * @param character　Characterクラスのコンストラクタ
     * @param enemy　Enemyクラスのコンストラクタ
     * @param character_hp　Characterクラスのコンストラクタ
     * @param enemy_hp　このターンのエネミーの残りHP
     * @return　効果処理と攻撃処理を行なった「キャラクタの残りHP」と「エネミーの残りHP」のint配列
     */
    public int[] DefenseUp(Character character,Enemy enemy,int character_hp,int enemy_hp){
        System.out.println("『あなた』はかちこちをつかった");
        System.out.println("このターンのみ防御力が1.5倍");
        in.nextLine();
        int return_defence =character.character_defense;
        int add_defense = (int) (character.character_defense*(1.5));

        character.character_defense=add_defense;

        int[] result = attack_turn(character,enemy,character_hp,enemy_hp);

        character.character_defense=return_defence;

        int[] return_result={result[0],result[1]};
        return return_result;

    }

    /**
     * 「じゅつ」で「はやきことかぜのごとし」を選択した場合の効果処理と攻撃処理を行うメソッド
     *
     * @param character　Characterクラスのコンストラクタ
     * @param enemy　Enemyクラスのコンストラクタ
     * @param character_hp　Characterクラスのコンストラクタ
     * @param enemy_hp　このターンのエネミーの残りHP
     * @return　効果処理と攻撃処理を行なった「キャラクタの残りHP」と「エネミーの残りHP」のint配列
     */
    public int[] SpeedUp(Character character,Enemy enemy,int character_hp,int enemy_hp){
        System.out.println("『あなた』はすばやくをつかった");
        System.out.println("このターンのみ速さが1.5倍");
        in.nextLine();
        int return_speed = character.character_speed;
        int add_speed = (int) (character.character_speed*(1.5));

        character.character_speed=add_speed;

        int[] result = attack_turn(character,enemy,character_hp,enemy_hp);

        character.character_speed=return_speed;

        int[] return_result={result[0],result[1]};
        return return_result;


    }

}

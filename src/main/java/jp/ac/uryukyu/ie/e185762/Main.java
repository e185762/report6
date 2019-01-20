package jp.ac.uryukyu.ie.e185762;

import java.io.IOException;
import java.util.Scanner;

/**
 * 作成したコンストラクタをまとめて動作させるMainクラス
 */

public class Main {

    /**
     * コンストラクタをまとめて動作させるメソッド
     * @param args　引数
     * @throws IOException ファイル入出力時に起こる例外
     */
    public static void main(String[] args) throws IOException {

        Adventure adv = new Adventure();
        Character cct = new Character();
        Enemy eny = new Enemy();

        Scanner in = new Scanner(System.in);


        adv.TitlePrint();
        String character=adv.FirstStory(cct);
        cct.CharacterMake(character);

        adv.StoryReader(cct,eny,character,1,5);

    }
}
package jp.ac.uryukyu.ie.e185762;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {

        Adventure adv = new Adventure();
        Character cct = new Character();
        Fight fig = new Fight();
        Enemy eny = new Enemy();

        Scanner in = new Scanner(System.in);



        //adv.TitlePrint();
        //adv.FirstStory();
        String input_text = in.nextLine();
        eny.EnemyDicision();
        cct.CharacterSerect(input_text);
        cct.CharacterMake();
        System.out.println(eny.enemy_name+"をはっけん");
        System.out.println(cct.character_name+"はどうしますか？");





    }
}
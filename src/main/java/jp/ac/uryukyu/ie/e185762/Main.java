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
        boolean input = false;
        String character = null;
        String[] character_string = null;
        System.out.print("どれにしますか？　＞＞＞");
        while (input==false){
            String input_text = in.nextLine();
            character_string = cct.CharacterSerect(input_text);

            if (character_string[1] == ("true")){
                input=!input;
            }

        }
        character=character_string[0];
        cct.CharacterMake(character);
        
        eny.EnemyDicision();
        fig.Fight(cct,eny);






    }
}
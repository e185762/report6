package jp.ac.uryukyu.ie.e185762;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) throws IOException {

        Adventure adv = new Adventure();
        Character cct = new Character();
        Fight fig = new Fight();
        Enemy eny = new Enemy();
        Tools tool = new Tools();

        Scanner in = new Scanner(System.in);



        // a = "doll_1.txt";
        //adv.Adventure("./sentence/story_list/first_story_list/doll_stories/"+a);
        //File dir = new File("./sentence/story_list/first_story_list/doll_stories/");
        //String[] files = dir.list();
        //System.out.println(files[0]);
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

        tool.StoryReader(cct,eny,character,1);

        //adv.Adventure("./sentence/story_list/first_story_list/doll_stories/doll_1.txt");
        //eny.EnemyDicision();
        //fig.Fight(cct,eny);
        //adv.Adventure("./sentence/story_list/first_story_list/doll_stories/doll_2.txt");








    }
}
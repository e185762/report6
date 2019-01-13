package jp.ac.uryukyu.ie.e185762;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        Adventure adv = new Adventure();
        Character cct = new Character();
        Fight fig = new Fight();
        //cct.MakeFileList("./sentence/character_list/doll.txt");
        //System.out.println(cct.character_defense);
        //adv.TitlePrint();
        //adv.Adventure("./sentence/story_list/first_story_list/first.txt");
        //adv.Serect_choice("a","!./sentence/story_list/first_story_list/choice_1/a.txt%./sentence/story_list/first_story_list/choice_1/s.txt\n");
        fig.Make_Serect_Menu();
    }
}
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


        adv.TitlePrint();
        String character=adv.FirstStory(cct);
        cct.CharacterMake(character);

        adv.StoryReader(cct,eny,character,1,5);

    }
}
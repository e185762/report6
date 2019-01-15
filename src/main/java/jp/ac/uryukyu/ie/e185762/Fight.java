package jp.ac.uryukyu.ie.e185762;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Fight {

    Scanner in = new Scanner(System.in);


    Character character = new Character();
    Enemy enemy = new Enemy();

    public void MakeSerectMenu() throws IOException {
        Tools tool = new Tools();
        System.out.println(tool.fileToString(new File("./sentence/menu.txt")));
    }

    public void Fight() throws IOException{

        MakeSerectMenu();

        String input_comand = in.nextLine();


    }
}

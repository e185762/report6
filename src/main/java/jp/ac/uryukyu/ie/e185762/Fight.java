package jp.ac.uryukyu.ie.e185762;

import java.io.File;
import java.io.IOException;

public class Fight {
    String[] enemy_list = {"いぬ","くるったひと","ころすべきようせい"};

    public void Make_Serect_Menu() throws IOException {
        Tools tool = new Tools();
        System.out.println(tool.fileToString(new File("./sentence/menu.txt")));
    }

    public void Enemy_encount() throws IOException{


    }
}

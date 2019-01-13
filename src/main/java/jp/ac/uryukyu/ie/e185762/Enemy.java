package jp.ac.uryukyu.ie.e185762;

import java.io.File;
import java.io.IOException;

public class Enemy {
    int[] character_ability;

    int character_physical;
    int character_attack;
    int character_defense;
    int character_speed;

    public void MakeAvilityList(String filename) throws IOException {
        Tools tools = new Tools();
        String sentence = tools.fileToString(new File(filename));
        String[] word_list = sentence.split(",", 0);
        this.character_ability = new int[word_list.length];


        String[] status_wordlist = {"attack", "defense", "physical", "speed"};

        for (int num = 0; num < word_list.length; num++) {

            for (int count = 0; count < word_list.length; count++) {
                if (word_list[num].contains(status_wordlist[count])) {
                    String[] serect_sentence = word_list[count].split("=", 0);
                    int avility = Integer.parseInt(serect_sentence[1]);

                    if (word_list[num].contains(status_wordlist[0])) {
                        this.character_attack = avility;
                    } else if (word_list[num].contains(status_wordlist[1])) {
                        this.character_defense = avility;
                    }else if (word_list[num].contains(status_wordlist[2])) {
                        this.character_physical = avility;
                    } else if (word_list[num].contains(status_wordlist[3])) {
                        this.character_speed = avility;
                    }

                }
            }
        }
    }

    public void Enemy_dicision(){

    }
}

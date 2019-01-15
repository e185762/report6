package jp.ac.uryukyu.ie.e185762;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;



public class Character {
    String character_name;
    int character_physical;
    int character_attack;
    int character_defense;
    int character_speed;

    String character_code;

    Scanner in = new Scanner(System.in);


    public void MakeAvilityList(String filename) throws IOException {
        Tools tools = new Tools();
        String sentence = tools.fileToString(new File(filename));

        String[] word_list = sentence.split(",", 0);
        String[] name_split = word_list[0].split("=", 0);
        this.character_name = name_split[1];

        String[] status_wordlist = {"attack", "defense", "physical", "speed"};

        for (int num = 1; num < word_list.length; num++) {

            for (int count = 0; count < word_list.length - 1; count++) {
                if (word_list[num].contains(status_wordlist[count])) {
                    String[] serect_sentence = word_list[count + 1].split("=", 0);
                    int avility = Integer.parseInt(serect_sentence[1]);

                    if (word_list[num].contains(status_wordlist[0])) {
                        this.character_attack = avility;
                    } else if (word_list[num].contains(status_wordlist[1])) {
                        this.character_defense = avility;
                    } else if (word_list[num].contains(status_wordlist[2])) {
                        this.character_physical = avility;
                    } else if (word_list[num].contains(status_wordlist[3])) {
                        this.character_speed = avility;
                    }

                }
            }
        }
    }


    public void CharacterSerect(String input) throws IOException {
        String character_url = "./sentence/character_list/doll.txt%./sentence/character_list/hero.txt%./sentence/character_list/tank.txt\n";
        String[] split_sentence = character_url.split("%", 0);

        //String input_text = in.nextLine();

        String[] choice_comands = {"a", "s", "d"};

        boolean success = false;
        int number=0;
        for (int num = 0; num < split_sentence.length; num++) {
            if (input.equals(choice_comands[num])) {
                System.out.println("");
                System.out.println("「ほんとうによろしいですか？」");
                System.out.print("yes or no　＞＞＞");
                String input_again_text = in.nextLine();
                if (input_again_text.equals("yes")) {
                    number += num;
                    success = true;
                } else {
                    System.out.print("どれにしますか？　＞＞＞");
                    String input_again = in.nextLine();
                    CharacterSerect(input_again);
                }
            }
        }
        if (success==false) {
            System.out.print("もういちどおねがいします　＞＞＞");
            String input_text = in.nextLine();
            CharacterSerect(input_text);
        }
        this.character_code = choice_comands[number];
    }

    public void CharacterMake () throws IOException {

        if (this.character_code == "a") {
            MakeAvilityList("./sentence/character_list/doll.txt");
        } else if (this.character_code == "s") {
            MakeAvilityList("./sentence/character_list/hero.txt");
        } else if (this.character_code == "d") {
            MakeAvilityList("./sentence/character_list/tank.txt");
        }
    }
}




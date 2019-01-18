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

    Scanner in = new Scanner(System.in);

    //public Character(String name,int attack,int defence, int physical, int character_speed){

    //}


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


    public String[] CharacterSerect(String input_code) throws IOException {
        String character_url = "./sentence/character_list/doll.txt%./sentence/character_list/hero.txt%./sentence/character_list/tank.txt\n";
        String[] split_sentence = character_url.split("%", 0);

        String[] choice_comands = {"a", "s", "d"};

        int number = 0;
        int j_number = 0;

        String comand = null;
        String j_comand = null;
        for (int num = 0; num < split_sentence.length; num++) {

            if (input_code.equals(choice_comands[num])) {
                System.out.println("");
                System.out.println("「ほんとうによろしいですか？」");
                System.out.print("yes or no　＞＞＞");
                String input_again_text = in.nextLine();
                if (input_again_text.equals("yes")) {
                    j_number = num;
                    j_comand = "true";
                } else {
                    System.out.print("どれにいたしますか？　＞＞＞");
                    j_comand= "false";
                }
            }
            comand=j_comand;
            number=j_number;
        }

        if (comand==null&&number==0){
            System.out.println("どれにいたしますか？　＞＞＞");
        }
        String judge_comand = comand;
        String return_string = choice_comands[number];
        String[] returns = {return_string,judge_comand};
        return returns;
    }

    public void CharacterMake (String input) throws IOException {

        if (input.equals("a")) {
            MakeAvilityList("./sentence/character_list/doll.txt");
        } else if (input.equals("s")) {
            MakeAvilityList("./sentence/character_list/hero.txt");
        } else if (input.equals("d")) {
            MakeAvilityList("./sentence/character_list/tank.txt");
        }

    }

    public int attack(int enemy_hp, int enemy_defence, int character_physical){

        int attack = this.character_attack-enemy_defence;
        if (attack<0){
            attack=0;
        }
        int attack_result = enemy_hp-attack;
        if (character_physical>0){

            System.out.println(this.character_name+"のこうげき！");
            System.out.println("あいてに"+attack+"のだめーじ");
            in.nextLine();

        }

        return attack_result;

    }

}




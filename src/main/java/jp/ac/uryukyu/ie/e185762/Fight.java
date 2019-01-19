package jp.ac.uryukyu.ie.e185762;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Fight {

    Scanner in = new Scanner(System.in);


    public void MakeSerectMenu() throws IOException {
        Tools tool = new Tools();
        System.out.println(tool.fileToString(new File("./sentence/menu.txt")));

    }

    public void Fight(Character character, Enemy enemy) throws IOException{

        System.out.println(enemy.enemy_name+"をはっけん");
        System.out.println(character.character_name+"はどうしますか？");


        int character_hitpoint = character.character_physical;
        int enemy_hitpoint = enemy.enemy_physical;


        int c_result = 0;
        int e_result = 0;

        boolean success = false;
        while (success==false){
            MakeSerectMenu();

            System.out.println("げんざいのHP:"+enemy_hitpoint);
            System.out.println("げんざいのHP:"+character_hitpoint);
            System.out.print("こまんどをどうぞ　＞＞＞");

            String input_comand = in.nextLine();


            if (input_comand.equals("a")){

                int[] result = attack(character,enemy,character_hitpoint,enemy_hitpoint);
                c_result=result[0];
                e_result=result[1];

            }else if (input_comand.equals("s")){
                System.out.println(character.character_name+"は回避体制にはいった！");
                in.nextLine();
                System.out.println("このターンのダメージは０");
                e_result=enemy_hitpoint;
                c_result=character_hitpoint;

            }else if (input_comand.equals("d")){
                int[] result =Magic(character_hitpoint,character,enemy_hitpoint,enemy);
                c_result=result[0];
                e_result=result[1];

            }else if (input_comand.equals("f")){
                System.out.println("にげろー");

            }else{
                continue;
            }

            enemy_hitpoint=e_result;
            character_hitpoint=c_result;

            if (enemy_hitpoint<=0||character_hitpoint<=0){
                success=true;

                System.out.println("しゅうりょう！");
            }

        }



    }


    public int[] attack(Character character, Enemy enemy, int character_hitpoint, int enemy_hitpoint){

        int c_result = 0;
        int e_result = 0;

        if (character.character_speed>enemy.enemy_speed){
                    e_result=character.attack(enemy_hitpoint,enemy.enemy_defense,character_hitpoint);
                    if (e_result>0){
                        c_result=enemy.attack(character_hitpoint,character.character_defense,enemy_hitpoint);
                    }
                }else{
                    c_result=enemy.attack(character_hitpoint,character.character_defense,enemy_hitpoint);
                    if (c_result>0){
                        e_result=character.attack(enemy_hitpoint,enemy.enemy_defense,character_hitpoint);
                    }
        }
         int[] results = {c_result,e_result};
        return results;
    }


    public int[] Magic(int character_hp, Character character, int enemy_hp, Enemy enemy) throws IOException {
        Tools tool = new Tools();
        System.out.println(tool.fileToString(new File("./sentence/magic_serect.txt")));

        System.out.print("こまんどをどうぞ　＞＞＞");
        String input_comand = in.nextLine();


        int c_hp = 0;
        int e_hp = 0;
        if (input_comand.equals("a")){
            System.out.println("『あなた』は回復をつかった");
            c_hp=character_hp+(character_hp/2);
            System.out.println("HPが"+character_hp/2+"かいふく");
            in.nextLine();
            if (c_hp>character.character_physical){
                c_hp=character.character_physical;
            }
            int[] result = attack(character,enemy,c_hp,enemy_hp);
            c_hp=result[0];
            e_hp=result[1];

        }else if (input_comand.equals("s")){
            c_hp=character_hp;
            e_hp=enemy_hp;

            System.out.println("『あなた』はついげきをつかった");
            System.out.println("このターンのみ攻撃力が1.5倍");
            in.nextLine();
            int return_attack = character.character_attack;
            int add_attack = (int) (character.character_attack*(1.5));

            character.character_attack=add_attack;

            int[] result = attack(character,enemy,c_hp,enemy_hp);

            character.character_attack=return_attack;

            c_hp=result[0];
            e_hp=result[1];


        }else if(input_comand.equals("d")){
            c_hp=character_hp;
            e_hp=enemy_hp;

            System.out.println("『あなた』はかちこちをつかった");
            System.out.println("このターンのみ防御力が1.5倍");
            in.nextLine();
            int return_defence =character.character_defense;
            int add_defense = (int) (character.character_defense*(1.5));

            character.character_defense=add_defense;

            int[] result = attack(character,enemy,c_hp,enemy_hp);

            character.character_defense=return_defence;

            c_hp=result[0];
            e_hp=result[1];

        }else if (input_comand.equals("f")){
            c_hp=character_hp;
            e_hp=enemy_hp;

            System.out.println("『あなた』はすばやくをつかった");
            System.out.println("このターンのみ速さが1.5倍");
            in.nextLine();
            int return_speed = character.character_speed;
            int add_speed = (int) (character.character_speed*(1.5));

            character.character_speed=add_speed;

            int[] result = attack(character,enemy,c_hp,enemy_hp);

            character.character_speed=return_speed;

            c_hp=result[0];
            e_hp=result[1];


        }else{
            c_hp=character_hp;
            e_hp=enemy_hp;

        }

        int[] results = {c_hp,e_hp};
        return results;

    }

}

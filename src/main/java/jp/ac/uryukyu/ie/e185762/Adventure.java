package jp.ac.uryukyu.ie.e185762;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;



public class Adventure {
    Scanner in = new Scanner(System.in);
    Fight fight = new Fight();
    Tools tool = new Tools();



    //物語パートの文章を文章ファイルから読み込み、エンター入力に応じて出力させるメソッドです。
    public void Adventure(String filename) throws IOException {
        Tools tools = new Tools();
        String sentence = tools.fileToString(new File(filename));
        String[] word_list = sentence.split(",",0);

        int count = 0;
        for(int num = 0; num<word_list.length; num++) {

            if (word_list[count].contains("&")) {
                System.out.println();
                System.out.print("選択肢をにゅうりょくしてね　＞＞＞");
                String input_text = in.nextLine();
                SerectChoice(input_text, word_list[count]);
            //}else if (word_list[count].contains("メニュー表示")){
              //  MainMenu();

            }else{
                System.out.print(word_list[count]+"　＞＞＞");
                in.nextLine();
            }
            count++;
            }
        }


    public void SerectChoice(String imput, String choice_sentence) throws IOException{

        String[] choice_sentences = choice_sentence.split("&", 0);
        String[] split_sentence = choice_sentences[1].split("%",0);

        String[] choice_comands = {"a","s","d","f"};

        boolean success = false;
        for(int num=0;num<split_sentence.length;num++){
            if(imput.equals(choice_comands[num])){
                Adventure(split_sentence[num]);
                success = true;
            }else if(num==split_sentence.length-1){
                System.out.print("もういちどおねがいします　＞＞＞");
                String input_text = in.nextLine();
                SerectChoice(input_text,choice_sentence);
            }
            if (success==true){
                break;
            }
        }

    }

    public void MainMenu() throws IOException {
        Adventure("./sentence/mainmenu.tzt");
        System.out.println("");
        System.out.println("＞＞＞");

        String input_order = in.nextLine();

        if (input_order.equals("a")){

        }else if (input_order.equals("s")){

        }


    }

    public void StoryReader(Character character,Enemy enemy,String character_code, int story_part,int enemy_attack_number) throws IOException {
        String[] choice_comands = {"a", "s", "d"};
        String[] character_dir = {"doll_stories","hero_stories","tank.stories"};

        String[] story_list = null;
        String dir_name = null;

        for (int num = 0; num<choice_comands.length; num++){
            if (choice_comands[num].equals(character_code)){
                File dir = new File("./sentence/story_list/"+character_dir[num]);
                story_list= dir.list();
                dir_name=character_dir[num];

            }
        }

        for (int num = 1; num<story_list.length;num++){
            Adventure("./sentence/story_list/"+dir_name+"/"+story_part+"_story_list/doll_"+String.valueOf(num)+".txt");


            if (num<story_list.length-1) {
                int enemy_attack = 0;
                boolean success = false;
                while (success == false) {
                    System.out.println("");
                    System.out.println(tool.fileToString(new  File("./sentence/mainmenu.txt")));
                    System.out.print("＞＞＞");

                    String input_order = in.nextLine();

                    if (input_order.equals("a")) {
                        CharacterTalk(dir_name);

                    } else if (input_order.equals("s")) {
                        enemy.EnemyDicision();
                        fight.Fight(character, enemy);
                        enemy_attack++;
                        if (enemy_attack == enemy_attack_number) {
                            success = true;
                        }

                    } else {
                        continue;
                    }

                }
            }else{
                System.out.println("おわり");
            }



        }

    }

    public void CharacterTalk(String dirctor_name) throws IOException {
        Random random = new Random();
        int num = random.nextInt(3)+1;
        Adventure("./sentence/story_list/"+dirctor_name+"/talk_story_list/talk"+num+".txt");

    }



    public void TitlePrint() throws IOException {
        Adventure("./sentence/title.txt");
    }

    public void FirstStory() throws IOException{
        Adventure("./sentence/story_list/first.txt");
    }


}

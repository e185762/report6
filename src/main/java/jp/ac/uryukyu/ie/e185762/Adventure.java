package jp.ac.uryukyu.ie.e185762;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Adventure {
    Scanner in = new Scanner(System.in);
    Character character = new Character();



    String[] first_story = {"./sentence/story_list/first_story_list/first.txt",};

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
            /*
            } else if (word_list[count].contains("¥")){
                System.out.println();
                System.out.print("『心臓』をせんたくしてね　＞＞＞");
                String input_text = in.nextLine();
                CharacterMake(input_text,word_list[count]);
            */
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


    /*public void CharacterMake(String imput) throws IOException {

        String character_url = "./sentence/character_list/doll.txt%./sentence/character_list/hero.txt%./sentence/character_list/tank.txt\n";

        String[] split_sentence = character_url.split("%", 0);

        //String[] choice_sentences = choice_sentence.split("¥", 0);
        //String[] split_sentence = choice_sentences[1].split("%",0);

        String[] choice_comands = {"a","s","d"};

        int number =0;
        for(int num=0;num<split_sentence.length;num++){
            if(imput.equals(choice_comands[num])){
                System.out.println("");
                System.out.println("「ほんとうによろしいですか？」");
                System.out.print("yes or no　＞＞＞");
                String input_again_text = in.nextLine();
                if (input_again_text.equals("yes")) {
                    character.character_code = choice_comands[num];
                    break;
                }else{
                    System.out.print("どれにしますか？　＞＞＞");
                    String input_again = in.nextLine();
                    CharacterMake(input_again);
                }

            }else{
                System.out.print("もういちどおねがいします　＞＞＞");
                String input_text = in.nextLine();
                CharacterMake(input_text);
            }
        }
    }*/



    public void TitlePrint() throws IOException {
        Adventure("./sentence/title.txt");
    }

    public void FirstStory() throws IOException{
        Adventure("./sentence/story_list/first_story_list/first.txt");
    }


}

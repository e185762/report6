package jp.ac.uryukyu.ie.e185762;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

/**
 * ストーリーパートを実行するクラス
 */

public class Adventure {
    /**
     * ユーザー入力を受け取るためのScannerのコンストラクタ
     */
    Scanner in = new Scanner(System.in);
    /**
     * Fightクラスのコンストラクタ
     */
    Fight fight = new Fight();
    /**
     * Toolsクラスのコンストラクタ
     */
    Tools tool = new Tools();



    /**
     * ストーリーパートの文章をテキストファイルから読み込み、エンター入力に応じて出力させるメソッド
     * 選択肢にも対応
     *
     * @param filename 　テキストファイルの場所　例：「./sentence/story_list/1_story_list/~」
     * @throws IOException ファイル入出力時に起こる例外
     */
    public void Adventure(String filename) throws IOException {
        Tools tools = new Tools();
        String sentence = tools.fileToString(new File(filename));
        String[] word_list = sentence.split(",", 0);

        int count = 0;
        for (int num = 0; num < word_list.length; num++) {

            if (word_list[count].contains("&")) {
                System.out.println();
                System.out.print("選択肢をにゅうりょくしてね　＞＞＞");
                String input_text = in.nextLine();
                SerectChoice(input_text, word_list[count]);
                //}else if (word_list[count].contains("メニュー表示")){
                //  MainMenu();

            } else {
                System.out.print(word_list[count] + "　＞＞＞");
                in.nextLine();
            }
            count++;
        }
    }


    /**
     * Adventure()で選択肢を促す文章が出てきた場合に、分岐の処理をするメソッド
     *
     * @param imput　選んだ選択肢の文字
     * @param choice_sentence　Adventure()から読み込んだ分岐の文章テキストの場所　例：「./sentence/story_list/1_story_list/choice_1/~」
     * @throws IOException ファイル入出力時に起こる例外
     */
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

    /**
     * ストーリーパートと戦闘パートを繋げるメソッド
     *
     * @param character　Characterクラスのコンストラクタ
     * @param enemy　Enemyクラスのコンストラクタ
     * @param character_code　キャラクタの種類を決める文字
     * @param story_part　「今何章目か」の数字を入力　例：１章の場合「１」　２章の場合「２」
     * @param enemy_attack_number　この章を終わらせるために「倒すべきエネミーの数」　例：４匹の時「４」　３匹の時「３」
     * @throws IOException ファイル入出力時に起こる例外
     */
    public void StoryReader(Character character,Enemy enemy,String character_code, int story_part,int enemy_attack_number) throws IOException {
        String[] choice_comands = {"a", "s", "d"};
        String[] character_dir = {"doll_stories","hero_stories","tank_stories"};

        String[] story_list = null;
        String dir_name = null;

        for (int num = 0; num<choice_comands.length; num++){
            if (choice_comands[num].equals(character_code)){
                File dir = new File("./sentence/story_list/"+character_dir[num]+"/"+story_part+"_story_list");
                story_list= dir.list();
                dir_name=character_dir[num];
            }
        }

        for (int num = 1; num<story_list.length+1;num++){
            Adventure("./sentence/story_list/"+dir_name+"/"+story_part+"_story_list/"+String.valueOf(num)+".txt");

            if (num<story_list.length) {
                int enemy_attack = 0;
                boolean success = false;
                while (success == false) {
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

    /**
     *StoryReader()でのMainmenu画面で「おはなしする」を選択した時に
     *ランダムで文章テキストを出力するメソッド
     *
     * @param dirctor_name 選択したキャラクタのディレクトリ名
     * @throws IOException ファイル入出力時に起こる例外
     */
    public void CharacterTalk(String dirctor_name) throws IOException {
        File dir = new File("./sentence/story_list/"+dirctor_name+"/talk_story_list");
        String[] story_list= dir.list();
        Random random = new Random();
        int num = random.nextInt(story_list.length)+1;
        //int num = random.nextInt(3)+1;
        Adventure("./sentence/story_list/"+dirctor_name+"/talk_story_list/talk"+num+".txt");

    }


    /**
     * タイトルを表示するメソッド
     *
     * @throws IOException ファイル入出力時に起こる例外
     */
    public void TitlePrint() throws IOException {
        Adventure("./sentence/title.txt");
    }

    /**
     * 導入部のストーリーを出力するメソッド
     *
     * @param character Characterクラスのコンストラクタ
     * @return　キャラクタの種類を決める文字
     * @throws IOException ファイル入出力時に起こる例外
     */

    public String FirstStory(Character character) throws IOException{
        Adventure("./sentence/story_list/first.txt");
        boolean input = false;
        String[] character_string = null;
        System.out.print("どれにしますか？　＞＞＞");
        while (input==false){
            String input_text = in.nextLine();
            character_string = character.CharacterSerect(input_text);

            if (character_string[1] == ("true")){
                input=!input;
            }

        }
        String returns= character_string[0];
        return returns;
    }


}

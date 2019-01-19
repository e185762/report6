package jp.ac.uryukyu.ie.e185762;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;


public class Tools {

    Adventure adventure = new Adventure();
    Fight fight = new Fight();

    // ファイル内容をを文字列化するメソッド。
    // 参考http://www7a.biglobe.ne.jp/~java-master/samples/file/FileToString.html
    public String fileToString(File file) throws IOException {
        BufferedReader reader = null;
        try {
            // ファイル内容を文字列化するバッファドリーダを作成。
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            // 読み込んだ文字を維持するストリングバッファを用意。
            StringBuffer buffer = new StringBuffer();
            // 読み込んだ一文字を保存する変数。
            int c;
            // ファイルから１文字ずつ読み込んでバッファに追加。
            while ((c = reader.read()) != -1) {
                buffer.append((char) c);
            }
            // バッファの内容を文字列化して返す。
            return buffer.toString();
        } finally {
            // リーダを閉じる。
            reader.close();
        }

    }

    public void StoryReader(Character character,Enemy enemy,String character_code, int story_part) throws IOException {
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

        //adventure.Adventure("./sentence/story_list/doll_stories/1_story_list/doll_1.txt");

        for (int num = 1; num<story_list.length+1;num++){
            adventure.Adventure("./sentence/story_list/"+dir_name+"/"+story_part+"_story_list/doll_"+String.valueOf(num)+".txt");
            //enemy.EnemyDicision();
            //fight.Fight(character,enemy);
        }

    }



}

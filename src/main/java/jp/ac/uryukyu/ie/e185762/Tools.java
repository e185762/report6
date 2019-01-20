package jp.ac.uryukyu.ie.e185762;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;





public class Tools {

    /**
     * ファイル内容をを文字列化するメソッド
     * 参考http://www7a.biglobe.ne.jp/~java-master/samples/file/FileToString.html
     *
     * @param file_name 文字列化したいテキストファイルの場所　例：「./sentence/~」
     * @return　文字列化したテキストファイル
     * @throws IOException　入出力処理の失敗によって生成される例外クラス
     */
    public String fileToString(File file_name) throws IOException {
        BufferedReader reader = null;

        reader = new BufferedReader(new InputStreamReader(new FileInputStream(file_name)));
        StringBuffer buffer = new StringBuffer();

        int word;
        while ((word = reader.read()) != -1) {
            buffer.append((char) word);
        }

        return buffer.toString();

    }



}

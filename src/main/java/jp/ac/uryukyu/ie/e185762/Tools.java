package jp.ac.uryukyu.ie.e185762;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;




public class Tools {


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



}

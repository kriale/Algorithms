package edu.kriale.algorithms.sem6.task7;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Runner {
    public static void main(String[] args) throws IOException {
        BoyerMoore bm = new BoyerMoore("pat");

        System.out.println(bm.search("vaabrpatbdf"));

        System.out.println(bm.search("vaabrphtbdf"));

        DataInputStream in = new DataInputStream(
                new FileInputStream("src/edu/kriale/algorithms/sem6/task7/text.txt"));
        System.out.println(bm.search(in));
    }
}

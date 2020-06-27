package edu.kriale.algorithms.sem6.task7;

import java.io.IOException;
import java.io.InputStream;

public class BoyerMoore {
    private final int R = 256;
    private int right[] = new int[R];
    private String pat;
    private int m;

    public BoyerMoore(String pat) {
        this.pat = pat;
        this.m = pat.length();
        for (int i = 0; i < R; i++) {
            right[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            right[pat.charAt(i)] = i;
        }
    }

    public int search(String txt) {
        int n = txt.length();
        int skip;
        for (int i = 0; i < n - m; i += skip) {
            skip = 0;
            for (int j = m-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i + j)) {
                    skip = j - right[txt.charAt(i + j)];
                    if (skip < 0) {
                        skip = 1;
                    }
                    break;
                }
            }
            if (skip == 0) {
                return i;
            }
        }
        return -1;
    }

    public int search(InputStream stream) throws IOException {
        int c;
        StringBuilder txt = new StringBuilder();
        while((c = stream.read()) != -1){
            txt.append((char)c);
        }
        return search(txt.toString());
    }
}

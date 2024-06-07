import parcs.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Bluck{

    public static void main(String[] args) throws Exception {
        task curtask = new task();
        curtask.addJarFile("BoyerMoore.jar");

        String text = textFromFile( curtask.findFile("input") );
        String pattern = patternFromFile( curtask.findFile("pattern"));

        AMInfo info = new AMInfo(curtask, null);

        int N = 10;
        int n = text.length() / N;
        int M = pattern.length();

        List<String> texts = new ArrayList<>();
        List<Integer> shifts = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int l = i * n;
            int r = (i + 1) * n;
            String textPart = text.substring(l, r);
            texts.add(textPart);
            shifts.add(l);
            if (i < N - 1) {
                int ll = r - (M - 1);
                int rr = r + M - 1;
                String text1 = text.substring(ll, rr);
                texts.add(text1);
                shifts.add(ll);
            }
        }

        List<point> points = new ArrayList<>();
        List<channel> channels = new ArrayList<>();

        for (int i = 0; i < texts.size(); i++) {
            String t = texts.get(i);
            Integer shift = shifts.get(i);

            point p = info.createPoint();
            channel c = p.createChannel();

            points.add(p);
            channels.add(c);

            Input input = new Input(t, pattern);

            p.execute("BoyerMoore");
            c.write(input);

            System.out.println("Waiting for result .. ");

            Result result = (Result) (c.readObject());
            List<Integer> ins = result.getRes();
            if (ins.size() > 0) {
                System.out.println("Pattern ins : {");
                for (int index : ins) {
                    System.out.print("{Shift: " + shift + " index: " + index + "} ");
                }
                System.out.println("}");
                System.out.println("Size: " + ins.size());
            }
        }


        curtask.end();
    }
    public static String textFromFile(String filename) throws Exception {

        String text = new Scanner(new File(filename)).useDelimiter("\\Z").next();

        return text;
    }
    public static String patternFromFile(String filename) throws Exception {
        String pattern = "";

        Scanner sc = new Scanner(new File(filename));

        pattern = sc.nextLine();

        return pattern;
    }
}

package 基础.拓展.钢琴;

import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class Main {

    public static void main(String[] args) throws Exception {
        String prePath = "E:\\ideaProject\\NOTE\\src\\基础\\拓展\\钢琴\\钢琴音频";
        Consumer<Integer> keyPress = code -> {
            String keyText = Listener.getKeyText(code);
            String filePath = prePath + "\\" + keyToNumber(keyText) + ".wav";
            Voice.play(filePath);
        };
        new Listener(keyPress, null);
    }

    @Nullable
    private static String keyToNumber(String keyText) {
        return switch (keyText) {
            case "Q" -> "1";
            case "W" -> "2";
            case "E" -> "3";
            case "R" -> "4";
            case "T" -> "5";
            case "Y" -> "6";
            case "U" -> "7";
            case "I" -> "8";
            case "O" -> "9";
            case "P" -> "10";
            case "左方括号" -> "11";
            case "右方括号" -> "12";
            default -> {
                System.out.println("不支持的键: "+keyText);
                yield null;
            }
        };
    }

}

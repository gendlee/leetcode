package utils;

public class Parsor {

    // 解析以逗号分隔的整数数组
    public static int[] parseString2IntArray(String input) {
        if (input == null || input.isEmpty()) return new int[0];
        String[] parts = input.split("[,，]");
        int[] result = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            result[i] = Integer.parseInt(parts[i].trim());
        }
        return result;
    }
}

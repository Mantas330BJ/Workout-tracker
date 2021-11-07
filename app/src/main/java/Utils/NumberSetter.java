package Utils;

public class NumberSetter {

    public static double getDoubleFromString(String s) {
        return s.isEmpty() ? 0 : Double.parseDouble(s);
    }

    public static int getIntegerFromString(String s) {
        return s.isEmpty() ? 0 : Integer.parseInt(s);
    }
}

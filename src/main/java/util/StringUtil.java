package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil
{
    public static boolean isNumeric(String str)
    {
        Pattern pattern = Pattern.compile("^[-+]?\\d+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() )
        {
            return false;
        }
        return true;
    }

    public static String removePunctuation(String str)
    {
        Pattern p=Pattern.compile("[.,\"\\?!:']:;");
        Matcher m=p.matcher(str);
        String result=m.replaceAll("");

        return result;
    }
}


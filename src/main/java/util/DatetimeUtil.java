package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DatetimeUtil
{
    private static List<SimpleDateFormat> simpleDateFormats;

    private static List<SimpleDateFormat> simpleDatetimeFormats;

    static
    {
        simpleDateFormats.add(new SimpleDateFormat("yyyy/MM/dd"));
        simpleDateFormats.add(new SimpleDateFormat("yyyy-MM-dd"));

        simpleDatetimeFormats.add(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        simpleDatetimeFormats.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
    public static boolean isDate(String datetimeStr)
    {
        boolean isDate = false;

        for(SimpleDateFormat simpleDateFormat : simpleDateFormats)
        {
            try
            {
                simpleDateFormat.parse(datetimeStr);
                isDate = true;
            } catch (ParseException e)
            {
            }
        }

        return isDate;
    }

    public static boolean isDateTime(String datetimeStr)
    {
        boolean isDatetime = false;

        for(SimpleDateFormat simpleDateFormat : simpleDatetimeFormats)
        {
            try
            {
                simpleDateFormat.parse(datetimeStr);
                isDatetime = true;
            } catch (ParseException e)
            {
            }
        }

        return isDatetime;
    }
}

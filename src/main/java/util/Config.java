package util;

import java.util.ResourceBundle;

public class Config
{
    private static final ResourceBundle jdbcBundle = ResourceBundle.getBundle("jdbc");

    public static final String JDBCDRIVERCLASSNAME = jdbcBundle.getString("jdbc_driverClassName");

    public static final String JDBCURL = jdbcBundle.getString("jdbc_url");

    public static final String JDBCUSERNAME = jdbcBundle.getString("jdbc_username");

    public static final String JDBCPASSWORD = jdbcBundle.getString("jdbc_password");

    public static final String JDBCFETCHSIZE = jdbcBundle.getString("jdbc_fetch_size");

    // --------------------------------------------------------------------------------------------------

    private static final ResourceBundle analysisBundle = ResourceBundle.getBundle("analysis");

    public static final int THREAD_COUNT = Integer.parseInt(analysisBundle.getString("thread_count"));

    public static final long ANALYSE_TIMEOUT = Long.parseLong(analysisBundle.getString("analyse_timeout"));

    public static final int ROW_MAX_COUNT = Integer.parseInt(analysisBundle.getString("row_max_count"));

    public static final int COLUMN_MAX_COUNT = Integer.parseInt(analysisBundle.getString("column_max_count"));

    // --------------------------------------------------------------------------------------------------

    public static final int REGRESSION_MIN_REG_DOT = Integer.parseInt(analysisBundle.getString("regression_minimum_regress_dot"));

    public static final double REGRESSION_GFI = Double.parseDouble(analysisBundle.getString("regression_goodness_of_fit"));

    public static final int REGRESSION_SIMPLING_MAX_COUNT = Integer.parseInt(analysisBundle.getString("regression_simpling_max_count"));

}

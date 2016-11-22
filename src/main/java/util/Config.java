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

    public static final int MIN_REG_DOT = Integer.parseInt(analysisBundle.getString("minimum_regress_dot"));

    public static final double GFI = Double.parseDouble(analysisBundle.getString("goodness_of_fit"));

}

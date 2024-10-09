package com.asklepios.backend_service.util;

import com.asklepios.backend_service.model.generated.pojo.ApMessages;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Utilities {


    public Utilities() {
    }

    public InputStream loadResource(String resourceName) {
        return this.getClass().getClassLoader().getResourceAsStream(resourceName);
    }

    public String getConfigProperty(String key) throws Exception {
        Properties props = new Properties();
        props.load(loadResource("config.properties"));
        return props.getProperty(key);
    }

    public String getProperty(String key, String prpertyName) throws Exception {
        Properties props = new Properties();
        props.load(loadResource(prpertyName));
        return props.getProperty(key);
    }

    public String formatDate(Date date) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }

    public String formatDouble(Double value) {
        if (value == null) {
            return "";
        }
        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);
        return myFormat.format(value);
    }

    public String formatDouble(BigDecimal value) {
        if (value == null) {
            return "";
        }
        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);
        return myFormat.format(value.doubleValue());
    }

    public String formatDoubleOneDecimals(Double value) {
        if (value == null) {
            return "";
        }
        NumberFormat myFormat = NumberFormat.getInstance();
        myFormat.setGroupingUsed(true);
        myFormat.setMaximumFractionDigits(1);
        return myFormat.format(value);
    }

    public static Double formatDoubleGlobalWithParse(Double value) {
        try {
            if (value == null) {
                return 0.0;
            }
            DecimalFormat df = new DecimalFormat("#.###");
            return Double.parseDouble(df.format(value));

        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public Double formatDoubleTwoDecimals(Double value) {
        try {
            if (value == null) {
                return 0.0;
            }
            DecimalFormat df = new DecimalFormat("#.##");
            return Double.parseDouble(df.format(value));

        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public Double formatDoubleFourDecimals(Double value) {
        try {
            if (value == null) {
                return 0.0;
            }
            DecimalFormat df = new DecimalFormat("#.####");
            return Double.parseDouble(df.format(value));

        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    public String formatPercentage(Double value) {
        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(1);
        return defaultFormat.format(value);
    }

    public static String formatPercentageGlobal(Double value) {
        NumberFormat defaultFormat = NumberFormat.getPercentInstance();
        defaultFormat.setMinimumFractionDigits(1);
        return defaultFormat.format(value);
    }

    public static synchronized int calculateAge(Date birthDate) {
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(birthDate);

        Calendar currentCalendar = Calendar.getInstance();

        int years = currentCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);
        int months = currentCalendar.get(Calendar.MONTH) - birthCalendar.get(Calendar.MONTH);
        int days = currentCalendar.get(Calendar.DAY_OF_MONTH) - birthCalendar.get(Calendar.DAY_OF_MONTH);

        // Adjust for cases where the birthdate hasn't occurred yet this year
        if (months < 0 || (months == 0 && days < 0)) {
            years--;
        }

        return years;
    }

    public static synchronized String generateOTP() {
        String numbers = "0123456789";
        String otp = "";
        // Using random method
        Random random_method = new Random();
        for (int i = 0; i < 4; i++) {
            otp += numbers.charAt(random_method.nextInt(numbers.length()));
        }
        return otp;
    }

    public static synchronized String generateToken() {
        return UUID.randomUUID().toString();
    }



}

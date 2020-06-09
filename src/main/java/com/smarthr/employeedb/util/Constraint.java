package com.smarthr.employeedb.util;

public class Constraint {

    public static final int MIN_EDRPO = 10000000;
    public static final int MAX_EDRPO = 99999999;
    public static final long MIN_INN = 1000000000L;
    public static final long MAX_INN = 9999999999L;

    public static final String INN_MESSAGE = "INN should be 10 symbols";
    public static final String EDRPO_MESSAGE = "EDRPO should be 8 symbols";
    public static final String INN_SIZE_MESSAGE = "You should enter less than 256 symbols!";
    public static final String EDRPO_SIZE_MESSAGE = "You should enter less than 226 symbols!";
}

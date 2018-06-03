package com.sweethome;

import java.lang.*;

class Main
{
    public static void test() throws InterruptedException {
        Price price1 = new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("09.01.2013 23:59:59"), 11000);
        Price price2 = new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("09.01.2013 23:59:59"), 11000);
        System.out.println(price1.equals(price2));
    }

    public static void main (String[] args) throws java.lang.Exception
    {
        Test.test0();
        Test.test1();
        Test.test2();
        Test.test3();
    }
}
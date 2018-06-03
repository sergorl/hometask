package com.sweethome;

import java.util.Arrays;

public class Test {
    static void test0() {

        System.out.println("Scenario 0.1");

        TablePrice prices = new TablePrice();

        prices.add(new Price("122856", 1, 1, new Date("01.01.2013 00:00:00"), new Date("31.01.2013 23:59:59"), 11000));
        prices.add(new Price("122856", 2, 1, new Date("10.01.2013 00:00:00"), new Date("20.01.2013 23:59:59"), 99000));
        prices.add(new Price("6654",   1, 2, new Date("01.01.2013 00:00:00"), new Date("31.01.2013 00:00:00"), 5000));

        prices.add(new Price("122856", 1, 1, new Date("20.01.2013 00:00:00"), new Date("20.02.2013 23:59:59"), 11000));
        prices.add(new Price("122856", 2, 1, new Date("15.01.2013 00:00:00"), new Date("25.01.2013 23:59:59"), 92000));
        prices.add(new Price("6654",   1, 2, new Date("12.01.2013 00:00:00"), new Date("13.01.2013 00:00:00"), 4000));

        prices.print();

        TablePrice control_prices = TablePrice.create( Arrays.asList(
            new Price("122856", 1, 1, new Date("01.01.2013 00:00:00"), new Date("20.02.2013 23:59:59"), 11000),
            new Price("122856", 2, 1, new Date("10.01.2013 00:00:00"), new Date("14.01.2013 23:59:59"), 99000),
            new Price("122856", 2, 1, new Date("15.01.2013 00:00:00"), new Date("25.01.2013 23:59:59"), 92000),
            new Price("6654",   1, 2, new Date("01.01.2013 00:00:00"), new Date("11.01.2013 23:59:59"), 5000),
            new Price("6654",   1, 2, new Date("12.01.2013 00:00:00"), new Date("13.01.2013 00:00:00"), 4000),
            new Price("6654",   1, 2, new Date("13.01.2013 00:00:01"), new Date("31.01.2013 00:00:00"), 5000)
        ));

        assert prices.equals(control_prices);

        System.out.println("Scenario 0.1 is OK.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }

    static void test1() {
        // A, B = different values of prices
        /* Scenario 1.1
             ________
            |___A____|
                            ________
                           |___B____|
             _________      ________
            |____A____|    |___B____|
        */
        System.out.println("Scenario 1.1");
        TablePrice prices_11 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("20.01.2013 23:59:59"), 11000), // A
            new Price("1", 1, 1, new Date("01.02.2013 00:00:00"), new Date("20.02.2013 23:59:59"), 99000)  // B
        ));
        prices_11.print();

        assert prices_11.countById("1", 1, 1) == 2;

        System.out.println("Scenario 1.1 is OK.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        //-------------------------------------------------------------------------------------------------------------
        /* Scenario 1.21
             ________
            |___A____|
                  ________
                 |____B___|
             _____________
            |__A_|____B___|

        */
        System.out.println("Scenario 1.21");

        TablePrice prices_121 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("15.01.2013 23:59:59"), 11000), // A
            new Price("1", 1, 1, new Date("10.01.2013 00:00:00"), new Date("25.02.2013 23:59:59"), 99000)  // B
        ));
        prices_121.print();

        TablePrice control_prices_121 = TablePrice.create( Arrays.asList(
                new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("09.01.2013 23:59:59"), 11000), // A
                new Price("1", 1, 1, new Date("10.01.2013 00:00:00"), new Date("25.02.2013 23:59:59"), 99000)  // B
        ));

        assert prices_121.equals(control_prices_121);

        System.out.println("Scenario 1.21 is OK.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        //-------------------------------------------------------------------------------------------------------------
        /* Scenario 1.21
                   ________
                  |___A____|
             ________
            |____B___|
             ______________
            |____B___|__A__|

        */
        System.out.println("Scenario 1.22");

        TablePrice prices_122 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("10.01.2013 00:00:00"), new Date("30.01.2013 23:59:59"), 11000), // A
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("20.01.2013 23:59:59"), 99000)  // B
        ));
        prices_122.print();

        TablePrice control_prices_122 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("20.01.2013 23:59:59"), 99000), // B
            new Price("1", 1, 1, new Date("21.01.2013 00:00:00"), new Date("30.01.2013 23:59:59"), 11000)  // A
        ));

        assert prices_122.equals(control_prices_122);

        System.out.println("Scenario 1.22 is OK.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        //-------------------------------------------------------------------------------------------------------------
        /* Scenario 1.31
             ________
            |____A___|
                  ________
                 |____A___|
             _____________
            |______A______|
        */
        System.out.println("Scenario 1.31");

        TablePrice prices_131 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("15.01.2013 23:59:59"), 11000), // A
            new Price("1", 1, 1, new Date("10.01.2013 00:00:00"), new Date("20.02.2013 23:59:59"), 11000)  // A
        ));
        prices_131.print();

        TablePrice control_prices_131 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("20.02.2013 23:59:59"), 11000)  // A
        ));

        assert prices_131.equals(control_prices_131);

        System.out.println("Scenario 1.31 is OK.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        //-------------------------------------------------------------------------------------------------------------
        /* Scenario 1.32
             ________
            |____A___|
                      ________
                     |____A___|
             _________________
            |____A___|____A___|
        */
        System.out.println("Scenario 1.32");

        TablePrice prices_132 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("15.01.2013 23:59:59"), 11000), // A
            new Price("1", 1, 1, new Date("16.01.2013 00:00:00"), new Date("30.01.2013 23:59:59"), 11000)  // A
        ));
        prices_132.print();

        assert prices_132.countById("1", 1, 1) == 2;

        System.out.println("Scenario 1.32 is OK.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        //-------------------------------------------------------------------------------------------------------------
        /* Scenario 1.4
             _________________
            |________A________|
                 _________
                |____B____|
             __________________
            |_A_|____B____|_A__|
        */
        System.out.println("Scenario 1.4");

        TablePrice prices_14 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("31.01.2013 23:59:59"), 11000), // A
            new Price("1", 1, 1, new Date("10.01.2013 00:00:00"), new Date("20.01.2013 23:59:59"), 9000)   // B
        ));
        prices_14.print();

        TablePrice control_prices_14 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("09.01.2013 23:59:59"), 11000), // A
            new Price("1", 1, 1, new Date("10.01.2013 00:00:00"), new Date("20.01.2013 23:59:59"), 9000),  // B
            new Price("1", 1, 1, new Date("21.01.2013 00:00:00"), new Date("31.01.2013 23:59:59"), 11000)  // A
        ));

        assert prices_14.equals(control_prices_14);

        System.out.println("Scenario 1.4 is OK.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        //-------------------------------------------------------------------------------------------------------------
        /* Scenario 1.5
             _________________
            |________A________|
                 _________
                |____A____|
             _________________
            |________A________|
        */
        System.out.println("Scenario 1.5");

        TablePrice prices_15 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("31.01.2013 23:59:59"), 11000), // A
            new Price("1", 1, 1, new Date("10.01.2013 00:00:00"), new Date("20.01.2013 23:59:59"), 11000)  // A
        ));
        prices_15.print();

        TablePrice control_prices_15 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("31.01.2013 23:59:59"), 11000) // A
        ));

        assert prices_15.equals(control_prices_15);

        System.out.println("Scenario 1.5 is OK.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
    }

    static void test2() {
        // A, B, C = three different values of prices
        /* Scenario 2.1
             ________    ________
            |___A____|  |____B___|
                ____________
               |______C_____|
             ____________________
            |_A|______C_____|__B_|

        */
        System.out.println("Scenario 2.1");

        TablePrice prices_21 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("30.01.2013 23:59:59"), 11000), // A
            new Price("1", 1, 1, new Date("10.02.2013 00:00:00"), new Date("28.02.2013 23:59:59"), 99000), // B
            new Price("1", 1, 1, new Date("15.01.2013 00:00:00"), new Date("20.02.2013 23:59:59"), 30000)  // C
        ));
        prices_21.print();

        TablePrice control_prices_21 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("14.01.2013 23:59:59"), 11000), // A
            new Price("1", 1, 1, new Date("15.01.2013 00:00:00"), new Date("20.02.2013 23:59:59"), 30000), // C
            new Price("1", 1, 1, new Date("21.02.2013 00:00:00"), new Date("28.02.2013 23:59:59"), 99000)  // B
        ));

        assert prices_21.equals(control_prices_21);

        System.out.println("Scenario 2.1 is OK.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        //-------------------------------------------------------------------------------------------------------------
        /* Scenario 2.2
             ___________________
            |___A______|___B____|
                ____________
               |______C_____|
             ___________________
            |_A|______C_____|_B_|

        */
        System.out.println("Scenario 2.2");

        TablePrice prices_22 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("30.01.2013 23:59:59"), 11000),  // A
            new Price("1", 1, 1, new Date("31.01.2013 00:00:00"), new Date("28.02.2013 23:59:59"), 99000),  // B
            new Price("1", 1, 1, new Date("15.01.2013 00:00:00"), new Date("20.02.2013 23:59:59"), 30000)   // C
        ));
        prices_22.print();

        TablePrice control_prices_22 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("14.01.2013 23:59:59"), 11000),  // A
            new Price("1", 1, 1, new Date("15.01.2013 00:00:00"), new Date("20.02.2013 23:59:59"), 30000),  // C
            new Price("1", 1, 1, new Date("21.02.2013 00:00:00"), new Date("28.02.2013 23:59:59"), 99000)   // B
        ));

        assert prices_22.equals(control_prices_22);

        System.out.println("Scenario 2.2 is OK.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        //-------------------------------------------------------------------------------------------------------------
        /* Scenario 2.3
             _____          _______
            |__A__|        |___B___|
                     ____
                    |__C_|
             _____   ____   _______
            |__A__| |__C_| |___B___|

        */
        System.out.println("Scenario 2.3");

        TablePrice prices_23 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("15.01.2013 23:59:59"), 11000), // A
            new Price("1", 1, 1, new Date("10.02.2013 00:00:00"), new Date("28.02.2013 23:59:59"), 99000), // B
            new Price("1", 1, 1, new Date("20.01.2013 00:00:00"), new Date("01.02.2013 23:59:59"), 30000)  // C
        ));
        prices_23.print();

        TablePrice control_prices_23 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("15.01.2013 23:59:59"), 11000), // A
            new Price("1", 1, 1, new Date("20.01.2013 00:00:00"), new Date("01.02.2013 23:59:59"), 30000), // C
            new Price("1", 1, 1, new Date("10.02.2013 00:00:00"), new Date("28.02.2013 23:59:59"), 99000)  // B
        ));

        assert prices_23.equals(control_prices_23);

        System.out.println("Scenario 2.3 is OK.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        //-------------------------------------------------------------------------------------------------------------
        /* Scenario 2.4
               _______________
              |___A___|___B___|
            _____________________
           |___________C_________|
            _____________________
           |___________C_________|
        */
        System.out.println("Scenario 2.4");

        TablePrice prices_24 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("10.01.2013 00:00:00"), new Date("30.01.2013 23:59:59"), 11000), // A
            new Price("1", 1, 1, new Date("31.01.2013 00:00:00"), new Date("15.02.2013 23:59:59"), 99000), // B
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("28.02.2013 23:59:59"), 30000)  // C
        ));

        prices_24.print();

        TablePrice control_prices_24 = TablePrice.create( Arrays.asList(
                new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("28.02.2013 23:59:59"), 30000)
        ));

        assert prices_24.equals(control_prices_24);

        System.out.println("Scenario 2.4 is OK.");
        System.out.println("-------------------------------------------------------------------------------------------------------------------");
        //-------------------------------------------------------------------------------------------------------------
    }

    static void test3() {
        // A, B, C = different values of prices
        /* Scenario 3.1
             ____________ ________
            |___A____|_B_|____c___|
                ____________
               |______D_____|
             ____________________
            |_A|______D_____|__C_|

        */
        System.out.println("Scenario 3.1:");

        TablePrice prices_31 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("20.01.2013 23:59:59"), 11000), // A
            new Price("1", 1, 1, new Date("21.01.2013 00:00:00"), new Date("01.02.2013 23:59:59"), 99000), // B
            new Price("1", 1, 1, new Date("02.02.2013 00:00:00"), new Date("30.02.2013 23:59:59"), 30000), // C
            new Price("1", 1, 1, new Date("10.01.2013 00:00:00"), new Date("20.02.2013 23:59:59"), 50000)  // D
        ));

        prices_31.print();

        TablePrice control_prices_31 = TablePrice.create( Arrays.asList(
            new Price("1", 1, 1, new Date("01.01.2013 00:00:00"), new Date("09.01.2013 23:59:59"), 11000),
            new Price("1", 1, 1, new Date("10.01.2013 00:00:00"), new Date("20.02.2013 23:59:59"), 50000),
            new Price("1", 1, 1, new Date("21.02.2013 00:00:00"), new Date("02.03.2013 23:59:59"), 30000)
        ));

        assert prices_31.equals(control_prices_31);

        System.out.println("Scenario 3.1 is OK.");
        //-------------------------------------------------------------------------------------------------------------
    }
}

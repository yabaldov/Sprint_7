package org.example;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierDataProvider {

    private static final String login = RandomStringUtils.random(16, true, true);
    private static final String password = RandomStringUtils.random(12, true, true);
    private static final String firstName = RandomStringUtils.random(16, true, false);

    public static Courier getDefaultCourier() {
        return new Courier("schemer", "password#1", "Ostap");
    }

    public static Courier getCourierWithRandomCredentials() {
        return new Courier(login, password, firstName);
    }

    public static Courier getCourierWithoutCredentials() {
        return new Courier(null, null, firstName);
    }

    public static Courier getCourierWithEmptyCredentials() {
        return  new Courier("", "", firstName);
    }

    public static Courier getCourierWithoutPassword() {
        return new Courier(login, null, firstName);
    }

    public static Courier getCourierWithEmptyPassword() {
        return new Courier(login, "", firstName);
    }

    public static Courier getCourierWithoutLogin() {
        return new Courier(null, password, firstName);
    }

    public static Courier getCourierWithEmptyLogin() {
        return new Courier("", password, firstName);
    }
}

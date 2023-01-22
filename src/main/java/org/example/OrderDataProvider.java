package org.example;

public class OrderDataProvider {

    private static final String COLOR_BLACK = "BLACK";
    private static final String COLOR_GREY = "GREY";

    public static Order getOrderOfBlackScooter() {
        String[] color = {COLOR_BLACK};
        return new Order(
                "Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                "4",
                "+7 800 355 35 35",
                5,
                "2023-01-22",
                "Saske, come back to Konoha",
                color
        );
    }

    public static Order getOrderOfGreyScooter() {
        String[] color = {COLOR_GREY};
        return new Order(
                "Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                "4",
                "+7 800 355 35 35",
                5,
                "2023-01-22",
                "Saske, come back to Konoha",
                color
        );
    }

    public static Order getOrderOfBothColorsScooter() {
        String[] color = {COLOR_BLACK, COLOR_GREY};
        return new Order(
                "Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                "4",
                "+7 800 355 35 35",
                5,
                "2023-01-22",
                "Saske, come back to Konoha",
                color
        );
    }

    public static Order getOrderOfColorlessScooter() {
        return new Order(
                "Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                "4",
                "+7 800 355 35 35",
                5,
                "2023-01-22",
                "Saske, come back to Konoha",
                null
        );
    }

    public static Order getOrderOfEmptyColorScooter() {
        String[] color = {};
        return new Order(
                "Naruto",
                "Uchiha",
                "Konoha, 142 apt.",
                "4",
                "+7 800 355 35 35",
                5,
                "2023-01-22",
                "Saske, come back to Konoha",
                color
        );
    }
}

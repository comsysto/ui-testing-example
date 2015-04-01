package com.comsysto.uitestingexample;

public class LoginPage {

    public enum LoginResponse {
        CORRECT,
        WRONG_PASSWORD,
        NO_SUCH_USER,
        SERVICE_NOT_AVAILABLE
    }

    public LoginResponse login(String user, String password) {

        System.out.println("Trying to log in");

        // Pretending to call the real website
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (user.equals("hans") && password.equals("secret")) {
            System.out.println("Login successful");
            return LoginResponse.CORRECT;

        } else if (user.equals("hans") && !password.equals("secret")) {
            System.out.println("Wrong Password");
            return LoginResponse.WRONG_PASSWORD;

        } else if (user.equals("franz")) {
            System.out.println("Login failed");
            return LoginResponse.SERVICE_NOT_AVAILABLE;

        } else {
            System.out.println("No such user");
            return LoginResponse.NO_SUCH_USER;
        }
    }
}

package com.comsysto.uitestingexample;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

   @Test
    public void login_successful() {

        Assert.assertSame(new LoginPage().login("hans", "secret"), LoginPage.LoginResponse.CORRECT);
    }

    @Test
    public void login_noSuchUser() {

        Assert.assertSame(new LoginPage().login("xxx", "secret"), LoginPage.LoginResponse.NO_SUCH_USER);
    }

    @Test
    public void login_wrongPassword() {

        Assert.assertSame(new LoginPage().login("hans", "not-secret"), LoginPage.LoginResponse.WRONG_PASSWORD);
    }

    @Test
    public void login_failed() {

        Assert.assertSame(new LoginPage().login("franz", "secret"), LoginPage.LoginResponse.SERVICE_NOT_AVAILABLE);
    }
}


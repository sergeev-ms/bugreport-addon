package com.borets.bugreport.web;

import com.borets.bugreport.web.exception.ExceptionHandlerExt;
import com.haulmont.cuba.web.DefaultApp;

import java.util.Locale;

public class AppExt extends DefaultApp {
    @Override
    protected void init(Locale requestLocale) {
        super.init(requestLocale);

        exceptionHandlers.setDefaultHandler(new ExceptionHandlerExt());
    }
}

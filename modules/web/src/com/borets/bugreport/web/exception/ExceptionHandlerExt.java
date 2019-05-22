package com.borets.bugreport.web.exception;

import com.haulmont.cuba.web.App;
import com.haulmont.cuba.web.AppUI;
import com.haulmont.cuba.web.exception.DefaultExceptionHandler;
import com.haulmont.cuba.web.exception.ExceptionDialog;
import com.vaadin.ui.Window;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExceptionHandlerExt extends DefaultExceptionHandler {

    @Override
    protected void showDialog(App app, Throwable exception) {
        //very similar as parent method but with one difference what we invoke our custom exception dialog
        Throwable rootCause = ExceptionUtils.getRootCause(exception);
        if (rootCause == null) {
            rootCause = exception;
        }
        ExceptionDialog dialog = new ExceptionDialogExt(rootCause);
        AppUI ui = AppUI.getCurrent();
        for (Window window : ui.getWindows()) {
            if (window.isModal()) {
                dialog.setModal(true);
                break;
            }
        }
        ui.addWindow(dialog);
        dialog.focus();
    }
}

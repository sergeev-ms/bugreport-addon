package com.borets.bugreport.web.exception;

import com.borets.bugreport.web.bugreportdialog.BugReportDialog;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.icons.CubaIcon;
import com.haulmont.cuba.gui.screen.OpenMode;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.web.AppUI;
import com.haulmont.cuba.web.exception.ExceptionDialog;
import com.haulmont.cuba.web.gui.icons.IconResolver;
import com.haulmont.cuba.web.widgets.CubaButton;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

import javax.annotation.Nullable;

public class ExceptionDialogExt extends ExceptionDialog {
    public ExceptionDialogExt(Throwable throwable) {
        this(throwable, null, null);
    }

    public ExceptionDialogExt(Throwable throwable, @Nullable String caption, @Nullable String message) {
        super(throwable, caption, message);

        setBugReportBtn();
    }

    private void setBugReportBtn() {
        HorizontalLayout buttons = null;
        if (mainLayout.getComponentCount() > 0) {
            Component component = mainLayout.getComponent(mainLayout.getComponentCount() - 1);
            if (component instanceof HorizontalLayout) {
                buttons = (HorizontalLayout) component;
            }
        }
        if (buttons != null) {
            Button reportButton = new CubaButton(messages.getMessage(getClass(), "extExceptionDialog.bugReport"));
            reportButton.setIcon(AppBeans.get(IconResolver.class).getIconResource(CubaIcon.BUG.source()));
            reportButton.addClickListener(e -> createBugReport(stackTraceTextArea.getValue()));
            buttons.addComponent(reportButton, 0);
        }
    }


    protected void createBugReport(String stackTrace) {
        final ScreenBuilders screenBuilders = AppBeans.get(ScreenBuilders.class);
        final Screen frameOwner = AppUI.getCurrent().getTopLevelWindowNN().getFrameOwner();

        final BugReportDialog dialog = screenBuilders.screen(frameOwner)
                .withScreenClass(BugReportDialog.class)
                .withOpenMode(OpenMode.DIALOG)
                .withAfterCloseListener(bugReportDialogAfterScreenCloseEvent -> {
                    if (bugReportDialogAfterScreenCloseEvent.getCloseAction().equals(Screen.WINDOW_COMMIT_AND_CLOSE_ACTION))
                        this.forceClose();
                })
                .build();
        dialog.setStackTrace(stackTrace);
        dialog.show();
    }
}

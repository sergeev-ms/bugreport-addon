package com.borets.bugreport.web.bugreportdialog;

import com.borets.bugreport.service.EmailSenderService;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.screen.*;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

@UiController("bugreport_BugReportDialog")
@UiDescriptor("bug-report-dialog.xml")
public class BugReportDialog extends Screen {
    @Inject
    private TextArea<String> stackTraceField;
    @Inject
    private TextArea<String> messageField;
    @Inject
    private Notifications notifications;
    @Inject
    private MessageBundle messageBundle;
    @Inject
    private EmailSenderService emailSenderService;

    private String stackTrace;

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    @Subscribe
    private void onBeforeShow(BeforeShowEvent event) {
        if (!StringUtils.isEmpty(stackTrace))
            stackTraceField.setValue(stackTrace);
        resetView();
    }
    private void resetView() {
        stackTraceField.setCursorPosition(0);
    }

    @Subscribe("okBtn")
    private void onOkBtnClick(Button.ClickEvent event) {
        final String subject = messageBundle.formatMessage("bugReportDialog.stacktrace.format", messageField.getValue(), stackTrace);
        emailSenderService.send(subject);
        close(WINDOW_COMMIT_AND_CLOSE_ACTION);
        notifications.create(Notifications.NotificationType.TRAY).withCaption(messageBundle.getMessage("bugReportDialog.notification")).show();
    }

    @Subscribe("cancelBtn")
    private void onCancelBtnClick(Button.ClickEvent event) {
        close(WINDOW_DISCARD_AND_CLOSE_ACTION);
    }

}
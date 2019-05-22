package com.borets.bugreport.service;

import com.borets.bugreport.configuration.BugReportConfig;
import com.google.common.base.Strings;
import com.haulmont.cuba.core.app.EmailerAPI;
import com.haulmont.cuba.core.global.EmailInfo;
import com.haulmont.cuba.core.global.UserSessionSource;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service(EmailSenderService.NAME)
public class EmailSenderServiceBean implements EmailSenderService {

    @Inject
    private EmailerAPI emailerAPI;
    @Inject
    private BugReportConfig bugReportConfig;
    @Inject
    private UserSessionSource userSessionSource;

    @Override
    public void send(String body) {
        final String email = userSessionSource.getUserSession().getUser().getEmail();
        String from = Strings.isNullOrEmpty(email) ? bugReportConfig.getEmailAddressFrom() : email;
        final EmailInfo emailInfo = new EmailInfo(bugReportConfig.getEmailAddressTo(), bugReportConfig.getEmailSubject(), from, body, null);

        emailerAPI.sendEmailAsync(emailInfo);
    }
}
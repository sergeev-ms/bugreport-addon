package com.borets.bugreport.configuration;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;

@Source(type = SourceType.DATABASE)
public interface BugReportConfig extends Config {

    @Property("bugreport.email.addressTo")
    String getEmailAddressTo();

    @Default("Bug Report")
    @Property("bugreport.email.subject")
    String getEmailSubject();

    @Property("bugreport.email.addressFrom")
    String getEmailAddressFrom();
}

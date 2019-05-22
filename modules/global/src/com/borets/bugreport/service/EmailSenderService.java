package com.borets.bugreport.service;

public interface EmailSenderService {
    String NAME = "bugreport_EmailSenderService";

    void send(String content);
}
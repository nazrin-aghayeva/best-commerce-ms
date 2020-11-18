package org.mail.ms.service;

import org.mail.ms.entities.MailDto;


public interface MailService {
    void processMailRequest(MailDto mailDto);
}

package org.mail.ms.listener;

import lombok.AllArgsConstructor;
import org.mail.ms.entities.MailDto;
import org.mail.ms.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@AllArgsConstructor
@EnableBinding(Sink.class)
public class MailListener {
    private final static Logger logger= LoggerFactory.getLogger(MailListener.class);
    private final MailService mailService;

    @StreamListener(Sink.INPUT)
    public void onNewMailRequest(MailDto mailDto){
        logger.debug("onNewMailRequest mail from {} queue start", mailDto.getTo());
        mailService.processMailRequest(mailDto);
        logger.debug("onNewMailRequest mail from {} queue end", mailDto.getTo());

    }


}

package com.imalive.api.Service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.*;
import com.imalive.api.DataTypes.MailType;
import com.imalive.api.Model.Base;
import org.springframework.stereotype.Component;

@Component
public class MailService {
    static final String FROM = "noreply@mitraecp.com";
    static final String TO = "erick.santana@mitraecp.com";
    static final String SUBJECT = "Alerta de alteração no estado de uma base";
    static final String TEXTBODYIFDOWN = " caiu!";
    static final String TEXTBODYIFUP = " está ativa!";

    public static void sendMail(Base base, MailType mailType) {
        String TEXTBODY = "";

        if (mailType.equals(MailType.UP)) {
            TEXTBODY = "A base " + base.getBucketName() + TEXTBODYIFUP;
        }
        if (mailType.equals(MailType.DOWN)) {
            TEXTBODY = "A base " + base.getBucketName() + TEXTBODYIFDOWN;
        }

        try {
            AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.SA_EAST_1).build();
            SendEmailRequest request = new SendEmailRequest().withDestination(new Destination().withToAddresses(TO))
                    .withMessage(new Message().withBody(new Body().withText(new Content().withCharset("UTF-8").withData(TEXTBODY)))
                            .withSubject(new Content().withCharset("UTF-8").withData(SUBJECT))).withSource(FROM);
            client.sendEmail(request);
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: " + ex.getMessage());
        }
    }
}

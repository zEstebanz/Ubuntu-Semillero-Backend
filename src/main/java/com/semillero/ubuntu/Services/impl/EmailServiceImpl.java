package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.Utils.UsuarioProjection;
import com.semillero.ubuntu.Repositories.MicroemprendimientoRepository;
import com.semillero.ubuntu.Repositories.PublicacionRepository;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import com.semillero.ubuntu.Services.EmailService;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.URLDataSource;
import jakarta.mail.BodyPart;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {

    public static final String NEW_USER_ACCOUNT_VERIFICATION = "The Ubuntu Team";
    public static final String EMAIL_TEMPLATE = "emailtemplate";
    public static final String TEXT_HTML = "text/html";

    @Value("${spring.mail.verify.host}")
    private String host;
    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final UsuarioRepository usuarioRepository;
    private final MicroemprendimientoRepository microRepository;
    private final PublicacionRepository publicacionRepository;

    @Scheduled(cron = "0 0 1 * * MON")
    public void sendEmailToUsers() {

        List<UsuarioProjection> users = usuarioRepository.findNombreAndEmailByIsDeletedFalse();

        LocalDate newPublications = LocalDate.now().minusDays(7);
        LocalDate todayPublications = LocalDate.now();

        long newPublis = publicacionRepository.countByFechaCreacionBetween(newPublications, todayPublications);

        LocalDate newMicro = LocalDate.now().minusDays(7);
        LocalDate todayMicros = LocalDate.now();

        long newMicros = microRepository.countByFechaCreacionBetween(newMicro, todayMicros);

        users.forEach(user -> sendEmail(
                user.getNombre(),
                user.getEmail(),
                newMicros,
                newPublis));
    }

    @Override
    @Async("asyncTaskExecutor")
    public void sendEmail(String user_name, String user_email, long newMicrosQuantity, long newPublicationsQuantity) {

        try {

            String img = "https://res.cloudinary.com/dvoxzrkzs/image/upload/v1712243784/ylskm5imguiqdn302v9y.png";
            MimeMessage message = getMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF_8_ENCODING");
            helper.setPriority(1);
            helper.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            helper.setFrom(fromEmail);
            helper.setTo(user_email);
//            helper.setText(text, true);
            Context context = new Context();
            context.setVariable("name", user_name);
            context.setVariable("micro", newMicrosQuantity);
            context.setVariable("publication", newPublicationsQuantity);

            String text = templateEngine.process(EMAIL_TEMPLATE, context);

            //HTML EMAIL BODY
            MimeMultipart mimeMultipart = new MimeMultipart("related");
            BodyPart messageBody = new MimeBodyPart();
            messageBody.setContent(text, TEXT_HTML);
            mimeMultipart.addBodyPart(messageBody);

            // Add Ubuntu IMG
            BodyPart imageBodyPart = new MimeBodyPart();
            DataSource dataSource = new URLDataSource(new URL(img));
            imageBodyPart.setDataHandler(new DataHandler(dataSource));
            imageBodyPart.setHeader("Content-ID", "image");
            mimeMultipart.addBodyPart(imageBodyPart);

            message.setContent(mimeMultipart);

            javaMailSender.send(message);

        } catch (Exception ex){
            log.info("ERR" + ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    private MimeMessage getMimeMessage() {

        return javaMailSender.createMimeMessage();
    }
}

package br.ufu.sisegresso.service

import br.ufu.sisegresso.dtos.EmailDTO
import br.ufu.sisegresso.exception.InternalEmailException
import br.ufu.sisegresso.exception.ServiceInternalError
import br.ufu.sisegresso.messages.AppMessages
import jakarta.mail.AuthenticationFailedException
import jakarta.mail.Message
import jakarta.mail.MessagingException
import jakarta.mail.internet.InternetAddress
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.MailSendException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMailMessage
import org.springframework.stereotype.Service

interface IEmailService {
    fun sendEmail(emailDetails: EmailDTO)
}

@Service
class EmailService(
    @Autowired private val mailSender: JavaMailSender,
    @Value("\${spring.mail.username}") private val senderEmail: String,
): IEmailService {

    override fun sendEmail(emailDetails: EmailDTO) {
        try {
            val emailMessage = mailSender.createMimeMessage().apply {
                setFrom(InternetAddress(senderEmail))
                setRecipients(Message.RecipientType.TO, emailDetails.recipient)
                subject = emailDetails.subject
                setContent(emailDetails.messageBody, "text/html; charset=utf-8")
            }

            mailSender.send(emailMessage)
        } catch (exception: MessagingException) {
            throw InternalEmailException(
                AppMessages.EMAIL_ERROR.name,
                AppMessages.EMAIL_ERROR.message
            )
        } catch (exception: AuthenticationFailedException) {
            throw InternalEmailException(
                AppMessages.EMAIL_AUTHENTICATION_ERROR.name,
                AppMessages.EMAIL_AUTHENTICATION_ERROR.message
            )
        } catch (exception: MailSendException) {
            throw InternalEmailException(
                AppMessages.EMAIL_SEND_ERROR.name,
                AppMessages.EMAIL_SEND_ERROR.message
            )
        } catch (exception: Exception) {
            throw ServiceInternalError(AppMessages.UNKNOWN_ERROR.name)
        }


    }
}
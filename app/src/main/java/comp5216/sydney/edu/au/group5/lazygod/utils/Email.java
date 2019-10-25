package comp5216.sydney.edu.au.group5.lazygod.utils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


import java.util.Date;
import java.util.Properties;

//发送邮件
public class Email {

    //配置信息
    private static final String MAIL_TRANSPORT_PROTOCOL ="mail.transport.protocol";//邮件的传输协议
    private static final String MAIL_TRANSPORT_PROTOCOL_VALUE ="smtp";//使用smtp协议
    private static final String MAIL_HOST ="mail.host";//发送邮件的主机
    private static final String MAIL_HOST_VALUE ="smtp.gmail.com"; //发送邮件的服务器地址
    private static final String MAIL_SMTP_AUTH ="mail.smtp.auth";//邮件smtp作者确认
    private static final String CONFIRM ="true";//确认
    private static final String SSL = "mail.smtp.ssl.enable";               // SSL
    private static final String SEND_ENCODING_LAYOUT ="text/html;charset=utf-8";//发送邮件的编码格式

    //邮件编辑信息(仅需写上自己的)
    private static final String MAIL_FROM ="lazygod.official@gmail.com";//邮件发送人
    private static final String MAIL_FROM_PASSWORD ="comp5216";//邮件发送人授权码
    private static final String MAIL_ORGANIZATION_LOGO ="/res/drawable/logo.png";//网站logo

    // request code
    private static final int verification = 11;
    private static final int passwords = 12;

    public static void main(String[] args) throws Exception{
        //sendEmail("freetimeliyun@gmail.com", "Test", "kakakka", verification);
    }

    /**
     * 发送邮件
     * @param to 接收人  (邮箱地址)
     * @param subject 主题
     * @param content 内容
     * @param type request type
     */
    public static void sendEmail(String to, String subject, String content, int type) {

        Properties props = new Properties();                                               //key value:配置参数。真正发送邮件时再配置
        props.setProperty(MAIL_TRANSPORT_PROTOCOL, MAIL_TRANSPORT_PROTOCOL_VALUE);         //指定邮件发送的协议，参数是规范规定的
        props.setProperty(MAIL_HOST, MAIL_HOST_VALUE);                                     //指定发件服务器的地址，参数是规范规定的
        props.setProperty(MAIL_SMTP_AUTH, CONFIRM);                                        //请求服务器进行身份认证。参数与具体的JavaMail实现有关
        props.setProperty(SSL, CONFIRM);
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MAIL_FROM, MAIL_FROM_PASSWORD);
                    }
                });

        String textContent = "";
        if (type == verification) {
           textContent = "<font size='10px'><br/>LazyGod<br/>Thanks for using LazyGod<br/></font>" +
                    "<font size='10px'>Your Verification code : </font>"+
                    "<font size='10px' color='red'>" + content + "</font>";
        }
        if (type == passwords) {
            textContent = "<font size='10px'><br/>LazyGod<br/>Thanks for using LazyGod<br/></font>" +
                    "<font size='10px'>Your Password : </font>"+
                    "<font size='10px' color='red'>" + content + "</font>";
        }
        Message message = new MimeMessage(session);
        //设置邮件的头
        try {
            message.setFrom(new InternetAddress(MAIL_FROM)); //谁发送的

            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//发送给谁
            message.setSubject(subject);
            //设置正文
            // 创建图片文本节点
            MimeBodyPart imagePart = new MimeBodyPart();
            DataHandler dataHandler = new DataHandler(new FileDataSource(MAIL_ORGANIZATION_LOGO));
            imagePart.setDataHandler(dataHandler);
            imagePart.setContentID("<logo>");
            imagePart.setHeader("Content-Type", "image/png");
            // text part
            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setContent(textContent, SEND_ENCODING_LAYOUT);

            //组装文本、图片节点
            MimeMultipart imageText = new MimeMultipart("related");
            imageText.addBodyPart(textPart);


            message.setContent(imageText);
            message.setSentDate(new Date());
            message.saveChanges();

            //发送邮件
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
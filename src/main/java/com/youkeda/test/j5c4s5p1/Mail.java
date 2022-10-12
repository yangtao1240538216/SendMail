package com.youkeda.test.j5c4s5p1;


import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

  private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
  private static Properties props = null;

  // TODO:发件人信箱
  private static final String FROM_MAIL = "@qq.com";
  // TODO:发件人信箱安全key
  private static final String FROM_MAIL_KEY = "";
  // TODO:收件人信箱
  private static final String TO_MAIL = "@qq.com";

  /**
   * 在构造函数中初始化相关的参数。这是固定用法。
   */
  public Mail() {
    // 设置SSL连接、邮件环境
    props = System.getProperties();
    props.setProperty("mail.smtp.host", "smtp.qq.com");
    props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
    props.setProperty("mail.smtp.socketFactory.fallback", "false");
    props.setProperty("mail.smtp.port", "465");
    props.setProperty("mail.smtp.socketFactory.port", "465");
    props.setProperty("mail.smtp.auth", "true");
  }

  /**
   * 发送邮件
   * @param subject
   * @param content
   */
  public void sendMail(String subject, String content) {
    try {
      // 建立邮件会话
      Session session = Session.getDefaultInstance(props, new Authenticator() {
        // 身份认证
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
          // 账户 授权码
          return new PasswordAuthentication(FROM_MAIL, FROM_MAIL_KEY);
        }
      });

      // 建立邮件对象
      MimeMessage message = new MimeMessage(session);
      // 设置邮件的发件人、收件人、主题
      // 附带发件人名字
      message.setFrom(new InternetAddress(FROM_MAIL));
      message.setRecipients(Message.RecipientType.TO, TO_MAIL);
      message.setSubject(subject);
      // 文本部分
      message.setContent(content, "text/html;charset=UTF-8");
      message.saveChanges();
      // 发送邮件
      Transport.send(message);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

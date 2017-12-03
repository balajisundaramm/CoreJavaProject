package com.noteManager;

/**
 * Created by Balaji on 1/12/17.
 */
public class EmailBean {
    private String fromEmail,password,toEmail,subject,content;

    public EmailBean(String fromEmail, String password, String toEmail, String subject, String content) {
        this.fromEmail = fromEmail;
        this.password = password;
        this.toEmail = toEmail;
        this.subject = subject;
        this.content = content;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public void setFromEmail(String fromEmail) {
        this.fromEmail = fromEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailBean emailBean = (EmailBean) o;

        if (!fromEmail.equals(emailBean.fromEmail)) return false;
        if (!password.equals(emailBean.password)) return false;
        if (!toEmail.equals(emailBean.toEmail)) return false;
        if (!subject.equals(emailBean.subject)) return false;
        return content.equals(emailBean.content);

    }

    @Override
    public int hashCode() {
        int result = fromEmail.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + toEmail.hashCode();
        result = 31 * result + subject.hashCode();
        result = 31 * result + content.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "EmailBean{" +
                "fromEmail='" + fromEmail + '\'' +
                ", password='" + password + '\'' +
                ", toEmail='" + toEmail + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

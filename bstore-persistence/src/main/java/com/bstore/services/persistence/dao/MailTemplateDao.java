package com.bstore.services.persistence.dao;

import com.bstore.services.persistence.hbm.MailTemplate;

/**
 *
 * @author User
 */
public interface MailTemplateDao {
    MailTemplate getMail(int idMail);
}

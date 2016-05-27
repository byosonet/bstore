package com.bstore.services.service;

import com.bstore.services.persistence.hbm.MailTemplate;

/**
 *
 * @author User
 */
public interface MailTemplateService {
    MailTemplate getEmail (int idMail);
}

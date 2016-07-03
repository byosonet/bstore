package com.bstore.services.persistence.dao;

import com.bstore.services.persistence.pojo.Properties;

public interface PropertyDao {
	Properties getValueByKey(String key);
}

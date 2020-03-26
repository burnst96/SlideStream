package com.slidestream.dao;

import com.slidestream.domain.Configuration;

public interface ConfigurationDao extends GenericDao<Configuration, Long> {

    Configuration findByGroupPk(long group_pk);

}

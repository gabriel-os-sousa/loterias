package com.gabriel_os_sousa.loterias.configuration;

import org.apache.logging.log4j.util.Strings;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableReactiveMongoRepositories
public class MongoReactiveApplication extends AbstractReactiveMongoConfiguration {

  @Override
  protected String getDatabaseName() {
    return Strings.EMPTY;
  }
}
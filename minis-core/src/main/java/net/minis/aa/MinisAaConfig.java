package net.minis.aa;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = { "net.minis.aa.domain" })
@EnableJpaRepositories(basePackages = "net.minis.aa.repository")
public class MinisAaConfig {

}

package com.nusiss.inventory.backend.configuration;

import com.nusiss.inventory.backend.utils.AuditorAwareProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfiguration {
  @Bean
  public AuditorAware<String> auditorAwareProvider() {
    return new AuditorAwareProvider();
  }
}

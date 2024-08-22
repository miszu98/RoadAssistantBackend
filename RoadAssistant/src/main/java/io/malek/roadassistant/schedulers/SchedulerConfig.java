package io.malek.roadassistant.schedulers;

import io.malek.roadassistant.api.RoadIncidentFacade;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@EnableSchedulerLock(defaultLockAtMostFor = "PT60S")
class SchedulerConfig {
    private final RoadIncidentFacade roadIncidentFacade;

    @Bean
    LockProvider lockProvider(DataSource dataSource) {
        return new JdbcTemplateLockProvider(dataSource);
    }

    @Bean
    RoadIncidentRefreshScheduler refreshScheduler() {
        return new RoadIncidentRefreshScheduler(roadIncidentFacade);
    }

}

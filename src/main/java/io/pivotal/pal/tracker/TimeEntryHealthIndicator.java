package io.pivotal.pal.tracker;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class TimeEntryHealthIndicator implements HealthIndicator {
     int maximumEntries = 5;
     TimeEntryRepository timeEntryRepo;


    public TimeEntryHealthIndicator(TimeEntryRepository timeEntryRepo) {
        this.timeEntryRepo = timeEntryRepo;
    }

    @Override

    public Health health() {
        Health.Builder builder = new Health.Builder();

        if (this.timeEntryRepo.list().size() <5){
           System.out.println(this.timeEntryRepo.list().size());
            builder.up();
        }
        else builder.down();

        return builder.build();
    }
}

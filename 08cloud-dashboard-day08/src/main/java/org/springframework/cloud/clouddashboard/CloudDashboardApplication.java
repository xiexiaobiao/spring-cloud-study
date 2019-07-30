package org.springframework.cloud.clouddashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class CloudDashboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudDashboardApplication.class, args);
    }

}

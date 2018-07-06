package com.flyingupword.membership.manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by killer9527 on 2018/5/13.
 */
@SpringBootApplication
public class MembershipManageApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MembershipManageApplication.class);
        application.run(args);
    }
}

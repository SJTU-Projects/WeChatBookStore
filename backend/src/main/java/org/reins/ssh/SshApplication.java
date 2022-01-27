package org.reins.ssh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"org.reins.ssh.repository"})
public class SshApplication {

    public static void main(String[] args) {

        SpringApplication.run(SshApplication.class, args);

    }
}

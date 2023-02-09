package com.dxvalley.epassbook;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.dxvalley.epassbook.models.Address;
import com.dxvalley.epassbook.models.Role;
import com.dxvalley.epassbook.models.Users;
import com.dxvalley.epassbook.repositories.AddressRepository;
import com.dxvalley.epassbook.repositories.RoleRepository;
import com.dxvalley.epassbook.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "database", name = "seed", havingValue = "true")
public class Bootstrap {

    Role userRole = new Role("user", "End System User");
    Role admin = new Role("admin", "System Administrator");
    Role sysAdmin = new Role("sysAdmin", "Highest Level System Administrator");
    Address address = new Address("+251924385314", "elshadayt@coopbankoromia.com.et", "Addis Ababa", "Lideta", "01",
            "312");
    Users user = new Users("0911111111", "elshu13", "Elshaday Tamire", "elshadayt@coopbankoromia.com.et", true, "MALE",
            "07-09-1999", "/image.png", "198.1.13.2", "01-09-2022", null, 1, 0, false, true);

    Collection<Role> roles = new ArrayList<>();

    void setUp() {
        roles.add(userRole);
        roles.add(admin);
        roles.add(sysAdmin);
        user.setRoles(roles);
        user.setAddress(address);
    }

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, RoleRepository roleRepository,
            AddressRepository addressRepo) {
        setUp();
        return args -> {
            log.info("Preloading " + roleRepository.save(admin));
            log.info("Preloading " + roleRepository.save(sysAdmin));
            log.info("Preloading " + userRepository.save(user));
        };
    }
}
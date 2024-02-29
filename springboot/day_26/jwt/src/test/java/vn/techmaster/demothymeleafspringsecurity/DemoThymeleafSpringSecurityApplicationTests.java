package vn.techmaster.demothymeleafspringsecurity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import vn.techmaster.demothymeleafspringsecurity.entity.Role;
import vn.techmaster.demothymeleafspringsecurity.entity.User;
import vn.techmaster.demothymeleafspringsecurity.repository.RoleRepository;
import vn.techmaster.demothymeleafspringsecurity.repository.UserRepository;

import java.util.List;

@SpringBootTest
class DemoThymeleafSpringSecurityApplicationTests {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void save_roles() {
        // Lưu 2 roles vào database có name = USER + ADMIN
        Role roleAdmin = new Role();
        roleAdmin.setName("ADMIN");
        roleRepository.save(roleAdmin);

        Role roleUser = new Role();
        roleUser.setName("USER");
        roleRepository.save(roleUser);
    }

    @Test
    void save_users() {
        // get role by name
        Role roleAdmin = roleRepository.findByName("ADMIN").get();
        Role roleUser = roleRepository.findByName("USER").get();

        // create user
        User userAdmin = new User();
        userAdmin.setName("Bùi Hiên");
        userAdmin.setEmail("hien@gmail.com");
        userAdmin.setPassword(passwordEncoder.encode("123"));
        userAdmin.setRoles(List.of(roleAdmin, roleUser));

        User userUser = new User();
        userUser.setName("Minh Duy");
        userUser.setEmail("duy@gmail.com");
        userUser.setPassword(passwordEncoder.encode("123"));
        userUser.setRoles(List.of(roleUser));

        // save user
        userRepository.save(userAdmin);
        userRepository.save(userUser);
    }
}

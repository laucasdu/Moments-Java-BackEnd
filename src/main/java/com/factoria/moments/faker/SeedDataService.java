package com.factoria.moments.faker;

import com.factoria.moments.models.Role;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.IMomentRepository;
import com.factoria.moments.repositories.RoleRepository;
import com.factoria.moments.repositories.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.management.relation.Relation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SeedDataService { //quan s'engega l'aplicació s'injecta el seed

    private IMomentRepository momentRepository;
    private IUserRepository userRepository;
    private PasswordEncoder encoder;

    private RoleRepository roleRepository;

    public SeedDataService(IMomentRepository momentRepository, IUserRepository userRepository, PasswordEncoder encoder, RoleRepository roleRepository) {
        this.momentRepository = momentRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @PostConstruct // quan construeix aquest bean (o component) Seed, s'executarà la funció createDate, just quan s'executarà l'app
    public void createUser() {
        Set<Role> userRoles = Set.of(roleRepository.findByName(Role.RoleName.ROLE_USER).get());

        var vera = new User();
        vera.setRoles(roleRepository.findAll().stream().collect(Collectors.toSet()));
        vera.setUsername("VERA");
        vera.setEmail("vera@gmail.com");
        vera.setUserImg("https://thumbs.dreamstime.com/z/smiling-girl-avatar-cute-woman-flat-icon-white-background-person-s-character-vector-illustration-eps-191715188.jpg");
        vera.setPassword(encoder.encode("12345678"));

        var gala = new User();
        gala.setRoles(roleRepository.findAll().stream().collect(Collectors.toSet()));
        gala.setUsername("GALA");
        gala.setEmail("gala@gmail.com");
        gala.setUserImg("https://thumbs.dreamstime.com/z/smiling-girl-avatar-cute-woman-flat-icon-white-background-person-s-character-vector-illustration-eps-191715956.jpg");
        gala.setPassword(encoder.encode("12345678"));

        userRepository.saveAll(List.of(vera, gala));

    }

//    @PostConstruct // quan construeix aquest bean (o component) Seed, s'executarà la funció createDate, just quan s'executarà l'app
//    public void createData() {
//
//        if (!momentRepository.findAll().isEmpty()) return;
//
//        var user = userRepository.findById(1L).get();
//        var moment = new Moment();
//        moment.setCreator(user);
//        moment.setTitle("te");
//        moment.setDescription("testDescription");
//        moment.setImgUrl("https://thumbs.dreamstime.com/b/cute-cartoon-character-girl-avatar-white-background-flat-vector-illustration-eps-cute-cartoon-character-girl-avatar-white-181634274.jpg");
//        momentRepository.save(moment);
//
//
//    }

}

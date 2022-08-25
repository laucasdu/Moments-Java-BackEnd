package com.factoria.moments.faker;

import com.factoria.moments.models.Moment;
import com.factoria.moments.models.Role;
import com.factoria.moments.models.User;
import com.factoria.moments.repositories.IMomentRepository;
import com.factoria.moments.repositories.IRoleRepository;
import com.factoria.moments.repositories.IUserRepository;
import com.factoria.moments.services.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Set;

@Component
public class SeedDataService { //quan s'engega l'aplicació s'injecta el seed

    private IMomentRepository momentRepository;
    private IUserRepository userRepository;
    private PasswordEncoder encoder;

    private IRoleRepository roleRepository;

    public SeedDataService(IMomentRepository momentRepository, IUserRepository userRepository, PasswordEncoder encoder, IRoleRepository roleRepository) {
        this.momentRepository = momentRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.roleRepository = roleRepository;
    }

    @PostConstruct // quan construeix aquest bean (o component) Seed, s'executarà la funció createDate, just quan s'executarà l'app
    public void createUser() {
        Set<Role> userRoles = Set.of(roleRepository.findByName(Role.RoleName.ROLE_USER).get());

//        if (!momentRepository.findAll().isEmpty()) return;

//        var user = userRepository.findById(1L).get();
//        var moment = new Moment();
//        moment.setCreator(user);
//        moment.setTitle("te");
//        moment.setDescription("testDescription");
//        moment.setImgUrl("https://thumbs.dreamstime.com/b/cute-cartoon-character-girl-avatar-white-background-flat-vector-illustration-eps-cute-cartoon-character-girl-avatar-white-181634274.jpg");
//        momentRepository.save(moment);

        var vera = new User();
        vera.setUsername("vera");
        vera.setEmail("vera@gmail.com");
        vera.setUserImg("https://thumbs.dreamstime.com/b/cute-cartoon-character-girl-avatar-white-background-flat-vector-illustration-eps-cute-cartoon-character-girl-avatar-white-181634274.jpg");
        vera.setPassword(encoder.encode("12345678"));

        var gala = new User();
        gala.setUsername("gala");
        gala.setEmail("gala@gmail.com");
        gala.setUserImg("https://thumbs.dreamstime.com/b/cute-cartoon-character-girl-avatar-white-background-flat-vector-illustration-eps-cute-cartoon-character-girl-avatar-white-181634274.jpg");
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
//
//        var gala = userRepository.findById(1L).get();
//        var moment1 = new Moment();
//        moment1.setCreator(gala);
//        moment1.setTitle("te");
//        moment1.setDescription("testDescription");
//        moment1.setImgUrl("https://thumbs.dreamstime.com/b/cute-cartoon-character-girl-avatar-white-background-flat-vector-illustration-eps-cute-cartoon-character-girl-avatar-white-181634274.jpg");
//        momentRepository.save(moment1);
//
//
//
//    }

}

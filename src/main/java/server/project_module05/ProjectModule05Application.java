package server.project_module05;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import server.project_module05.model.entity.Role;
import server.project_module05.model.entity.RoleName;
import server.project_module05.model.entity.User;
import server.project_module05.repository.IUserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

@SpringBootApplication
public class ProjectModule05Application {

    public static void main(String[] args) {
        SpringApplication.run(ProjectModule05Application.class, args);
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    @Bean
//    CommandLineRunner commandLineRunner(IUserRepository userRepository){
//        return args -> {
//            Role admin = new Role(null, RoleName.ADMIN);
//            Role user = new Role(null,RoleName.USER);
//
//            User u1 = new User(null,"dhoanghai", passwordEncoder().encode("123456"),"hoanghai150103@gmail.com","Dao Hoang Hai",true,"0358532562","LongBien,HaNoi","https://i.pinimg.com/736x/75/1b/9a/751b9a98a29c605f70ef79db4d8d830d.jpg",new Date(),new Date(),new ArrayList<>(),new HashSet<>(),new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
//            u1.getRoles().add(admin);
//            u1.getRoles().add(user);
//
//            userRepository.save(u1);
//        };
//    }
}

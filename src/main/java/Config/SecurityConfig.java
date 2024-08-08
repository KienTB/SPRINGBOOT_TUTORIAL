package Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) { //lấy thông tin user từ DB
//        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
//        users.setUsersByUsernameQuery("select username, password, enabled from users where username = ?");
//        users.setAuthoritiesByUsernameQuery("select username, authority from authorities where username = ?");
//        return users;
//    }
//    @Bean
//    public CommandLineRunner dataLoader(UserDetailsManager userDetailsManager, PasswordEncoder encoder) {
//        return args -> {
//                userDetailsManager.createUser(User.withUsername("kienuser3")
//                        .password(encoder.encode("password"))
//                        .roles("USER")
//                        .build());
//                userDetailsManager.createUser(User.withUsername("kienadmin3")
//                        .password(encoder.encode("password"))
//                        .roles("USER", "ADMIN")
//                        .build());
//        };
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/v1/register").permitAll() //để có thể truy cập vào api này đăng ký
                        .requestMatchers("/v1/greeting").authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}
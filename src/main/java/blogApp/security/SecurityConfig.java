package blogApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


//     This is a mess.
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint(entryPoint)
//                .and()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/example/*").permitAll()
//                .antMatchers(HttpMethod.GET, "/blog/list").permitAll()
//                .antMatchers("/user/login").permitAll()
//                .antMatchers("/user/create").permitAll()
//                .antMatchers("/blog/*").hasRole("USER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .successHandler(successHandler)
//                .failureHandler(myFailureHandler)
//                .and()
//                .httpBasic()
//                .and()
//                .logout().deleteCookies("JSESSIONID");
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers(HttpMethod.GET, "/blog/list").permitAll()
//                .antMatchers(HttpMethod.POST, "/user/create").permitAll()
//                .antMatchers("/**").denyAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .successForwardUrl("/example/hello")
//                .and()
//                .httpBasic()
//                .and()
//                .logout()
//                .logoutSuccessUrl("/");
//
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//
//                .formLogin()
//                .successForwardUrl("/example/hello")
//
//                .and()
//                .logout().deleteCookies("JSESSIONID");
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()

                .and()
                .logout().logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }
}

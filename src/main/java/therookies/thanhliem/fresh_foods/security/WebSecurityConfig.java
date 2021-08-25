package therookies.thanhliem.fresh_foods.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import therookies.thanhliem.fresh_foods.security.jwt.JwtAuthEntryPoint;
import therookies.thanhliem.fresh_foods.security.jwt.JwtAuthTokenFilter;
import therookies.thanhliem.fresh_foods.security.jwt.JwtUtils;
import therookies.thanhliem.fresh_foods.security.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        // securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    final private JwtAuthEntryPoint unauthorizedHandler;

    private final JwtUtils jwtUtils;

    public WebSecurityConfig (UserDetailsServiceImpl userDetailsService, JwtAuthEntryPoint unauthorizedHandler, JwtUtils jwtUtils) {
        this.userDetailsService = userDetailsService;
        this.unauthorizedHandler = unauthorizedHandler;
        this.jwtUtils = jwtUtils;
    }

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter(jwtUtils, userDetailsService);
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        // TODO
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                //product
                .antMatchers(HttpMethod.GET,"/api/product/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/productlist/**").permitAll()
                .antMatchers(HttpMethod.GET,"/api/top10discount").permitAll()
                .antMatchers(HttpMethod.GET,"/api/admin/product**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/api/admin/productlist/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/product").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/product").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/product").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/api/uploadFile").hasRole("ADMIN")
                //category
                .antMatchers(HttpMethod.GET,"/api/category/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/category").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/category").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/category").hasRole("ADMIN")
                //payment
                .antMatchers(HttpMethod.GET,"/api/payment/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/payment").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/api/payment").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api/payment").hasRole("ADMIN")
                //order
                .antMatchers(HttpMethod.GET,"/api/order/**").hasAnyRole("USER","ADMIN")
                .antMatchers(HttpMethod.POST,"/api/order/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/api/order").hasAnyRole("USER","ADMIN")
                //customer
                .antMatchers(HttpMethod.GET,"/api/customer/**").hasRole("USER")
                .antMatchers(HttpMethod.PUT,"/api/customer").hasRole("USER")
                //delete
                .antMatchers(HttpMethod.DELETE,"/api/image/").hasRole("ADMIN")
                .antMatchers("/api/shopcart").hasRole("USER")
                
                
                
                
                .antMatchers("api/customer/**").hasRole("USER")
                .antMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated();


        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}


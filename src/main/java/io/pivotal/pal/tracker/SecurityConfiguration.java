package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    Boolean httpsDisabled ;

public SecurityConfiguration( @Value("${https.disabled}") Boolean httpDisabled){
    this.httpsDisabled =httpDisabled;
}

    @Override
    public void configure(HttpSecurity https) throws Exception {
        if (!httpsDisabled) {
            https.requiresChannel().anyRequest().requiresSecure();
        }

        https.authorizeRequests().antMatchers("/**").hasRole("USER").and().httpBasic().and().csrf().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");

    }

}

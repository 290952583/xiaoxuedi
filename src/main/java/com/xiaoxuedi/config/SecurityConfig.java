package com.xiaoxuedi.config;

import com.xiaoxuedi.controller.api.AbstractController;
import com.xiaoxuedi.entity.UsersEntity;
import com.xiaoxuedi.service.AccountService;
import com.xiaoxuedi.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.query.spi.EvaluationContextExtension;
import org.springframework.data.repository.query.spi.EvaluationContextExtensionSupport;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new UserDetailsService()
		{
			@Autowired
			private AccountService accountService;
			@Autowired
			private SmsService smsService;
			@Autowired
			private HttpServletRequest request;

			/*@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
			{
				UsersEntity user = accountService.findUserByMobile(username);
				if (request.getParameter("password") != null)
				{
					if (!smsService.verificationSmsSession(username, request.getParameter("password")))
					{
						throw new UsernameNotFoundException("UsernameNotFoundException");
					}
					if (user != null)
					{
						user.setPassword(request.getParameter("password"));
					}
				}
				if (user == null)
				{
					throw new UsernameNotFoundException("UsernameNotFoundException");
				}
				String autoLoginPassword = AbstractController.getAutoLoginPassword();
				if (autoLoginPassword != null)
				{
					user.setPassword("autoLogin");
				}
				return user;
			}*/

			/**
			 * 重写登陆方法，为了减少成本改用通过账号密码进行登陆；
			 * @param username
			 * @return
			 * @throws UsernameNotFoundException
			 */
			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
			{
				UsersEntity user = accountService.findUserByMobile(username);
				String password = request.getParameter("password");
				String loginType = request.getParameter("loginType");//增加支持短信和密码验证
				if("psd".equals(loginType)) {//密码验证登录
				if (user!=null&&password != null && !password.equalsIgnoreCase(user.getPassword()))
				{
					throw new UsernameNotFoundException("The username or password you've entered is incorrect");
				}
				}else {//短信验证登录
					if (request.getParameter("password") != null)
					{
						if (!smsService.verificationSmsSession(username, request.getParameter("password")))
						{
							throw new UsernameNotFoundException("UsernameNotFoundException");
						}
						if (user != null)
						{
							user.setPassword(request.getParameter("password"));
						}
					}
				}
				if (user == null)
				{
					throw new UsernameNotFoundException("UsernameNotFoundException");
				}
				String autoLoginPassword = AbstractController.getAutoLoginPassword();
				if (autoLoginPassword != null)
				{
					user.setPassword("autoLogin");
				}
				user.setJsessionId(request.getSession().getId());
				return user;
			}
		};
	}

    @Bean
    // 在Repository中使用与权限认证相关的SpEL时需要添加Bean
    // https://spring.io/blog/2014/07/15/spel-support-in-spring-data-jpa-query-definitions#spel-evaluationcontext-extension-model
    public EvaluationContextExtension securityExtension()
    {
        return new EvaluationContextExtensionSupport()
        {
            @Override
            public String getExtensionId()
            {
                return "security";
            }

            @Override
            public SecurityExpressionRoot getRootObject()
            {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                return new SecurityExpressionRoot(authentication)
                {
                };
            }
        };
    }

	@Bean
	public AuthenticationProvider authenticationProvider(
			UserDetailsService userDetailsService)
	{
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		return authenticationProvider;
	}

	@Override
    public void configure(WebSecurity web)
    {
		web.ignoring()
		   .antMatchers("/error/**", "/lib/**", "/js/**", "/css/**", "/images/**", "/**/favicon.ico");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.csrf().disable();

		http.authorizeRequests()
		    .antMatchers("/api/common/**",
				    "/api/account/login",
				    "/api/account/register",
				    "/api/account/getUserAvatar/**",
				    "/api/mission/nearby",
				    "/api/order/webHooks").permitAll()
		    .antMatchers("/api/**").authenticated();


		// 配置登录
		http.formLogin()
		    .loginPage("/api/account/login")
		    .successForwardUrl("/api/account/loginSuccess");

		// 注销
		http.logout()
		    .logoutUrl("/api/account/logout")
		    .logoutSuccessUrl("/api/account/logoutSuccess")
		    .invalidateHttpSession(true)
		    .deleteCookies("rmsession");

		// 记住登陆状态
		http.rememberMe()
		    .alwaysRemember(true)
		    .rememberMeCookieName("rmsession")
		    .tokenValiditySeconds(1800);

		// 会话管理
		http.sessionManagement()
		    .sessionFixation()
		    .migrateSession()
		    .maximumSessions(1)
		    .expiredUrl("/api/account/login?reason=maxSessions");
	}
}
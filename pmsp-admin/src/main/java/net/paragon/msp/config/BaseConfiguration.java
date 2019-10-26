/**
 * 
 */
package net.paragon.msp.config;

import java.util.Locale;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties.LocaleResolver;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author bqduc
 *
 */
//@Slf4j
@EnableCaching
@Configuration
@EnableJpaRepositories(basePackages = {"net.paragon"})
@ComponentScan(basePackages = {"net.paragon"})
@EntityScan(basePackages={"net.paragon"})
@EnableTransactionManagement
public class BaseConfiguration {
	/*
	@Inject
	private ApplicationContext applicationContext;
	*/

	/**
	 * {@link PasswordEncoder} bean.
	 * 
	 * @return <b>{@code BCryptPasswordEncoder}</b> with strength (passed as
	 *         argument) the log rounds to use, between 4 and 31
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	/*
	@Bean
	  public MessageSource messageSource() {
	  	String[] resourceBundles = new String[]{
	  			"classpath:/i18n/Messages", 
	    		"classpath:/i18n/ValidationMessages"
	    };
	  	
	  	//log.info("Initialize the message source......");
	  	
	  	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	      messageSource.setBasenames(resourceBundles);
	      messageSource.setDefaultEncoding("UTF-8");
	      return messageSource;
	  }*/
/*
		@Bean
    public SpringSecurityDialect springSecurityDialect(){
        return new SpringSecurityDialect();
    }
*/		

		@Bean
		public ThreadPoolTaskExecutor taskExecutor() {
			ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
			taskExecutor.setCorePoolSize(5);
			taskExecutor.setMaxPoolSize(25);
			taskExecutor.setQueueCapacity(100);
			taskExecutor.initialize();
			return taskExecutor;
		}
}

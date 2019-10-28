/**
 * 
 */
package net.paragon.msp.config;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.HierarchicalMessageSource;
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
  @Autowired(required = false)
  @Qualifier("external")
  private List<HierarchicalMessageSource> externalMessageSources = Collections.emptyList();

  @Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:messages");
	    messageSource.setCacheSeconds(10); //reload messages every 10 seconds
	    
    ReloadableResourceBundleMessageSource rootMessageSource = new ReloadableResourceBundleMessageSource();
    rootMessageSource.setBasenames("classpath:messages");

    if (externalMessageSources.isEmpty()) {
        // No external message sources found, just main message source will be used
        return rootMessageSource;
    }
    else {
        // Wiring detected external message sources, putting main message source as "last resort"
        int count = externalMessageSources.size();

        for (int i = 0; i < count; i++) {
            HierarchicalMessageSource current = externalMessageSources.get(i);
            current.setParentMessageSource( i == count - 1 ? rootMessageSource : externalMessageSources.get(i + 1) );
        }
        return externalMessageSources.get(0);
    }
	}	*/
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

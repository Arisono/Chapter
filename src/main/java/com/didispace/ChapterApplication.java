package com.didispace;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SuppressWarnings("unused")
@SpringBootApplication
public class ChapterApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ChapterApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(ChapterApplication.class);
	}

	//
	// @Bean
	// public EmbeddedServletContainerFactory servletContainer() {
	//
	// TomcatEmbeddedServletContainerFactory tomcat = new
	// TomcatEmbeddedServletContainerFactory() {
	//
	// @Override
	// protected void postProcessContext(Context context) {
	//
	// SecurityConstraint securityConstraint = new SecurityConstraint();
	// securityConstraint.setUserConstraint("CONFIDENTIAL");
	// SecurityCollection collection = new SecurityCollection();
	//// collection.addMethod("post");
	// collection.addPattern("/*");
	// securityConstraint.addCollection(collection);
	// context.addConstraint(securityConstraint);
	// }
	// };
	// tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
	//
	// return tomcat;
	// }
	//
	// private Connector initiateHttpConnector() {
	// Connector connector = new Connector(
	// "org.apache.coyote.http11.Http11NioProtocol");
	// connector.setScheme("http");
	// connector.setPort(8080);
	// connector.setSecure(false);
	//
	// connector.setRedirectPort(8081);
	// return connector;
	// }

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {

		return new EmbeddedServletContainerCustomizer() {
			@Override
			public void customize(
					ConfigurableEmbeddedServletContainer container) {

				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND,
						"/index.html");

				container.addErrorPages(error404Page);
			}
		};
	}

}

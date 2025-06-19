package com.tienda;

import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration //Esto le dice a Spring que esta clase es de configuración. 
//Spring buscará esta clase al iniciar y aplicará lo que aquí se defina (como beans o interceptores).

public class ProjectConfig implements WebMvcConfigurer {
    /* Los siguientes métodos son para incorporar el tema de internacionalización en el proyecto */
    
    /* localeResolver se utiliza para crear una sesión de cambio de idioma*/
    @Bean
    public LocaleResolver localeResolver() {
        var slr = new SessionLocaleResolver(); // Usa la sesión del usuario para guardar su idioma preferido.
        slr.setDefaultLocale(Locale.getDefault()); // Usa el idioma predeterminado del sistema (por ejemplo, español si estás en Costa Rica).
        slr.setLocaleAttributeName("session.current.locale"); // Guarda el idioma como una variable llamada "session.current.locale".
        slr.setTimeZoneAttributeName("session.current.timezone"); // Guarda la zona horaria en sesión (opcional).
        return slr;
    }

    /* localeChangeInterceptor se utiliza para crear un interceptor de cambio de idioma*/
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() { // Este método permite cambiar el idioma usando un parámetro en la URL.
        var lci = new LocaleChangeInterceptor(); // Crea un interceptor para detectar cambios de idioma.
        lci.setParamName("lang"); // Le dice que el parámetro lang en la URL será usado para cambiar el idioma.
        return lci;
    }

    /* Le dice a Spring que active el interceptor anterior (localeChangeInterceptor) 
    para que funcione cada vez que alguien navegue por la web.*/
    @Override 
    public void addInterceptors(InterceptorRegistry registro) {
        registro.addInterceptor(localeChangeInterceptor());
    }

    //Bean para poder acceder a los Messages.properties en código...
    // Este método carga los archivos con los textos en diferentes idiomas.
    @Bean("messageSource") 
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource= new ResourceBundleMessageSource(); //Crea el manejador para buscar los archivos messages_xx.properties.
        messageSource.setBasenames("messages"); // Busca archivos como: messages.properties, messages_en.properties, messages_es.properties, etc.
        messageSource.setDefaultEncoding("UTF-8"); // Usa UTF-8 para que los textos se vean bien (tildes, ñ, etc.).
        return messageSource;
    }
}
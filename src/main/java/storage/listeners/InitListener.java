package storage.listeners;

import storage.repositories.UsersRepository;
import storage.repositories.impl.UsersRepositoryImpl;
import storage.services.Authorization;
import storage.services.PasswordEncoder;
import storage.services.UserMapper;
import storage.services.impl.AuthorizationImpl;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import storage.services.impl.PasswordEncoderImpl;
import storage.services.impl.UserMapperImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class InitListener implements ServletContextListener {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "ateez";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/library";
    private static final String DB_DRIVER = "org.postgresql.Driver";
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Postgresql Driver not found.");
        }

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        PasswordEncoder passwordEncoder = new PasswordEncoderImpl();
        UserMapper userMapper = new UserMapperImpl();
        UsersRepository usersRepository = new UsersRepositoryImpl(dataSource);
        Authorization authorization = new AuthorizationImpl(usersRepository, userMapper, passwordEncoder);

        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("usersRepository", usersRepository);
        servletContext.setAttribute("authorization", authorization);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {}
}

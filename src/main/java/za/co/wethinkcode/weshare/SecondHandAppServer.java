package za.co.wethinkcode.weshare;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.jetbrains.annotations.NotNull;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import za.co.wethinkcode.weshare.app.DefaultAccessManager;
import za.co.wethinkcode.weshare.app.Sessions;
import za.co.wethinkcode.weshare.calculator.PhoneFromDatabaseController;
import za.co.wethinkcode.weshare.calculator.ClaimsApiController;
import za.co.wethinkcode.weshare.login.LoginController;
import za.co.wethinkcode.weshare.add.AddPhoneToDatabaseController;


import static io.javalin.apibuilder.ApiBuilder.*;

public class SecondHandAppServer {
    private static final int DEFAULT_PORT = 7070;
    private static final String STATIC_DIR = "/html";

    /**
     * The main class starts the server on the default port 7070.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Javalin app = getInstance();
        app.start(DEFAULT_PORT);
    }

    /**
     * This is a convenience for running Selenium tests.
     * It allows the test to get access to the server to start and stop it.
     * @return a configured server for the app
     */
    public static Javalin getInstance() {
        configureThymeleafTemplateEngine();
        Javalin server = createAndConfigureServer();
        setupRoutes(server);
        return server;
    }

    /**
     * Setup the Thymeleaf template engine to load templates from 'resources/templates'
     */
    private static void configureThymeleafTemplateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();

        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix("/templates/");
        templateEngine.setTemplateResolver(templateResolver);

        templateEngine.addDialect(new LayoutDialect());
        JavalinThymeleaf.configure(templateEngine);
    }

    private static void setupRoutes(Javalin server) {
        server.routes(() -> {
            loginAndLogoutRoutes();
            homePageRoute();
            claimRoutes();
        });
    }


    private static void claimRoutes() {
        get(PhoneFromDatabaseController.PHONE_PATH, PhoneFromDatabaseController::renderPhonePage);
        post(ClaimsApiController.CLAIMS_PATH, ClaimsApiController::create);
    }



    private static void homePageRoute() {
        path(AddPhoneToDatabaseController.PATH, () -> get(AddPhoneToDatabaseController::renderHomePage));
    }

    private static void loginAndLogoutRoutes() {
        post(LoginController.LOGIN_PATH, LoginController::handleLogin);
        get(LoginController.LOGOUT_PATH, LoginController::handleLogout);
    }

    @NotNull
    private static Javalin createAndConfigureServer() {
        return Javalin.create(config -> {
            config.addStaticFiles(STATIC_DIR, Location.CLASSPATH);
            config.sessionHandler(Sessions::nopersistSessionHandler);
            config.accessManager(new DefaultAccessManager());
        });
    }
}

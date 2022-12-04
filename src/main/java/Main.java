import config.DefaultServer;
import db_migration.DBCreator;
import filter.MyFilter;
import handler.SecurityHandlerBuilder;
import org.eclipse.jetty.security.ConstraintSecurityHandler;
import org.eclipse.jetty.security.JDBCLoginService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.DoSFilter;
import org.eclipse.jetty.servlets.QoSFilter;
import org.eclipse.jetty.util.resource.Resource;
import servlet.MainServlet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import java.net.URL;
import java.util.EnumSet;

public class Main {
    public static void main(String[] args) throws Exception {
        DBCreator.init();

        final Server server = new DefaultServer().build();

        ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        final URL resource = DefaultServlet.class.getResource("/static/doc.html");
        context.setBaseResource(Resource.newResource(resource.toExternalForm()));
        context.addServlet(DefaultServlet.class, "/*");
        context.addServlet(new ServletHolder("product", new MainServlet()), "/product");

        final Filter filter = new MyFilter();
        final FilterHolder filterHolder = new FilterHolder(filter);
        filterHolder.setInitParameter("maxRequests", "1");
        context.addFilter(filterHolder, "/product", EnumSet.of(DispatcherType.REQUEST));
        server.setHandler(context);

        final String jdbcConfig = Main.class.getResource("/db/jdbc_config").toExternalForm();
        final JDBCLoginService jdbcLoginService = new JDBCLoginService("login", jdbcConfig);
        final ConstraintSecurityHandler securityHandler = new SecurityHandlerBuilder().build(jdbcLoginService);
        server.addBean(jdbcLoginService);
        securityHandler.setHandler(context);
        server.setHandler(securityHandler);

        server.start();
    }
}

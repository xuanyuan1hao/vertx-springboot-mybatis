package com.ayit.demo.springboot;

import com.ayit.demo.handler.BookHandler;
import com.ayit.demo.service.BookAsyncService;
import com.ayit.demo.service.BookService;
import com.ayit.demo.service.impl.BookAsyncServiceImpl;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.AuthHandler;
import io.vertx.ext.web.handler.RedirectAuthHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;
import io.vertx.serviceproxy.ServiceProxyBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ayit.demo.springboot.ApplicationConfiguration;

import java.security.AuthProvider;

@Component
public class StaticServer extends AbstractVerticle {

    @Autowired
    ApplicationConfiguration configuration;

    @Autowired
    private BookAsyncService bookAsyncService;

    private FreeMarkerTemplateEngine templateEngine;


    @Override
    public void start() throws Exception {
        System.out.print("StaticServer.start \n");

        Router router = Router.router(vertx);


        router.route("/book").handler(routingContext -> BookHandler.addBook(routingContext, bookAsyncService));


        router.route("/books").handler((routingContext) -> BookHandler.getAllBooks(routingContext, bookAsyncService));


        router.route("/dev").handler(new Handler<RoutingContext>() {
            @Override
            public void handle(RoutingContext routingContext) {
                routingContext.response().end("dev");
            }
        });

        router.route("/").handler((routingContext -> BookHandler.index(routingContext,templateEngine)));

        router.route().handler(StaticHandler.create());

        vertx.createHttpServer().requestHandler(router::accept).listen(configuration.httpPort());
    }

    public void setFreeMarkerTemplateEngine(FreeMarkerTemplateEngine freeMarkerTemplateEngine) {
        this.templateEngine = freeMarkerTemplateEngine;
    }
}
package co.vinni.common.filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext,
                       ContainerResponseContext responseContext) throws IOException {

        // Permitimos explícitamente tu dominio de producción en Render
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "https://gestion-medicamentos-frontend.onrender.com");

        // Permitimos los métodos necesarios para la gestión
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");

        // Permitimos las cabeceras que Angular envía por defecto
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization, x-requested-with");

        // Si es una petición de pre-vuelo (OPTIONS), nos aseguramos de responder exitosamente
        if ("OPTIONS".equalsIgnoreCase(requestContext.getMethod())) {
            responseContext.setStatus(200);
        }
    }
}
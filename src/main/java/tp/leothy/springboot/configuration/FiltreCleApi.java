package tp.leothy.springboot.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FiltreCleApi extends OncePerRequestFilter {

    @Value("${application.cle-api}")
    private String cleApiAttendue;

    @Override
    protected void doFilterInternal(HttpServletRequest requete,
                                    HttpServletResponse reponse,
                                    FilterChain chaine) throws ServletException, IOException {

        String methode = requete.getMethod();
        boolean ecriture = HttpMethod.POST.matches(methode)
                || HttpMethod.PUT.matches(methode)
                || HttpMethod.PATCH.matches(methode)
                || HttpMethod.DELETE.matches(methode);

        if (!ecriture) {
            chaine.doFilter(requete, reponse);
            return;
        }

        String cleRecu = requete.getHeader("X-CLE-API");

        if (cleRecu == null || !cleRecu.equals(cleApiAttendue)) {
            reponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            reponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
            reponse.getWriter().write("{\"message\":\"Clé API manquante ou invalide.\"}");
            return;
        }

        chaine.doFilter(requete, reponse);
    }
}
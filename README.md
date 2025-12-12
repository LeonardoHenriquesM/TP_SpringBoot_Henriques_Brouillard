# TP_SpringBoot_Henriques_Brouillard (tp.leothy.springboot)

Projet de TP Spring Boot pour gérer une **bibliothèque** (auteurs + livres)  
avec **tout le code en français** et **Lombok**, package racine `tp.leothy.springboot`.

## Lancer l'application

```bash
mvn spring-boot:run
```

- API : `http://localhost:8080`
- Swagger UI : `http://localhost:8080/swagger-ui.html`
- Console H2 : `http://localhost:8080/h2-console`  
  (JDBC URL : `jdbc:h2:mem:bibliotheque-db`)

## Endpoints principaux

### Auteurs

- `GET /auteurs`
- `GET /auteurs/{identifiant}`
- `POST /auteurs`
- `PUT /auteurs/{identifiant}`
- `DELETE /auteurs/{identifiant}`

### Livres

- `GET /livres` avec filtres :

  - `titre`
  - `identifiantAuteur`
  - `categorie` (`ROMAN`, `ESSAI`, `POESIE`, `AUTRE`)
  - `anneeMin`
  - `anneeMax`
  - pagination / tri standard : `page`, `size`, `sort=anneeParution,desc`…

- `GET /livres/{identifiant}`
- `POST /livres`
- `PUT /livres/{identifiant}`
- `DELETE /livres/{identifiant}`

### Statistiques

- `GET /statistiques/livres-par-categorie`
- `GET /statistiques/top-auteurs?limite=3`

## Clé d'API

Toutes les requêtes **POST / PUT / PATCH / DELETE** exigent l'en-tête :

```http
X-CLE-API: MA_SUPER_CLE_API_FR
```

Valeur configurable dans `application.properties` :

```properties
application.cle-api=MA_SUPER_CLE_API_FR
```

## Lombok

Les classes de modèle et les DTO utilisent Lombok (`@Data`, `@Builder`, etc.)  
pour éviter le code répétitif (getters / setters / constructeurs).

## Doc des annotations

Voir `docs/annotations_spring_lombok_fr.md` pour une explication claire
de toutes les annotations `@...` utilisées dans le projet.

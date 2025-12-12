# TP_SpringBoot_Henriques_Brouillard (tp.leothy.springboot)

## pour lancer l'appli (je rapelle même si vous le savez déjà)

```bash
mvn spring-boot:run
```

- API : `http://localhost:8080`
- Swagger UI : `http://localhost:8080/swagger-ui.html`
- Console H2 : `http://localhost:8080/h2-console`  
  (JDBC URL : `jdbc:h2:mem:bibliotheque-db`)

## les endpoints

### auteurs

- `GET http://localhost:8080/auteurs`
- `GET http://localhost:8080/auteurs/id`
- `POST http://localhost:8080/auteurs`
- `PUT http://localhost:8080/auteurs/id`
- `DELETE http://localhost:8080/auteurs/id`

### les livres
 `GET /livres` avec filtres :

  - `titre`
  - `identifiantAuteur`
  - `categorie` (`ROMAN`, `ESSAI`, `POESIE`, `AUTRE`)
  - `anneeMin`
  - `anneeMax`
  - pages et tri classique : `page`, `size`, `sort=anneeParution,desc`…

- `GET http://localhost:8080/livres/id`
- `POST http://localhost:8080/livres`
- `PUT http://localhost:8080/livres/id`
- `DELETE http://localhost:8080/livres/id`

### stats

- `GET http://localhost:8080/statistiques/livres_par_categorie`
- `GET http://localhost:8080/statistiques/top-auteurs`

## cle pour l'api

toutes les requêtes **POST / PUT / PATCH / DELETE** vont demander cet en-tête pour la vérification :

```http
Dans postman aller dans headers et mettre Key = cle_api et Value = trop_style
```

vous pouvez changer le nom de la cle dans `application.properties` :

```properties
application.cle-api=trop_style
```

## lombok

Les classes/DTO utilisent Lombok (`@Data`, `@Builder`, etc.) je sais plus si c'était demandé mais j'aime bien l'utiliser ça mévite de réecrire touts les getters et setters et les builders etc ..

## doc avec les explications de chaque annotation

Dans `docs/annotations_springb.md` je me suis aidé de l'ia et des docs officielles: 

python(Python – Decorators & Annotations)
java(Java – Annotations)
Jakarta EE(Jakarta EE (anciennement Java EE) – Annotations pour frameworks)

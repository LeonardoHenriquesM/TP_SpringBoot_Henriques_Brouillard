# explications @ pour prof

## spring boot et config

`@SpringBootApplication` : Point d’entrée principal de l’application Spring Boot. Elle active la configuration automatique, le scan des composants et la configuration Spring.

`@Configuration` : Indique qu’une classe contient des définitions de configuration Spring. Permet de créer des beans manuellement.

`@Bean` : Déclare une méthode qui fournit un objet géré par Spring (bean).

`@Component` : Désigne une classe comme composant Spring générique détecté automatiquement lors du scan.

`@ComponentScan` : Indique à Spring dans quels packages chercher les composants, services et configurations.

`@Order` : Définit l’ordre d’exécution d’un composant, souvent utilisé pour les filtres.

---

## controller

`@RestController` : Spécifie qu’une classe est un contrôleur REST qui renvoie directement des objets JSON (au lieu de vues HTML).

`@RequestMapping` : Définit l’URL de base (préfixe) pour les routes du contrôleur.

`@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` : Définissent respectivement les routes HTTP GET, POST, PUT et DELETE.
GET lit des données, POST crée, PUT modifie, DELETE supprime.

`@PathVariable` : Récupère une valeur depuis l’URL, souvent un identifiant (`/auteurs/{id}`).

`@RequestParam` : Lit un paramètre envoyé dans la requête, par exemple `?tri=titre`.

`@RequestBody` : Indique que le corps JSON de la requête doit être converti en objet Java.

`@ResponseStatus` : Force le code HTTP renvoyé (ex. `201 CREATED`, `204 NO_CONTENT`).

`@ResponseEntity` : Permet de contrôler à la fois le corps et le code HTTP de la réponse.

---

## jpa et hibernate

`@Entity` : Marque une classe comme entité persistante liée à une table SQL.

`@Table` : Configure le nom ou les contraintes d’une table (unique, index…).

`@Id` : Indique la clé primaire d’une entité.

`@GeneratedValue(strategy = GenerationType.IDENTITY)` : Demande à la base de générer automatiquement la valeur de la clé primaire.

`@Column` : Permet de configurer une colonne (nom, nullabilité, longueur…).

`@Enumerated(EnumType.STRING)` : Spécifie qu’une énumération doit être enregistrée en texte dans la base (et non en entier).

`@ManyToOne` : Indique qu’une entité est liée à plusieurs instances d’une autre (ex. plusieurs livres pour un auteur).

`@OneToMany` : Relation inverse d’un `@ManyToOne` : un auteur peut avoir plusieurs livres.

`@JoinColumn` : Définit la colonne de clé étrangère (ex. `auteur_id`).

---

## repository

`@Repository` : Indique que la classe ou interface correspond à un composant d’accès aux données (DAO).
Permet à Spring de gérer automatiquement les exceptions de persistance.

---

## service

`@Service` : Marque une classe comme service métier. Elle contient la logique entre les contrôleurs et les dépôts (`Repository`).

`@Autowired` : Injecte automatiquement une dépendance (comme un service ou un dépôt).

---

## validation des entrées des utilisateur

`@NotNull` : Indique qu’une valeur ne peut pas être nulle.

`@NotBlank` : Indique qu’une chaîne de caractères ne peut pas être vide ou ne contenir que des espaces.

`@PositiveOrZero` : Indique qu’un nombre doit être positif ou égal à zéro.

`@Min(value = x)` : Indique qu’un nombre doit être supérieur ou égal à `x`.

`@Pattern(regexp = "…")` : Valide un champ texte en fonction d’une expression régulière (utile pour les ISBN).

`@Valid` : Permet de valider les objets complexes (comme les DTO) avant de les traiter.

---

## la gestion des erreurs

`@RestControllerAdvice` : Regroupe la gestion des erreurs REST dans une seule classe pour tous les contrôleurs.

`@ExceptionHandler` : Associe une méthode à un type d’exception pour renvoyer une réponse JSON claire au client.

---

## config et les @ dans clé api

`@Component` : Utilisé pour enregistrer la classe `FiltreCleApi` comme filtre Spring géré automatiquement.

`@Value` : Injecte une valeur issue du fichier `application.properties` (comme ta clé API).

`@Order(1)` : Indique la priorité du filtre par rapport aux autres.

---

## lombok pour réduire le code avec les @getter et @setter notamment

`@Data` : Génère automatiquement les getters, setters, `toString`, `equals` et `hashCode`.

`@Getter` / `@Setter` : Génèrent respectivement les accesseurs et les mutateurs pour chaque attribut.

`@NoArgsConstructor` : Crée un constructeur sans arguments.

`@AllArgsConstructor` : Crée un constructeur avec tous les attributs.

`@Builder` : Active le pattern “builder” pour instancier facilement un objet fluide (`Livre.builder().titre("1984")...build()`).

`@ToString.Exclude` : Permet d’exclure un attribut du `toString()` (utile pour éviter les boucles infinies dans les relations bidirectionnelles).

`@Slf4j` : Génère automatiquement un logger nommé `log` pour écrire des messages dans la console.

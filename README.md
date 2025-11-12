# 🧭 Spring Data JPA Auditing - Exemplo Prático

Este é um projeto simples criado para demonstrar o uso do **Spring Data JPA Auditing**, um recurso poderoso que permite preencher automaticamente informações como quando uma entidade foi criada/modificada ,quem criou e quem atualizou.

---

## 🚀 Sobre o projeto

A ideia aqui é mostrar, de forma prática e didática, como habilitar e usar o recurso de auditoria do Spring Data JPA.

Neste exemplo, eu **não utilizei autenticação real** (como JWT ou Spring Security).  
Em vez disso, criei uma **simulação de “usuário autenticado”** usando uma lista de usuários mock e selecionando um deles de forma aleatória.

Isso facilita a demonstração do funcionamento do JPA Auditing sem precisar configurar um sistema de login completo. :)

---

## 🧠 O que é o Spring Data JPA Auditing?

O **Spring Data JPA Auditing** permite que o próprio framework preencha automaticamente campos como:

- `@CreatedBy` → quem criou o registro  
- `@LastModifiedBy` → quem fez a última atualização  
- `@CreatedDate` → data/hora da criação  
- `@LastModifiedDate` → data/hora da última modificação  

Basta anotar a entidade e habilitar a auditoria.

---

## 🧾 Exemplo de entidade auditável

```java
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private String description;

    @CreatedBy
    private String createdBy;

    @LastModifiedBy
    private String updatedBy;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
```

## ⚙️ A implementação do AuditorAware
O AuditorAware é uma interface que o Spring usa para descobrir quem é o usuário atual no momento em que uma entidade é criada ou atualizada. No meu exemplo, eu optei por uma implementação simples que simula o usuário autenticado:

```java
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    private final List<User> users = new ArrayList<>();

    @Autowired
    private UserService userService;

    @PostConstruct
    public void loadMockUsers() {
        // Cria e guarda os usuários mock assim que a aplicação inicia
        users.addAll(userService.createMockUsers());
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        int index = new Random().nextInt(users.size());
        return Optional.of(users.get(index).getName());
    }
}
```

## Em uma aplicação real
Em um projeto com autenticação (por exemplo, com Spring Security), o AuditorAware poderia ser mais ou menos assim:

```java
@Component
public class SecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        return Optional.of(authentication.getName()); // Nome do usuário autenticado
    }
}
```
Assim, o Spring pegaria o usuário logado de verdade para preencher as informações de auditoria.

package com.example.jpa_auditing.audit;

import com.example.jpa_auditing.domain.User;
import com.example.jpa_auditing.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * Implementação de AuditorAware<String> usada pelo Spring Data JPA para determinar
 * qual "usuário" está realizando a operação no momento.
 *
 * O Spring chama o método getCurrentAuditor() automaticamente sempre que
 * uma entidade com campos anotados com @CreatedBy ou @LastModifiedBy
 * é persistida ou atualizada.
 *
 * Nesse exemplo, em vez de integrar com autenticação real (ex: Spring Security),
 * eu simulei um usuário autenticado de forma aleatória entre uma lista de usuários mock.
 */
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    private final List<User> users = new ArrayList<>();

    @Autowired
    private UserService userService;

    @PostConstruct
    public void loadMockUsers(){
        // Cria e guarda os usuários mock assim que a aplicação inicia
        users.addAll(userService.createMockUsers());
    }

    @Override
    public Optional<String> getCurrentAuditor() {
        if (users.isEmpty()) {
            return Optional.empty();
        }
        int index = new Random().nextInt(users.size());
        String randomUserName = users.get(index).getName();

        return Optional.of(randomUserName);
    }
}

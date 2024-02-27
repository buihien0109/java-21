package vn.techmaster.demothymeleafspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.demothymeleafspringsecurity.entity.TokenConfirm;

import java.util.Optional;

public interface TokenConfirmRepository extends JpaRepository<TokenConfirm, Integer> {
    Optional<TokenConfirm> findByTokenAndTokenType(String token, TokenConfirm.TokenType tokenType);
}
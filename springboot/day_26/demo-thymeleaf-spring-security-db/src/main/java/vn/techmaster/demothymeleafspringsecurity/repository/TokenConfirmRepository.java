package vn.techmaster.demothymeleafspringsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.techmaster.demothymeleafspringsecurity.entity.TokenConfirm;

public interface TokenConfirmRepository extends JpaRepository<TokenConfirm, Integer> {
}
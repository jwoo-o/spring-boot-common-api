package com.gen.api.global.domain.entity.repositorys;

import com.gen.api.global.Enum.LoginHistoryEnum;
import com.gen.api.global.domain.entity.PasswordHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-10-21
 * Time: 오후 1:42
 */
public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Long> {

    List<PasswordHistory> findAllByLoginIdAndLoginTypeOrderByCreatedAtAsc(String loginId, LoginHistoryEnum type);

}

package com.gen.api.global.domain.entity.repositorys;

import com.gen.api.global.domain.entity.UserPolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-10-21
 * Time: 오후 1:44
 */
public interface UserPolicyRepository extends JpaRepository<UserPolicy, Long> {

    Optional<UserPolicy> findByUserId(String userId);

    @Modifying
    @Query(value = "DELETE FROM tb_user_policy u WHERE u.userId = :userId")
    void deleteByUserId(@Param("userId")String userId);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM tb_user_policy u WHERE u.userId in :userIds")
    void deleteAllByUserIds(@Param("userIds") Collection<String> userIds);
}

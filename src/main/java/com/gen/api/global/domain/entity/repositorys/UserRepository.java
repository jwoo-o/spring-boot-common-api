package com.gen.api.global.domain.entity.repositorys;

import com.gen.api.global.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-10-21
 * Time: 오후 1:43
 */
public interface UserRepository extends JpaRepository<User, String> {


    Stream<User> findByOrgCodeAndUseYn(String orgCode, boolean useYn);

    Optional<User> findByUserIdAndUseYn(String userId, boolean useYn);

    int countByUserId(String userId);

    @Modifying
    @Query(value = "UPDATE tb_user u SET u.orgCode = :orgCode,u.modifiedAt = current_timestamp WHERE u.orgCode in :orgCodes")
    void updateOrgCodeByOrgCodes(@Param("orgCodes") Collection<String> orgCodes, @Param("orgCode") String orgCode);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM tb_user u WHERE u.userId = :userId")
    void deleteByUserId(@Param("userId") String userId);
}

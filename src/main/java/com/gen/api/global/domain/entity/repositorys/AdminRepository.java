package com.gen.api.global.domain.entity.repositorys;

import com.gen.api.global.domain.entity.Admin;
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
 * Time: 오후 1:39
 */
public interface AdminRepository extends JpaRepository<Admin, String> {

    Optional<Admin> findByAdminIdAndUseYn(String adminId, boolean useYn);

    int countByAdminIdAndUseYn(String adminId, boolean useYn);

    int countByAdminId(String id);

    @Modifying
    @Query(value = "DELETE FROM tb_admin a WHERE a.mgrOrgCode in :orgCodes")
    void deleteByOrgCodes(@Param("orgCodes") Collection<String> orgCodes);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE tb_admin a SET a.useYn = 'N', a.modifiedAt = current_timestamp WHERE a.orgCode in :orgCodes")
    void updateUseYnByOrgCodes(@Param("orgCodes") Collection<String> orgCodes);

    @Modifying
    @Query(value = "UPDATE tb_admin a SET a.orgCode = :orgCode, a.modifiedAt = current_timestamp WHERE a.orgCode in :orgCodes")
    void updateOrgCodeByOrgCodes(@Param("orgCodes") Collection<String> orgCodes, @Param("orgCode") String orgCode);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM tb_admin a WHERE a.adminId = :adminId")
    void deleteByAdminId(@Param("adminId") String adminId);
}


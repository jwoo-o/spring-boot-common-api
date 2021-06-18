package com.gen.api.global.domain.entity.repositorys;

import com.gen.api.global.domain.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-10-21
 * Time: 오후 1:41
 */
public interface OrganizationRepository extends JpaRepository<Organization, String> {

    int countByOrgCode(String orgCode);

    @Modifying
    @Query(value = "DELETE FROM tb_organization o WHERE o.orgCode in :orgCodes")
    void deleteByOrgCodeIn(@Param("orgCodes") Collection<String> orgCodes);

    Optional<Organization> findFirstByOrgCodeLikeOrderByOrgCodeDesc(String orgCode);

    List<Organization> findByOrgPaCode(String orgCode);
}

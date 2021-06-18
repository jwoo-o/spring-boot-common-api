package com.gen.api.global.domain.entity.repositorys;

import com.gen.api.global.domain.entity.OrganizationPolicy;
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
 * Time: 오후 1:42
 */
public interface OrganizationPolicyRepository extends JpaRepository<OrganizationPolicy, Long> {

    Optional<OrganizationPolicy> findByOrgCode(String orgCode);

    @Modifying
    @Query(value = "DELETE FROM tb_organization_policy p WHERE p.orgCode in :orgCodes")
    void deleteByOrgCodeIn(@Param("orgCodes") Collection<String> orgCodes);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE tb_organization_policy p SET p.policy = :policy, p.settingId = :settingId, p.modifiedAt = current_timestamp WHERE p.orgCode in :orgCodes")
    void updatePolicyAndSettingIByOrgCodes(@Param("orgCodes") Collection<String> orgCodes, @Param("policy") String policy, @Param("settingId") Long settingId);
}

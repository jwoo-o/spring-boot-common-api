package com.gen.api.global.domain.entity.repositorys;

import com.gen.api.global.domain.entity.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-10-21
 * Time: 오후 1:39
 */
public interface AgentRepository extends JpaRepository<Agent, String> {

    @Modifying
    @Query(value = "UPDATE tb_agent a SET a.userId = null, a.name = null, a.orgCode = '-1', a.modifiedAt = current_timestamp WHERE a.userId = :userId")
    void updateUserId(@Param("userId") String userId);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE tb_agent a SET a.syncTime = current_timestamp WHERE a.pcId = :pcId")
    void updateSyncTimeByPcId(@Param("pcId") String pcId);

    @Modifying
    @Query(value = "UPDATE tb_agent a SET a.orgCode = :orgCode, a.modifiedAt = current_timestamp WHERE a.orgCode in :orgCodes")
    void updateOrgCodeByOrgCodes(@Param("orgCodes") Collection<String> orgCodes, @Param("orgCode") String orgCode);

    Stream<Agent> findByOrgCodeAndDeletion(String orgCode, boolean deletion);

    Stream<Agent> findByUserIdAndOrgCodeAndDeletion(String userId, String orgCode, boolean deletion);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE tb_agent a SET a.loginYn = false, a.userId = null, a.name = null, a.orgCode = '-1', a.deletion= true, a.modifiedAt = current_timestamp WHERE a.pcId in :pcIds")
    void updateDeletionByPcIds(@Param("pcIds") Collection<String> pcIds);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE tb_agent a SET a.userId = null, a.name = null, a.orgCode = '-1', a.modifiedAt = current_timestamp WHERE a.pcId = :pcId")
    void updateChangeByPcId(@Param("pcId") String pcId);

    @Modifying
    @Query(value = "UPDATE tb_agent a SET a.orgCode = :orgCode, a.modifiedAt = current_timestamp WHERE a.userId = :userId")
    void updateOrgCodeByUserId(@Param("userId") String userId, @Param("orgCode") String orgCode);

    Stream<Agent> findByUserIdAndDeletion(String userId, boolean deletion);

    Stream<Agent> findByUserIdInAndDeletion(Collection<String> userId, boolean deletion);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE tb_agent a SET a.loginYn = :loginYn")
    void updateLoginYnAll(@Param("loginYn") boolean loginYn);


    List<Agent> findByOrgCodeInAndDeletion(Collection<String> orgCodes,boolean deletion);
}

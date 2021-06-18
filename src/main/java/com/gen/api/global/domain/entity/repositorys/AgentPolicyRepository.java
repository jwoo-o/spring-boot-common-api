package com.gen.api.global.domain.entity.repositorys;

import com.gen.api.global.domain.entity.AgentPolicy;
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
public interface AgentPolicyRepository extends JpaRepository<AgentPolicy, Long> {

    Optional<AgentPolicy> findByPcId(String pcId);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM tb_agent_policy a WHERE a.pcId in :pcIds")
    void deleteAllByPcIds(@Param("pcIds") Collection<String> pcIds);
}

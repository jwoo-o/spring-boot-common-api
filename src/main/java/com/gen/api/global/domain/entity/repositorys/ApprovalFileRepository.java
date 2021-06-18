package com.gen.api.global.domain.entity.repositorys;

import com.gen.api.global.domain.entity.ApprovalFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.stream.Stream;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-10-21
 * Time: 오후 1:39
 */
public interface ApprovalFileRepository extends JpaRepository<ApprovalFile, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE tb_approval_file a set a.requestId= :requestId WHERE a.id in :ids")
    void updateRequestIdByIds(@Param("ids") Collection<Long> ids, @Param("requestId") Long requestId);

    @Query(value = "SELECT r from tb_approval_file r where r.requestId in :ids")
    Stream<ApprovalFile> findAllByRequestIdIn(@Param("ids") Collection<Long> ids);
}

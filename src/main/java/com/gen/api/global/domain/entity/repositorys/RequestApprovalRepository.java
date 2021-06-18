package com.gen.api.global.domain.entity.repositorys;

import com.gen.api.global.Enum.ApprovalEnum;
import com.gen.api.global.domain.entity.RequestApproval;
import com.gen.bluexray.server.approvalLog.dto.ApprovalRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-10-21
 * Time: 오후 1:42
 */
public interface RequestApprovalRepository extends JpaRepository<RequestApproval, Long> {

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE tb_request_approval r SET r.modifiedAt = :#{#dto.now} " +
            ",r.approvalType = :#{#dto.approvalEnum}, r.adminId = :#{#dto.adminId}," +
            "r.approvalAt = :#{#dto.now}, r.rejectReason = :#{#dto.rejectReason} WHERE r.id in :#{#dto.approvalIds}")
    void updateApprovalTypeByIds(@Param("dto") ApprovalRequestDto dto);

    @Query(value = "select r.id from tb_request_approval r where r.pcId = :pcId and r.approvalType = :approvalType")
    List<Long> findByPcIdAndApprovalType(@Param("pcId") String pcId,@Param("approvalType") ApprovalEnum approvalType);

    @Query(value = "select r from tb_request_approval r where r.id in :ids group by r.pcId")
    List<RequestApproval> findAllByIdGroupByPcId(@Param("ids") Collection<Long> ids);
}

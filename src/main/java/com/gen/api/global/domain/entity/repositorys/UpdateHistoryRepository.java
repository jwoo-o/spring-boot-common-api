package com.gen.api.global.domain.entity.repositorys;


import com.gen.api.global.domain.entity.UpdateHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-10-21
 * Time: 오후 1:40
 */
public interface UpdateHistoryRepository extends JpaRepository<UpdateHistory, Long> {

    @Modifying
    @Query("update tb_update_history u set u.useYn = 'N'")
    void updateUseYnByAll();

    Optional<UpdateHistory> findByVersion(String version);


    Optional<UpdateHistory> findByUseYn(boolean useYn);
}

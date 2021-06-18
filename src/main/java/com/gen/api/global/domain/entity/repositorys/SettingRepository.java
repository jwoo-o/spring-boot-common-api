package com.gen.api.global.domain.entity.repositorys;

import com.gen.api.global.domain.entity.Setting;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-10-21
 * Time: 오후 1:43
 */
public interface SettingRepository extends JpaRepository<Setting, Long> {

    int countBySettingName(String settingName);
}

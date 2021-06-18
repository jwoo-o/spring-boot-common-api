package com.gen.api.global.domain.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gen.api.global.domain.common.BaseEntity;
import com.gen.api.global.util.JsonUtil;
import com.gen.bluexray.server.setting.dto.SettingUpdRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-11-10
 * Time: 오후 4:11
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_setting")
public class Setting extends BaseEntity {

    @Column(name = "setting_name", length = 40, unique = true)
    private String settingName;

    @Column(name = "ex_folder", length = 2048)
    private String exFolders;
    @Column(name = "file_extension", length = 2048)
    private String fileExtensions;
    @Column(name = "inspect_regex", length = 2048)
    private String inspectRegex;

    @OneToMany(mappedBy = "setting")
    private List<OrganizationPolicy> policies;


    @Builder
    public Setting(String settingName, String exFolders, String fileExtensions, String inspectRegex) {
        this.settingName = settingName;
        this.exFolders = exFolders;
        this.fileExtensions = fileExtensions;
        this.inspectRegex = inspectRegex;
    }

    public void update(SettingUpdRequestDto dto) throws JsonProcessingException {

        this.exFolders = JsonUtil.dtoToString(dto.getExFolderList());
        this.fileExtensions = JsonUtil.dtoToString(dto.getExtList());
        this.inspectRegex = JsonUtil.dtoToString(dto.getRegexList());
    }
}

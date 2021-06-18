package com.gen.api.global.domain.entity;

import com.gen.api.global.domain.common.BaseEntity;
import com.gen.api.global.util.JsonUtil;
import com.gen.bluexray.server.role.dto.RolesRequestDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2020-10-21
 * Time: 오전 10:07
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_role")
public class Role extends BaseEntity {

    /**
     * 권한
     */
    @Column(name = "permission", length = 2048)
    private String permission;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "role_desc")
    private String roleDesc;

    @Column(name = "level")
    private Long level;

    @OneToMany(mappedBy = "role")
    private List<Admin> admins = new ArrayList<>();


    @Builder
    public Role(String permission, String roleName, String roleDesc, Long level) {
        this.permission = permission;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.level = level;
    }

    public void update(RolesRequestDto dto) throws Exception {
        this.permission = JsonUtil.dtoToString(dto.getPermission());
        this.roleDesc = dto.getRoleDesc();
        this.roleName = dto.getRoleName();
        this.level = dto.getLevel();
    }
}

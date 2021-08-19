package com.gen.api.global.domain.entity;


import com.gen.api.global.domain.common.BaseTimeEntity;
import com.gen.api.global.domain.common.BooleanToYNConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jinwoo.
 * User: jwoh
 * Date: 2020-07-03
 * Time: 오후 5:00
 */
@NoArgsConstructor
@Getter
@Entity(name = "tb_user")
@Table(name = "tb_user",
        indexes = {
                @Index(name = "i_tb_user_use_yn_org_code_created_at", columnList = "use_yn,org_code,created_at"),
                @Index(name = "i_tb_user_use_yn_created_at", columnList = "use_yn,created_at"),
                @Index(name = "i_tb_user_name_use_yn_org_code_created_at", columnList = "name,use_yn,org_code,created_at")
        })
public class User extends BaseTimeEntity implements Persistable<String> {

    /**
     * 사용자계정
     */
    @Id
    @Column(length = 20, name = "user_id")
    private String userId;

    /**
     * 패스워드
     */
    @Column(length = 100, nullable = false)
    private String password;

    /**
     * 암호화 유무
     */
    @Column(length = 2)
    @Convert(converter = BooleanToYNConverter.class)
    private boolean salt;

    /**
     * 이름
     */
    @Column(name = "name", length = 30, nullable = false)
    private String name;


    /**
     * 부서코드
     */
    @Column(name = "org_code", nullable = false,length = 200)
    private String orgCode;

    @Column(name = "rank_cd")
    private String rankCd;

    @Column(name = "rank_nm")
    private String rankNm;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private String tel;

    /**
     * 계정 사용 여부
     */
    @Convert(converter = BooleanToYNConverter.class)
    @Column(name = "use_yn", length = 2, nullable = false)
    private boolean useYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_code", updatable = false, insertable = false)
    private Organization organization;

    @OneToMany(mappedBy = "user")
    private List<Agent> agents = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserHistory> userHistories = new ArrayList<>();


    @Builder
    public User(String userId, String password, String name, String orgCode, String rankCd, String rankNm, String email, String tel,boolean salt) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.orgCode = orgCode;
        this.rankCd = rankCd;
        this.rankNm = rankNm;
        this.email = email;
        this.tel = tel;
        this.salt = salt;
        this.useYn = true;
    }

    public void delete() {
        this.useYn = false;
    }


    @Override
    public String getId() {
        return userId;
    }

    @Override
    public boolean isNew() {
        return true;
    }

    public void changePassword(String pwd, boolean salt) {

        this.password = pwd;
        this.salt = salt;
    }
}

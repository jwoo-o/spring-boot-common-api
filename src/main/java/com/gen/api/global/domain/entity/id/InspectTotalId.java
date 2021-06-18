package com.gen.api.global.domain.entity.id;

/**
 * Created by dhwlsdn741@gmail.com
 * User: jinwoo
 * Date: 2021-03-08
 * Time: 오후 2:05
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class InspectTotalId implements Serializable {

    @Column(name = "org_code",length = 200)
    private String orgCode;
    @Column(name = "prd_code",length = 10)
    private String prdCode;
}

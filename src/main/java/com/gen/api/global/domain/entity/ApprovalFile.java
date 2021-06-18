package com.gen.api.global.domain.entity;

import com.gen.api.global.domain.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@ToString
@Entity(name = "tb_approval_file")
public class ApprovalFile extends BaseEntity {

    /**
     * 승인요청 정보 키
     */
    @Column(name = "request_id")
    private Long requestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", updatable = false, insertable = false)
    private RequestApproval requestApproval;


    /**
     * 파일이름
     */
    @Column(name = "file_name", nullable = false)
    private String fileName;

    /**
     * 업로드된  파일이름
     */
    @Column(name = "file_key", nullable = false)
    private String fileKey;

    /**
     * 파일 사이즈
     */
    @Column(name = "file_size", nullable = false)
    private Long fileSize;

    /**
     * 파일 사이즈
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 파일 해시
     */
    @Column(name = "file_hash", nullable = false)
    private String fileHash;

    @Builder
    public ApprovalFile(String fileName, String fileKey, Long fileSize, String fileHash, String filePath) {
        this.fileName = fileName;
        this.fileKey = fileKey;
        this.fileSize = fileSize;
        this.fileHash = fileHash;
        this.filePath = filePath;
    }
}

package com.Amigoscode.reqcache;

import javax.persistence.*;

@Entity
public class ReqCache {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reqcache_sequence")
    @SequenceGenerator(name = "reqcache_sequence", allocationSize = 1)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReqName() {
        return reqName;
    }

    public void setReqName(String reqName) {
        this.reqName = reqName;
    }

    public String getReqResult() {
        return reqResult;
    }

    public void setReqResult(String reqResult) {
        this.reqResult = reqResult;
    }

    private String reqName;
    private String reqResult;

}

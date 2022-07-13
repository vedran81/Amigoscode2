package com.Edu.reqcache;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReqCacheRepository extends JpaRepository<RequestCache, Long> {
    boolean existsByReqName(String reqName);

    RequestCache findByReqName(String reqName);

}
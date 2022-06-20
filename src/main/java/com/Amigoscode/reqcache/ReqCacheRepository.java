package com.Amigoscode.reqcache;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReqCacheRepository extends JpaRepository<ReqCache, Long> {
    boolean existsByReqName(String reqName);

    ReqCache findByReqName(String reqName);

}
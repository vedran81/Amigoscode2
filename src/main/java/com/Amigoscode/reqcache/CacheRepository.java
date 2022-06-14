package com.Amigoscode.reqcache;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CacheRepository extends JpaRepository<RequestCache, Long> {
    boolean existsByReqName(String reqName);

    RequestCache findByReqName(String reqName);

}
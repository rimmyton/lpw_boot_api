package com.loupan.boot.repository.centaec.map;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.loupan.boot.domain.centaec.map.ViewAreaMap;

public interface ViewAreaMapRepository extends JpaRepository<ViewAreaMap, Integer>, JpaSpecificationExecutor<ViewAreaMap> {

}

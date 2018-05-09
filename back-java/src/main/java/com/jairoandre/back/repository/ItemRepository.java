package com.jairoandre.back.repository;

import com.jairoandre.back.entity.ItemEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "item", path = "items")
public interface ItemRepository extends PagingAndSortingRepository<ItemEntity, Long> {

  List<ItemEntity> findByName(@Param("name") String name);

}

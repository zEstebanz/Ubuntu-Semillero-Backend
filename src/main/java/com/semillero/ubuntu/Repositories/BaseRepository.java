package com.semillero.ubuntu.Repositories;

import com.semillero.ubuntu.Entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface BaseRepository <E extends BaseEntity, ID extends Serializable> extends JpaRepository<E,ID> {
}

package net.minis.aa.repository;

import org.springframework.stereotype.Repository;

import net.minis.aa.domain.Application;
import net.minis.api.spring.data.BaseRepository;

@Repository
public interface ApplicationDao extends BaseRepository<Application, String> {

}

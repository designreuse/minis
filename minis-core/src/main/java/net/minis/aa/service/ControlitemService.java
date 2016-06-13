package net.minis.aa.service;

import java.util.List;

import net.minis.aa.domain.Controlitem;
import net.minis.api.spring.data.SearchCondition;

import org.springframework.data.domain.Page;

public interface ControlitemService {

    Controlitem saveControlitem(Controlitem controlitem);

    void deleteControlitem(String id);

    Page<Controlitem> searchControlitems(SearchCondition condition);

    long countAllControlitems();

    List<Controlitem> findAllControlitems();

    List<Controlitem> findControlitemsByRoleId(String roleId);

    Controlitem getControlitemById(String id);

}

package net.minis.aa.service.impl;

import java.util.List;

import net.minis.aa.domain.Controlitem;
import net.minis.aa.domain.ControlitemPermission;
import net.minis.aa.repository.ControlitemDao;
import net.minis.aa.repository.ControlitemPermissionDao;
import net.minis.aa.service.ControlitemService;
import net.minis.api.spring.data.SearchCondition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

@Service
public class ControlitemServiceImpl implements ControlitemService {

    @Autowired
    private ControlitemDao controlitemDao;

    @Autowired
    private ControlitemPermissionDao controlPermissionDao;

    @Override
    public Controlitem saveControlitem(Controlitem controlitem) {
        return controlitemDao.save(controlitem);
    }

    @Override
    public void deleteControlitem(String id) {
        controlitemDao.delete(id);
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Page<Controlitem> searchControlitems(SearchCondition condition) {
        Pageable pageable = condition.getPageable();
        Specification specification = condition.getSpecification();
        return controlitemDao.findAll(specification, pageable);
    }

    @Override
    public List<Controlitem> findAllControlitems() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return controlitemDao.findAll(sort);
    }

    @Override
    public long countAllControlitems() {
        return controlitemDao.count();
    }

    @Override
    public List<Controlitem> findControlitemsByRoleId(String roleId) {

        List<ControlitemPermission> permissions = controlPermissionDao.findByRoleId(roleId);

        List<Controlitem> controlitems = Lists.transform(permissions, new Function<ControlitemPermission, Controlitem>() {
            public Controlitem apply(ControlitemPermission input) {
                return input.getControlitem();
            }
        });

        return controlitems;
    }

    @Override
    public Controlitem getControlitemById(String id) {
        return (id == null) ? null : controlitemDao.findOne(id);
    }

}

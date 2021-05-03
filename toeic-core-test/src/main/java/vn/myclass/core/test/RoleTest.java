package vn.myclass.core.test;

import org.junit.jupiter.api.Test;
import vn.myclass.core.dao.RoleDao;
import vn.myclass.core.daoimpl.RoleDaoImpl;
import vn.myclass.core.persistence.entity.RoleEntity;

import java.util.ArrayList;
import java.util.List;

public class RoleTest {
    @Test
    public void checkFindAll(){
        RoleDao roleDao=new RoleDaoImpl();
        List<RoleEntity> list= roleDao.findAll();
    }

    @Test
    public void checkUpdateRole(){
        RoleDao roleDao=new RoleDaoImpl();
        RoleEntity roleEntity=new RoleEntity();
        roleEntity.setRoleID(2);
        roleEntity.setName("USER");
        roleDao.update(roleEntity);
    }

    @Test
    public void checkSaveRole(){
        RoleDao roleDao=new RoleDaoImpl();
        RoleEntity roleEntity=new RoleEntity();
        roleEntity.setRoleID(1);
        roleEntity.setName("ADMIN");
        RoleEntity roleEntity1=new RoleEntity();
        roleEntity1.setRoleID(2);
        roleEntity1.setName("USER");
        roleDao.update(roleEntity);
        roleDao.update(roleEntity1);
    }

    @Test void checkByID(){
        RoleDao roleDao=new RoleDaoImpl();
        RoleEntity entity=roleDao.findById(1);
    }
    @Test
    public void checkFindByProperty(){
        RoleDao roleDao= new RoleDaoImpl();
        String property= null;
        Object value= null;
        String sortExpression= null;
        String sortDirection = null;
        Object[] objects=roleDao.findByProperty(property,value,sortExpression,sortDirection);
    }

    @Test
    public void checkDelete(){
        List<Integer> listID=new ArrayList<>();
        listID.add(1);
        listID.add(2);
        RoleDao roleDao=new RoleDaoImpl();
        Integer count= roleDao.delete(listID);
    }
}

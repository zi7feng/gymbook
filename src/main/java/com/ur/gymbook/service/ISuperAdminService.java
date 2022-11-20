package com.ur.gymbook.service;

import com.ur.gymbook.model.Admin;
import com.ur.gymbook.model.SuperAdmin;

import java.util.List;

public interface ISuperAdminService {
    List<SuperAdmin> listSu();

    SuperAdmin findByNameAndPassword(String suUserName, String suUserPwd);

    List<Admin> listAdmin();

    void activatedStatusSwitch(Admin admin);
}

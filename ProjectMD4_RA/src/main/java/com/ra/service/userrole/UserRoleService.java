package com.ra.service.userrole;

import com.ra.model.dto.userrole.UserRoleDTO;
import java.util.List;

public interface UserRoleService {
    List<UserRoleDTO> findAll();
    UserRoleDTO save(UserRoleDTO userRoleDTO);
    boolean delete(Long userId, Long roleId);
    boolean assignRoleToUser(Long userId, Long roleId);
    boolean removeRoleFromUser(Long userId, Long roleId);
}

package com.ra.service.role;

import com.ra.model.dto.role.RoleDTO;
import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<RoleDTO> findAll();
    Optional<RoleDTO> findById(Long id);
    RoleDTO save(RoleDTO roleDTO);
    RoleDTO update(Long id, RoleDTO roleDTO);
    boolean delete(Long id);
}

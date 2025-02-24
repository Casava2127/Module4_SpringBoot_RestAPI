package com.ra.service.role.imp;

import com.ra.model.dto.role.RoleDTO;
import com.ra.model.entity.Role;
import com.ra.model.entity.RoleName;
import com.ra.repository.RoleRepository;
import com.ra.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    private RoleDTO convertToDTO(Role role) {
        return RoleDTO.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName().name())
                .build();
    }

    private Role convertToEntity(RoleDTO roleDTO) {
        return Role.builder()
                .roleId(roleDTO.getRoleId())
                .roleName(RoleName.valueOf(roleDTO.getRoleName()))
                .build();
    }

    @Override
    public List<RoleDTO> findAll() {
        return roleRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDTO> findById(Long id) {
        return roleRepository.findById(id)
                .map(this::convertToDTO);
    }

    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role role = convertToEntity(roleDTO);
        Role savedRole = roleRepository.save(role);
        return convertToDTO(savedRole);
    }

    @Override
    public RoleDTO update(Long id, RoleDTO roleDTO) {
        if (!roleRepository.existsById(id)) {
            return null;
        }
        Role role = convertToEntity(roleDTO);
        role.setRoleId(id);
        Role updatedRole = roleRepository.save(role);
        return convertToDTO(updatedRole);
    }

    @Override
    public boolean delete(Long id) {
        if (!roleRepository.existsById(id)) {
            return false;
        }
        roleRepository.deleteById(id);
        return true;
    }
}

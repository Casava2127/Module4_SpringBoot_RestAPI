package com.ra.service.userrole.imp;

import com.ra.model.dto.userrole.UserRoleDTO;
import com.ra.model.entity.Role;
import com.ra.model.entity.User;
import com.ra.model.entity.UserRole;
import com.ra.model.entity.UserRoleId;
import com.ra.repository.RoleRepository;
import com.ra.repository.UserRepository;
import com.ra.repository.UserRoleRepository;
import com.ra.service.userrole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private UserRoleDTO convertToDTO(UserRole userRole) {
        return UserRoleDTO.builder()
                .userId(userRole.getUser().getUserId())
                .roleId(userRole.getRole().getRoleId())
                .build();
    }

    @Override
    public List<UserRoleDTO> findAll() {
        return userRoleRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserRoleDTO save(UserRoleDTO userRoleDTO) {
        User user = userRepository.findById(userRoleDTO.getUserId()).orElse(null);
        Role role = roleRepository.findById(userRoleDTO.getRoleId()).orElse(null);

        if (user == null || role == null) {
            return null;
        }

        UserRole userRole = UserRole.builder()
                .id(new UserRoleId(user.getUserId(), role.getRoleId()))
                .user(user)
                .role(role)
                .build();

        UserRole savedUserRole = userRoleRepository.save(userRole);
        return convertToDTO(savedUserRole);
    }

    @Override
    public boolean delete(Long userId, Long roleId) {
        UserRoleId id = new UserRoleId(userId, roleId);
        if (!userRoleRepository.existsById(id)) {
            return false;
        }
        userRoleRepository.deleteById(id);
        return true;
    }
}

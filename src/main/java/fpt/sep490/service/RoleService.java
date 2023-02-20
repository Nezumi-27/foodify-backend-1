package fpt.sep490.service;

import fpt.sep490.entity.Role;
import fpt.sep490.payload.RoleDto;

import java.util.List;

public interface RoleService {
    RoleDto createRole(RoleDto roleDto);

    List<RoleDto> getAllRoles();

    RoleDto getRoleById(Long roleId);

    RoleDto updateRoleById(Long roleId, RoleDto roleDto);

    void deleteRole(Long roleId);
}

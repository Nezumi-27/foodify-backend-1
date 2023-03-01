package fpt.sep490.service.impl;

import fpt.sep490.entity.Role;
import fpt.sep490.exception.ResourceNotFoundException;
import fpt.sep490.payload.RoleDto;
import fpt.sep490.repository.RoleRepository;
import fpt.sep490.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;
    private ModelMapper mapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper mapper) {
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = mapper.map(roleDto, Role.class);
        return mapper.map(roleRepository.save(role), RoleDto.class);
    }

    @Override
    public List<RoleDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(role -> mapper.map(role, RoleDto.class)).collect(Collectors.toList());
    }

    @Override
    public RoleDto getRoleById(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(()-> new ResourceNotFoundException("Role", "id", roleId));
        return mapper.map(role, RoleDto.class);
    }

    @Override
    public RoleDto updateRoleById(Long roleId, RoleDto roleDto) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(()-> new ResourceNotFoundException("Role", "id", roleId));
        role.setName(roleDto.getRoleName());
        Role updatedRole = roleRepository.save(role);
        return mapper.map(updatedRole, RoleDto.class);
    }

    @Override
    public void deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId)
                .orElseThrow(()-> new ResourceNotFoundException("Role", "id", roleId));
        roleRepository.delete(role);
    }
}

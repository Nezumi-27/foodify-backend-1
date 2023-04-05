package fpt.sep490;

import fpt.sep490.entity.Role;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.payload.RoleDto;
import fpt.sep490.repository.RoleRepository;
import fpt.sep490.service.RoleService;
import fpt.sep490.service.impl.RoleServiceImpl;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleServiceTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Spy
    private ModelMapper mapper;

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void stage1_testCreateRole(){
        RoleDto roleDto = new RoleDto(1L, "role_test");
        Role role = mapper.map(roleDto, Role.class);

        Mockito.when(roleRepository.save(Mockito.any(Role.class))).thenReturn(role);

        RoleDto newRole = roleService.createRole(roleDto);

        Assert.assertNotNull(newRole);
        Assert.assertEquals("role_test", newRole.getRoleName());
    }

    @Test
    public void stage2_testGetAllRole(){
        List<RoleDto> roleDtoList = new ArrayList<>();
        roleDtoList.add(new RoleDto(1L, "role1"));
        roleDtoList.add(new RoleDto(2L, "role2"));
        roleDtoList.add(new RoleDto(3L, "role3"));
        List<Role> roles = roleDtoList.stream().map(roleDto -> mapper.map(roleDto, Role.class)).collect(Collectors.toList());

        Mockito.when(roleRepository.findAll()).thenReturn(roles);

        List<RoleDto> roleDtos = roleService.getAllRoles();

        Assert.assertNotNull(roleDtos);
    }

    @Test
    public void stage3_testGetRoleById(){
        Role role = new Role(1L, "role_test");

        Mockito.when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        RoleDto roleDto = roleService.getRoleById(1L);

        Assert.assertNotNull(roleDto);
        Assert.assertEquals("role_test", roleDto.getRoleName());
    }

    @Test
    public void stage4_testUpdateRole(){
        Role role = new Role(1L, "role_test");
        Role updateRole = new Role(1L, "role_update");
        RoleDto updateDto = mapper.map(updateRole, RoleDto.class);

        Mockito.when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        Mockito.when(roleRepository.save(Mockito.any(Role.class))).thenReturn(updateRole);

        RoleDto roleUpdated = roleService.updateRoleById(1L, updateDto);

        Assert.assertNotNull(roleUpdated);
        Assert.assertEquals("role_update", roleUpdated.getRoleName());
    }

    @Test
    public void stage5_testDeleteRole(){
        Role role = new Role(1L, "role_test");

        Mockito.when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        roleService.deleteRole(1L);

        Mockito.verify(roleRepository, Mockito.times(1)).delete(role);
    }
}

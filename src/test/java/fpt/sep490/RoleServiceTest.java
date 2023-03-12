package fpt.sep490;

import fpt.sep490.entity.Role;
import fpt.sep490.exception.FoodifyAPIException;
import fpt.sep490.payload.RoleDto;
import fpt.sep490.repository.RoleRepository;
import fpt.sep490.service.RoleService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private RoleRepository roleRepository;

    public RoleServiceTest(){

    }

    @Test
    public void stage1_testCreateRole(){
        Role role = new Role();
        role.setId(5L);
        role.setName("ROLE_TEST");

        RoleDto roleDto = mapper.map(role, RoleDto.class);

        RoleDto createRoleDto = roleService.createRole(roleDto);
        Role createdRole = mapper.map(roleDto, Role.class);

        Assert.assertNotNull(createdRole);
        Assert.assertEquals("ROLE_TEST", createdRole.getName());
    }

    @Test
    public void stage2_testGetAllRoles(){
        List<RoleDto> roleDtos = roleService.getAllRoles();
        List<Role> roles = roleDtos.stream().map(roleDto -> mapper.map(roleDto, Role.class)).collect(Collectors.toList());
        Assert.assertNotNull(roles);
        Assert.assertFalse(roles.isEmpty());
    }

    @Test
    public void stage3_testGetRoleById(){
        Role role = roleRepository.findByName("ROLE_TEST")
                .orElseThrow(() -> new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Role not found with name: " + "ROLE_TEST"));

        RoleDto roleGetDto = roleService.getRoleById(role.getId());
        Role roleGet = mapper.map(roleGetDto, Role.class);

        Assert.assertNotNull(roleGet);
        Assert.assertEquals("ROLE_TEST", roleGet.getName());
    }

    @Test
    public void stage4_testUpdateRoleById(){
        Role role = roleRepository.findByName("ROLE_TEST")
                .orElseThrow(() -> new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Role not found with name: " + "ROLE_TEST"));
        role.setName("ROLE_TEST_UPDATE");

        RoleDto roleUpdateDto = roleService.updateRoleById(role.getId(), mapper.map(role, RoleDto.class));
        Role roleUpdate = mapper.map(roleUpdateDto, Role.class);

        Assert.assertNotNull(roleUpdate);
        Assert.assertEquals("ROLE_TEST_UPDATE", roleUpdate.getName());
    }

    @Test
    public void stage5_testDeleteRole(){
        Role roleToDelete = roleRepository.findByName("ROLE_TEST_UPDATE")
                .orElseThrow(() -> new FoodifyAPIException(HttpStatus.BAD_REQUEST, "Role not found with name: " + "ROLE_TEST_UPDATE"));

        roleService.deleteRole(roleToDelete.getId());

        Optional<Role> deletedRole = roleRepository.findByName("ROLE_TEST_UPDATE");
        Assert.assertTrue(deletedRole.isEmpty());
    }

}

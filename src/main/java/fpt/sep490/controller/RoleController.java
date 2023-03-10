package fpt.sep490.controller;

import fpt.sep490.payload.RoleDto;
import fpt.sep490.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("CRUD Apis for Role Resource")
@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("Create Role")
    @PostMapping
    public ResponseEntity<RoleDto> createRole(RoleDto roleDto){
        return new ResponseEntity<>(roleService.createRole(roleDto), HttpStatus.CREATED);
    }

    @ApiOperation("Get All Roles")
    @GetMapping
    public ResponseEntity<List<RoleDto>> getAllRoles(){
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @ApiOperation("Get Role by Id")
    @GetMapping("/{id}")
    public ResponseEntity<RoleDto> getRoleById(@PathVariable(value = "id") Long roleId){
        return ResponseEntity.ok(roleService.getRoleById(roleId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("Update Role by Id")
    @PutMapping("/{id}")
    public ResponseEntity<RoleDto> updateRole(@PathVariable(value = "id") Long roleId,
                                              @RequestBody RoleDto roleDto){
        return ResponseEntity.ok(roleService.updateRoleById(roleId, roleDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation("Delete Role by Id")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRole(@PathVariable(value = "id") Long roleId){
        roleService.deleteRole(roleId);
        return ResponseEntity.ok("Role deleted successfully");
    }
}

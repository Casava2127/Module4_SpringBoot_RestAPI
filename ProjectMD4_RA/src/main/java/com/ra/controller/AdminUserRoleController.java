package com.ra.controller;



import com.ra.service.userrole.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/users")
public class AdminUserRoleController {

    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/{userId}/role/{roleId}")
    public ResponseEntity<String> assignRole(@PathVariable Long userId, @PathVariable Long roleId) {
        boolean assigned = userRoleService.assignRoleToUser(userId, roleId);
        if (assigned) {
            return ResponseEntity.ok("Role assigned successfully.");
        }
        return ResponseEntity.badRequest().body("Failed to assign role.");
    }

    @DeleteMapping("/{userId}/role/{roleId}")
    public ResponseEntity<String> removeRole(@PathVariable Long userId, @PathVariable Long roleId) {
        boolean removed = userRoleService.removeRoleFromUser(userId, roleId);
        if (removed) {
            return ResponseEntity.ok("Role removed successfully.");
        }
        return ResponseEntity.badRequest().body("Failed to remove role.");
    }
}

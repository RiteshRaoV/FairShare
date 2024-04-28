package splitwise.project.splitwise.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import splitwise.project.splitwise.DTO.GroupDTO;
import splitwise.project.splitwise.Model.Group;
import splitwise.project.splitwise.Services.GroupService;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<Group> createGroup(@RequestBody GroupDTO groupDTO){
        Group group=groupService.createGroup(groupDTO);
        if(group!=null){
            return ResponseEntity.ok(group);
        }return ResponseEntity.notFound().build();
    }
}

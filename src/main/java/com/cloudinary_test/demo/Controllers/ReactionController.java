package com.cloudinary_test.demo.Controllers;

import com.cloudinary_test.demo.Entities.Enums.ReactionType;
import com.cloudinary_test.demo.Entities.Reaction;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Services.ReactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/images")

public class ReactionController extends BaseController<Reaction> {

    private final ReactionService reactionService;
    public ReactionController(ReactionService reactionService){
        super(reactionService);
        this.reactionService = reactionService;
    }
    @PostMapping("/{id}/reaction")
    public ResponseEntity<Void> reactToImage(@PathVariable Long id, @RequestParam ReactionType type, @AuthenticationPrincipal User user){
        reactionService.reactToImage(id, user, type);
        return ResponseEntity.ok().build();
    }
}

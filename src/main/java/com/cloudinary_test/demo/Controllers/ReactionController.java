package com.cloudinary_test.demo.Controllers;

import com.cloudinary_test.demo.Entities.Enums.ReactionType;
import com.cloudinary_test.demo.Entities.Reaction;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Services.ReactionService;
import com.cloudinary_test.demo.Utils.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reaction")

public class ReactionController extends BaseController<Reaction> {

    private final ReactionService reactionService;
    public ReactionController(ReactionService reactionService){
        super(reactionService);
        this.reactionService = reactionService;
    }
    @PostMapping("/images/{id}")
    public ResponseEntity<Void> reactToImage(@PathVariable Long id, @RequestParam("type") int typeValue, @AuthenticationPrincipal CustomUserDetails userDetails){
        ReactionType type = ReactionType.fromInt(typeValue);
        User user = userDetails.getUser();
        reactionService.reactToImage(id, user, type);
        return ResponseEntity.ok().build();
    }


}

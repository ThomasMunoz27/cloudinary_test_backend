package com.cloudinary_test.demo.Controllers;

import com.cloudinary_test.demo.DTOs.ReactionDTOResponse;
import com.cloudinary_test.demo.Entities.Enums.ReactionType;
import com.cloudinary_test.demo.Entities.Reaction;
import com.cloudinary_test.demo.Entities.User;
import com.cloudinary_test.demo.Services.ReactionService;
import com.cloudinary_test.demo.Utils.CustomUserDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/reaction")

public class ReactionController extends BaseController<Reaction> {

    private final ReactionService reactionService;
    public ReactionController(ReactionService reactionService){
        super(reactionService);
        this.reactionService = reactionService;
    }
    @PostMapping("/images/{id}")
    public ResponseEntity<?> reactToImage(@PathVariable Long id, @RequestParam("type") int typeValue, @AuthenticationPrincipal CustomUserDetails userDetails){
        try{
            ReactionType type = ReactionType.fromInt(typeValue);
            User user = userDetails.getUser();

            //se realiza la reaccion
            reactionService.reactToImage(id, user, type);

            //obtener la reaccion actual
            Optional<Reaction> reactionOptional = reactionService.getReactionByUserAndImage(user.getId(), id);


            int likes = reactionService.countReactionByType(id, ReactionType.LIKE);
            int dislikes = reactionService.countReactionByType(id, ReactionType.DISKLIKE);

            if(reactionOptional.isEmpty()){
                ReactionDTOResponse dto = new ReactionDTOResponse();
                dto.setId(null);
                dto.setReactionType(null);
                dto.setLikes(likes);
                dto.setDislikes(dislikes);
                return ResponseEntity.ok(dto);
            }

            ReactionDTOResponse dto = new ReactionDTOResponse(reactionOptional.get(), likes, dislikes);
            return ResponseEntity.ok(dto);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Ocurrió un error inesperado al reaccionar."));
            }
        }
    }

   /*
   @GetMapping("/user/image/{imageId}")
    public ResponseEntity<?> getUserReactionToImage(@PathVariable Long imageId,
                                                    @AuthenticationPrincipal CustomUserDetails userDetails){
        try{
            Long userId = userDetails.getUser().getId();
            Optional<Reaction> reactionOpt = reactionService.getReactionByUserAndImage(userId, imageId);

            if(reactionOpt.isPresent()){
                return ResponseEntity.ok(new ReactionDTOResponse(reactionOpt.get()));
            }else {
                return ResponseEntity.ok().body(null);
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "Ocurrio un error al obtener la reaccion del usuario"));
        }
        */





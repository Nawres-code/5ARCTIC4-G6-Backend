package com.Parking.GestionParking.controller;

import com.Parking.GestionParking.entities.Comments;
import com.Parking.GestionParking.entities.Poste;
import com.Parking.GestionParking.services.IGestionComments;
import com.Parking.GestionParking.services.IGestionPoste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@PreAuthorize("hasRole('SimpleCLient')")
@RequestMapping("/comments")
@CrossOrigin(origins = "http://localhost:4200")
public class CommnetsControllerImpl {
    @Autowired
    IGestionComments gComments ;
    @Autowired
    IGestionPoste gPoste;

    @GetMapping("/getall")
    public List<Comments> getAll(){
        return gComments.retrieveAllComment();
    }

    @PostMapping("/addComments")
    public Comments addComments(@RequestBody Comments comments){
        return gComments.addComment(comments);
    }

    @GetMapping("/getCommentsId/{idComm}")
    public Comments getCommentsId(@PathVariable("idComm") Integer idComm){
        return gComments.retrieveComment(idComm);
    }
    @PutMapping("/updatecomments{idComm}")
    public Comments updatecomments(@PathVariable Integer idComm ,@RequestBody Comments comments){
        comments.setIdComm(idComm);
        return gComments.updateComment(comments);
    }
    
    @DeleteMapping("deleteId/{idComm}")
    public void delete(@PathVariable("idComm") Integer idComm){
        gComments.removeComment(idComm);
    }

    @PostMapping("/assignCommentToPost/{postId}")
    public Comments assignCommentToPost(@PathVariable("postId") Integer postId, @RequestBody Comments comment) {
        Poste poste = gPoste.retrievePoste(postId);
        if (poste == null) {
            return null;
        }
        comment.setPoste(poste);
        return gComments.addComment(comment);
    }

}

package com.Parking.GestionParking.services;

import com.Parking.GestionParking.entities.Comments;
import com.Parking.GestionParking.entities.Poste;
import com.Parking.GestionParking.repository.ICommentRepository;
import com.Parking.GestionParking.repository.IPosteRepository; // Import de IPosteRepository
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class GestionCommentsImpl implements IGestionComments {
    @Autowired
    ICommentRepository commentRepo;

    @Autowired // Injection de IPosteRepository
    IPosteRepository posteRepo;

    private final List<String> motsSensibles = Arrays.asList("chbinou", "yosser");

    @Override
    public Comments addComment(Comments comments) {
        if (contientMotsSensibles(comments.getContent()) || contientMotsSensibles(comments.getDescription())) {
            throw new IllegalArgumentException("Le contenu ou la description contient des mots sensibles.");
        }

        // Fetch the Poste entity from the database
        Optional<Poste> optionalPoste = posteRepo.findById(comments.getPoste().getIdPoste());
        if (optionalPoste.isPresent()) {
            // If the Poste exists, associate it with the Comments entity
            comments.setPoste(optionalPoste.get());
            // Save the Comments entity
            return commentRepo.save(comments);
        } else {
            // Handle the case when the Poste doesn't exist
            throw new EntityNotFoundException("Poste with ID " + comments.getPoste().getIdPoste() + " not found");
        }
    }

    @Override
    public List<Comments> retrieveAllComment() {
        return (List<Comments>) commentRepo.findAll();
    }

    @Override
    public Comments updateComment(Comments comments) {
        if (contientMotsSensibles(comments.getContent()) || contientMotsSensibles(comments.getDescription())) {
            throw new IllegalArgumentException("Le contenu ou la description contient des mots sensibles.");
        }
        return commentRepo.save(comments);
    }

    @Override
    public Comments retrieveComment(Integer idComm) {
        return commentRepo.findById(idComm).orElse(null);
    }

    @Override
    public void removeComment(Integer idComm) {
        commentRepo.deleteById(idComm);
    }

    @Override
    public Comments assignCommentToPost(Integer postId, Comments comment) {
        Poste poste = posteRepo.findById(postId).orElse(null);
        if (poste == null) {
            return null;
        }
        comment.setPoste(poste);
        return addComment(comment);
    }


    private boolean contientMotsSensibles(String texte) {
        for (String mot : motsSensibles) {
            if (texte.toLowerCase().contains(mot.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}

package com.Parking.GestionParking.services;

import com.Parking.GestionParking.entities.Comments;

import java.util.List;

public interface IGestionComments {
    Comments addComment(Comments comments);
    List<Comments> retrieveAllComment();
    Comments updateComment(Comments comments);
    Comments retrieveComment (Integer idComm);
    void removeComment (Integer idComm);

    public Comments assignCommentToPost(Integer postId, Comments comment);


    //  List<Comments> retrieveAllCommentsOrderedByDate();
}

package fr.polytech.tetris.model;

/**
 * Created by REZIGA on 15/05/2017.
 */
public class IdentifierBoolean {
    private Long identifierPiece;
    private boolean caseFulled;

    public IdentifierBoolean(Long identifierPiece, boolean caseFulled) {
        if(identifierPiece == null) this.identifierPiece = identifierPiece;
        this.caseFulled = caseFulled;
    }

    public Long getIdentifierPiece() {
        return identifierPiece;
    }

    public boolean isCaseEmpty() {
        return !caseFulled;
    }
}

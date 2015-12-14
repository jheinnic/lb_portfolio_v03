/**
 */
package info.jchein.sandbox.app.crossword.resources.xw.impl;

import info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail;
import info.jchein.sandbox.app.crossword.resources.xw.XwPackage;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Published Ticket Detail</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PublishedTicketDetailImpl#getBoardContent <em>Board Content</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PublishedTicketDetailImpl#getBonusWord <em>Bonus Word</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PublishedTicketDetailImpl#isTwentySpotIncluded <em>Twenty Spot Included</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PublishedTicketDetailImpl extends MinimalEObjectImpl.Container implements PublishedTicketDetail {
    /**
     * The default value of the '{@link #getBoardContent() <em>Board Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBoardContent()
     * @generated
     * @ordered
     */
    protected static final String BOARD_CONTENT_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBoardContent() <em>Board Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBoardContent()
     * @generated
     * @ordered
     */
    protected String boardContent = BOARD_CONTENT_EDEFAULT;

    /**
     * The default value of the '{@link #getBonusWord() <em>Bonus Word</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBonusWord()
     * @generated
     * @ordered
     */
    protected static final String BONUS_WORD_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getBonusWord() <em>Bonus Word</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBonusWord()
     * @generated
     * @ordered
     */
    protected String bonusWord = BONUS_WORD_EDEFAULT;

    /**
     * The default value of the '{@link #isTwentySpotIncluded() <em>Twenty Spot Included</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isTwentySpotIncluded()
     * @generated
     * @ordered
     */
    protected static final boolean TWENTY_SPOT_INCLUDED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isTwentySpotIncluded() <em>Twenty Spot Included</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isTwentySpotIncluded()
     * @generated
     * @ordered
     */
    protected boolean twentySpotIncluded = TWENTY_SPOT_INCLUDED_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PublishedTicketDetailImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return XwPackage.Literals.PUBLISHED_TICKET_DETAIL;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getBoardContent() {
        return boardContent;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getBonusWord() {
        return bonusWord;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isTwentySpotIncluded() {
        return twentySpotIncluded;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case XwPackage.PUBLISHED_TICKET_DETAIL__BOARD_CONTENT:
                return getBoardContent();
            case XwPackage.PUBLISHED_TICKET_DETAIL__BONUS_WORD:
                return getBonusWord();
            case XwPackage.PUBLISHED_TICKET_DETAIL__TWENTY_SPOT_INCLUDED:
                return isTwentySpotIncluded();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case XwPackage.PUBLISHED_TICKET_DETAIL__BOARD_CONTENT:
                return BOARD_CONTENT_EDEFAULT == null ? boardContent != null : !BOARD_CONTENT_EDEFAULT.equals(boardContent);
            case XwPackage.PUBLISHED_TICKET_DETAIL__BONUS_WORD:
                return BONUS_WORD_EDEFAULT == null ? bonusWord != null : !BONUS_WORD_EDEFAULT.equals(bonusWord);
            case XwPackage.PUBLISHED_TICKET_DETAIL__TWENTY_SPOT_INCLUDED:
                return twentySpotIncluded != TWENTY_SPOT_INCLUDED_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString() {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (boardContent: ");
        result.append(boardContent);
        result.append(", bonusWord: ");
        result.append(bonusWord);
        result.append(", twentySpotIncluded: ");
        result.append(twentySpotIncluded);
        result.append(')');
        return result.toString();
    }

} //PublishedTicketDetailImpl

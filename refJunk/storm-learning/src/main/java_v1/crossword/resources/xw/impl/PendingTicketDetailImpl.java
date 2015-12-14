/**
 */
package info.jchein.sandbox.app.crossword.resources.xw.impl;

import info.jchein.sandbox.app.crossword.resources.xw.PendingTicketDetail;
import info.jchein.sandbox.app.crossword.resources.xw.XwPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Pending Ticket Detail</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PendingTicketDetailImpl#getBoardContent <em>Board Content</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PendingTicketDetailImpl#getBonusWord <em>Bonus Word</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PendingTicketDetailImpl#isTwentySpotIncluded <em>Twenty Spot Included</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PendingTicketDetailImpl#getAllWords <em>All Words</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PendingTicketDetailImpl#getBasicWords <em>Basic Words</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PendingTicketDetailImpl#getTripleWords <em>Triple Words</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PendingTicketDetailImpl#isPublishable <em>Publishable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PendingTicketDetailImpl extends MinimalEObjectImpl.Container implements PendingTicketDetail {
    /**
     * The default value of the '{@link #getBoardContent() <em>Board Content</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBoardContent()
     * @generated
     * @ordered
     */
    protected static final String BOARD_CONTENT_EDEFAULT = "_________________________________________________________________________________________________________________________";

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
     * This is true if the Bonus Word attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean bonusWordESet;

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
     * This is true if the Twenty Spot Included attribute has been set.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    protected boolean twentySpotIncludedESet;

    /**
     * The default value of the '{@link #isPublishable() <em>Publishable</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isPublishable()
     * @generated
     * @ordered
     */
    protected static final boolean PUBLISHABLE_EDEFAULT = false;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PendingTicketDetailImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return XwPackage.Literals.PENDING_TICKET_DETAIL;
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
    public void setBoardContent(String newBoardContent) {
        String oldBoardContent = boardContent;
        boardContent = newBoardContent;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, XwPackage.PENDING_TICKET_DETAIL__BOARD_CONTENT, oldBoardContent, boardContent));
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
    public void setBonusWord(String newBonusWord) {
        String oldBonusWord = bonusWord;
        bonusWord = newBonusWord;
        boolean oldBonusWordESet = bonusWordESet;
        bonusWordESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, XwPackage.PENDING_TICKET_DETAIL__BONUS_WORD, oldBonusWord, bonusWord, !oldBonusWordESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetBonusWord() {
        String oldBonusWord = bonusWord;
        boolean oldBonusWordESet = bonusWordESet;
        bonusWord = BONUS_WORD_EDEFAULT;
        bonusWordESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, XwPackage.PENDING_TICKET_DETAIL__BONUS_WORD, oldBonusWord, BONUS_WORD_EDEFAULT, oldBonusWordESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetBonusWord() {
        return bonusWordESet;
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
    public void setTwentySpotIncluded(boolean newTwentySpotIncluded) {
        boolean oldTwentySpotIncluded = twentySpotIncluded;
        twentySpotIncluded = newTwentySpotIncluded;
        boolean oldTwentySpotIncludedESet = twentySpotIncludedESet;
        twentySpotIncludedESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, XwPackage.PENDING_TICKET_DETAIL__TWENTY_SPOT_INCLUDED, oldTwentySpotIncluded, twentySpotIncluded, !oldTwentySpotIncludedESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void unsetTwentySpotIncluded() {
        boolean oldTwentySpotIncluded = twentySpotIncluded;
        boolean oldTwentySpotIncludedESet = twentySpotIncludedESet;
        twentySpotIncluded = TWENTY_SPOT_INCLUDED_EDEFAULT;
        twentySpotIncludedESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.UNSET, XwPackage.PENDING_TICKET_DETAIL__TWENTY_SPOT_INCLUDED, oldTwentySpotIncluded, TWENTY_SPOT_INCLUDED_EDEFAULT, oldTwentySpotIncludedESet));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isSetTwentySpotIncluded() {
        return twentySpotIncludedESet;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getAllWords() {
        // TODO: implement this method to return the 'All Words' attribute list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getBasicWords() {
        // TODO: implement this method to return the 'Basic Words' attribute list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<String> getTripleWords() {
        // TODO: implement this method to return the 'Triple Words' attribute list
        // Ensure that you remove @generated or mark it @generated NOT
        // The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting
        // so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.EcoreEList should be used.
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isPublishable() {
        // TODO: implement this method to return the 'Publishable' attribute
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case XwPackage.PENDING_TICKET_DETAIL__BOARD_CONTENT:
                return getBoardContent();
            case XwPackage.PENDING_TICKET_DETAIL__BONUS_WORD:
                return getBonusWord();
            case XwPackage.PENDING_TICKET_DETAIL__TWENTY_SPOT_INCLUDED:
                return isTwentySpotIncluded();
            case XwPackage.PENDING_TICKET_DETAIL__ALL_WORDS:
                return getAllWords();
            case XwPackage.PENDING_TICKET_DETAIL__BASIC_WORDS:
                return getBasicWords();
            case XwPackage.PENDING_TICKET_DETAIL__TRIPLE_WORDS:
                return getTripleWords();
            case XwPackage.PENDING_TICKET_DETAIL__PUBLISHABLE:
                return isPublishable();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eSet(int featureID, Object newValue) {
        switch (featureID) {
            case XwPackage.PENDING_TICKET_DETAIL__BOARD_CONTENT:
                setBoardContent((String)newValue);
                return;
            case XwPackage.PENDING_TICKET_DETAIL__BONUS_WORD:
                setBonusWord((String)newValue);
                return;
            case XwPackage.PENDING_TICKET_DETAIL__TWENTY_SPOT_INCLUDED:
                setTwentySpotIncluded((Boolean)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID) {
        switch (featureID) {
            case XwPackage.PENDING_TICKET_DETAIL__BOARD_CONTENT:
                setBoardContent(BOARD_CONTENT_EDEFAULT);
                return;
            case XwPackage.PENDING_TICKET_DETAIL__BONUS_WORD:
                unsetBonusWord();
                return;
            case XwPackage.PENDING_TICKET_DETAIL__TWENTY_SPOT_INCLUDED:
                unsetTwentySpotIncluded();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID) {
        switch (featureID) {
            case XwPackage.PENDING_TICKET_DETAIL__BOARD_CONTENT:
                return BOARD_CONTENT_EDEFAULT == null ? boardContent != null : !BOARD_CONTENT_EDEFAULT.equals(boardContent);
            case XwPackage.PENDING_TICKET_DETAIL__BONUS_WORD:
                return isSetBonusWord();
            case XwPackage.PENDING_TICKET_DETAIL__TWENTY_SPOT_INCLUDED:
                return isSetTwentySpotIncluded();
            case XwPackage.PENDING_TICKET_DETAIL__ALL_WORDS:
                return !getAllWords().isEmpty();
            case XwPackage.PENDING_TICKET_DETAIL__BASIC_WORDS:
                return !getBasicWords().isEmpty();
            case XwPackage.PENDING_TICKET_DETAIL__TRIPLE_WORDS:
                return !getTripleWords().isEmpty();
            case XwPackage.PENDING_TICKET_DETAIL__PUBLISHABLE:
                return isPublishable() != PUBLISHABLE_EDEFAULT;
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
        if (bonusWordESet) result.append(bonusWord); else result.append("<unset>");
        result.append(", twentySpotIncluded: ");
        if (twentySpotIncludedESet) result.append(twentySpotIncluded); else result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} //PendingTicketDetailImpl

/**
 */
package info.jchein.sandbox.app.crossword.resources.xw.impl;

import info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage;
import info.jchein.sandbox.app.crossword.resources.xw.BonusValue;
import info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail;
import info.jchein.sandbox.app.crossword.resources.xw.ResolvedTicket;
import info.jchein.sandbox.app.crossword.resources.xw.XwPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resolved Ticket</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.ResolvedTicketImpl#getFixedDetails <em>Fixed Details</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.ResolvedTicketImpl#getBoardContent <em>Board Content</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.ResolvedTicketImpl#getBonusWord <em>Bonus Word</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.ResolvedTicketImpl#isTwentySpotIncluded <em>Twenty Spot Included</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.ResolvedTicketImpl#getLetterPool <em>Letter Pool</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.ResolvedTicketImpl#isTwentySpotPaid <em>Twenty Spot Paid</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.ResolvedTicketImpl#getBonusValue <em>Bonus Value</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.ResolvedTicketImpl#getPayoutTier <em>Payout Tier</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.ResolvedTicketImpl#getBoardCoverage <em>Board Coverage</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResolvedTicketImpl extends MinimalEObjectImpl.Container implements ResolvedTicket {
    /**
     * The cached value of the '{@link #getFixedDetails() <em>Fixed Details</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFixedDetails()
     * @generated
     * @ordered
     */
    protected PublishedTicketDetail fixedDetails;

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
     * The default value of the '{@link #getBonusWord() <em>Bonus Word</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBonusWord()
     * @generated
     * @ordered
     */
    protected static final String BONUS_WORD_EDEFAULT = null;

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
     * The default value of the '{@link #getLetterPool() <em>Letter Pool</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLetterPool()
     * @generated
     * @ordered
     */
    protected static final String LETTER_POOL_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getLetterPool() <em>Letter Pool</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLetterPool()
     * @generated
     * @ordered
     */
    protected String letterPool = LETTER_POOL_EDEFAULT;

    /**
     * The default value of the '{@link #isTwentySpotPaid() <em>Twenty Spot Paid</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isTwentySpotPaid()
     * @generated
     * @ordered
     */
    protected static final boolean TWENTY_SPOT_PAID_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isTwentySpotPaid() <em>Twenty Spot Paid</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isTwentySpotPaid()
     * @generated
     * @ordered
     */
    protected boolean twentySpotPaid = TWENTY_SPOT_PAID_EDEFAULT;

    /**
     * The default value of the '{@link #getBonusValue() <em>Bonus Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBonusValue()
     * @generated
     * @ordered
     */
    protected static final BonusValue BONUS_VALUE_EDEFAULT = BonusValue.FOUR;

    /**
     * The cached value of the '{@link #getBonusValue() <em>Bonus Value</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBonusValue()
     * @generated
     * @ordered
     */
    protected BonusValue bonusValue = BONUS_VALUE_EDEFAULT;

    /**
     * The default value of the '{@link #getPayoutTier() <em>Payout Tier</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPayoutTier()
     * @generated
     * @ordered
     */
    protected static final int PAYOUT_TIER_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getPayoutTier() <em>Payout Tier</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getPayoutTier()
     * @generated
     * @ordered
     */
    protected int payoutTier = PAYOUT_TIER_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected ResolvedTicketImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return XwPackage.Literals.RESOLVED_TICKET;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PublishedTicketDetail getFixedDetails() {
        return fixedDetails;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetFixedDetails(PublishedTicketDetail newFixedDetails, NotificationChain msgs) {
        PublishedTicketDetail oldFixedDetails = fixedDetails;
        fixedDetails = newFixedDetails;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XwPackage.RESOLVED_TICKET__FIXED_DETAILS, oldFixedDetails, newFixedDetails);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getBoardContent() {
        // TODO: implement this method to return the 'Board Content' attribute
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getBonusWord() {
        // TODO: implement this method to return the 'Bonus Word' attribute
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isTwentySpotIncluded() {
        // TODO: implement this method to return the 'Twenty Spot Included' attribute
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getLetterPool() {
        return letterPool;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isTwentySpotPaid() {
        return twentySpotPaid;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BonusValue getBonusValue() {
        return bonusValue;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getPayoutTier() {
        return payoutTier;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public BoardCoverage getBoardCoverage() {
        // TODO: implement this method to return the 'Board Coverage' containment reference
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetBoardCoverage(BoardCoverage newBoardCoverage, NotificationChain msgs) {
        // TODO: implement this method to set the contained 'Board Coverage' containment reference
        // -> this method is automatically invoked to keep the containment relationship in synch
        // -> do not modify other features
        // -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)
        // Ensure that you remove @generated or mark it @generated NOT
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case XwPackage.RESOLVED_TICKET__FIXED_DETAILS:
                return basicSetFixedDetails(null, msgs);
            case XwPackage.RESOLVED_TICKET__BOARD_COVERAGE:
                return basicSetBoardCoverage(null, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType) {
        switch (featureID) {
            case XwPackage.RESOLVED_TICKET__FIXED_DETAILS:
                return getFixedDetails();
            case XwPackage.RESOLVED_TICKET__BOARD_CONTENT:
                return getBoardContent();
            case XwPackage.RESOLVED_TICKET__BONUS_WORD:
                return getBonusWord();
            case XwPackage.RESOLVED_TICKET__TWENTY_SPOT_INCLUDED:
                return isTwentySpotIncluded();
            case XwPackage.RESOLVED_TICKET__LETTER_POOL:
                return getLetterPool();
            case XwPackage.RESOLVED_TICKET__TWENTY_SPOT_PAID:
                return isTwentySpotPaid();
            case XwPackage.RESOLVED_TICKET__BONUS_VALUE:
                return getBonusValue();
            case XwPackage.RESOLVED_TICKET__PAYOUT_TIER:
                return getPayoutTier();
            case XwPackage.RESOLVED_TICKET__BOARD_COVERAGE:
                return getBoardCoverage();
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
            case XwPackage.RESOLVED_TICKET__FIXED_DETAILS:
                return fixedDetails != null;
            case XwPackage.RESOLVED_TICKET__BOARD_CONTENT:
                return BOARD_CONTENT_EDEFAULT == null ? getBoardContent() != null : !BOARD_CONTENT_EDEFAULT.equals(getBoardContent());
            case XwPackage.RESOLVED_TICKET__BONUS_WORD:
                return BONUS_WORD_EDEFAULT == null ? getBonusWord() != null : !BONUS_WORD_EDEFAULT.equals(getBonusWord());
            case XwPackage.RESOLVED_TICKET__TWENTY_SPOT_INCLUDED:
                return isTwentySpotIncluded() != TWENTY_SPOT_INCLUDED_EDEFAULT;
            case XwPackage.RESOLVED_TICKET__LETTER_POOL:
                return LETTER_POOL_EDEFAULT == null ? letterPool != null : !LETTER_POOL_EDEFAULT.equals(letterPool);
            case XwPackage.RESOLVED_TICKET__TWENTY_SPOT_PAID:
                return twentySpotPaid != TWENTY_SPOT_PAID_EDEFAULT;
            case XwPackage.RESOLVED_TICKET__BONUS_VALUE:
                return bonusValue != BONUS_VALUE_EDEFAULT;
            case XwPackage.RESOLVED_TICKET__PAYOUT_TIER:
                return payoutTier != PAYOUT_TIER_EDEFAULT;
            case XwPackage.RESOLVED_TICKET__BOARD_COVERAGE:
                return getBoardCoverage() != null;
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
        result.append(" (letterPool: ");
        result.append(letterPool);
        result.append(", twentySpotPaid: ");
        result.append(twentySpotPaid);
        result.append(", bonusValue: ");
        result.append(bonusValue);
        result.append(", payoutTier: ");
        result.append(payoutTier);
        result.append(')');
        return result.toString();
    }

} //ResolvedTicketImpl

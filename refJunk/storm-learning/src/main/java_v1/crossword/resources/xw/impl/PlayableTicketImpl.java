/**
 */
package info.jchein.sandbox.app.crossword.resources.xw.impl;

import info.jchein.sandbox.app.crossword.resources.xw.BoardCoverage;
import info.jchein.sandbox.app.crossword.resources.xw.PlayableTicket;
import info.jchein.sandbox.app.crossword.resources.xw.PrizeAnalysis;
import info.jchein.sandbox.app.crossword.resources.xw.PublishedTicketDetail;
import info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation;
import info.jchein.sandbox.app.crossword.resources.xw.XwPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Playable Ticket</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PlayableTicketImpl#getFixedDetails <em>Fixed Details</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PlayableTicketImpl#getBoardContent <em>Board Content</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PlayableTicketImpl#getBonusWord <em>Bonus Word</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PlayableTicketImpl#isTwentySpotIncluded <em>Twenty Spot Included</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PlayableTicketImpl#getDynamicInput <em>Dynamic Input</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PlayableTicketImpl#getLatestAnalysis <em>Latest Analysis</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PlayableTicketImpl#getBoardCoverage <em>Board Coverage</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.PlayableTicketImpl#isResolved <em>Resolved</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PlayableTicketImpl extends MinimalEObjectImpl.Container implements PlayableTicket {
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
     * The cached value of the '{@link #getDynamicInput() <em>Dynamic Input</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getDynamicInput()
     * @generated
     * @ordered
     */
    protected RevealedInformation dynamicInput;

    /**
     * The cached value of the '{@link #getLatestAnalysis() <em>Latest Analysis</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getLatestAnalysis()
     * @generated
     * @ordered
     */
    protected PrizeAnalysis latestAnalysis;

    /**
     * The default value of the '{@link #isResolved() <em>Resolved</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isResolved()
     * @generated
     * @ordered
     */
    protected static final boolean RESOLVED_EDEFAULT = false;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected PlayableTicketImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return XwPackage.Literals.PLAYABLE_TICKET;
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
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XwPackage.PLAYABLE_TICKET__FIXED_DETAILS, oldFixedDetails, newFixedDetails);
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
    public RevealedInformation getDynamicInput() {
        return dynamicInput;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetDynamicInput(RevealedInformation newDynamicInput, NotificationChain msgs) {
        RevealedInformation oldDynamicInput = dynamicInput;
        dynamicInput = newDynamicInput;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XwPackage.PLAYABLE_TICKET__DYNAMIC_INPUT, oldDynamicInput, newDynamicInput);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setDynamicInput(RevealedInformation newDynamicInput) {
        if (newDynamicInput != dynamicInput) {
            NotificationChain msgs = null;
            if (dynamicInput != null)
                msgs = ((InternalEObject)dynamicInput).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XwPackage.PLAYABLE_TICKET__DYNAMIC_INPUT, null, msgs);
            if (newDynamicInput != null)
                msgs = ((InternalEObject)newDynamicInput).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XwPackage.PLAYABLE_TICKET__DYNAMIC_INPUT, null, msgs);
            msgs = basicSetDynamicInput(newDynamicInput, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, XwPackage.PLAYABLE_TICKET__DYNAMIC_INPUT, newDynamicInput, newDynamicInput));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public PrizeAnalysis getLatestAnalysis() {
        return latestAnalysis;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetLatestAnalysis(PrizeAnalysis newLatestAnalysis, NotificationChain msgs) {
        PrizeAnalysis oldLatestAnalysis = latestAnalysis;
        latestAnalysis = newLatestAnalysis;
        if (eNotificationRequired()) {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XwPackage.PLAYABLE_TICKET__LATEST_ANALYSIS, oldLatestAnalysis, newLatestAnalysis);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setLatestAnalysis(PrizeAnalysis newLatestAnalysis) {
        if (newLatestAnalysis != latestAnalysis) {
            NotificationChain msgs = null;
            if (latestAnalysis != null)
                msgs = ((InternalEObject)latestAnalysis).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XwPackage.PLAYABLE_TICKET__LATEST_ANALYSIS, null, msgs);
            if (newLatestAnalysis != null)
                msgs = ((InternalEObject)newLatestAnalysis).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XwPackage.PLAYABLE_TICKET__LATEST_ANALYSIS, null, msgs);
            msgs = basicSetLatestAnalysis(newLatestAnalysis, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, XwPackage.PLAYABLE_TICKET__LATEST_ANALYSIS, newLatestAnalysis, newLatestAnalysis));
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
    public boolean isResolved() {
        // TODO: implement this method to return the 'Resolved' attribute
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
            case XwPackage.PLAYABLE_TICKET__FIXED_DETAILS:
                return basicSetFixedDetails(null, msgs);
            case XwPackage.PLAYABLE_TICKET__DYNAMIC_INPUT:
                return basicSetDynamicInput(null, msgs);
            case XwPackage.PLAYABLE_TICKET__LATEST_ANALYSIS:
                return basicSetLatestAnalysis(null, msgs);
            case XwPackage.PLAYABLE_TICKET__BOARD_COVERAGE:
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
            case XwPackage.PLAYABLE_TICKET__FIXED_DETAILS:
                return getFixedDetails();
            case XwPackage.PLAYABLE_TICKET__BOARD_CONTENT:
                return getBoardContent();
            case XwPackage.PLAYABLE_TICKET__BONUS_WORD:
                return getBonusWord();
            case XwPackage.PLAYABLE_TICKET__TWENTY_SPOT_INCLUDED:
                return isTwentySpotIncluded();
            case XwPackage.PLAYABLE_TICKET__DYNAMIC_INPUT:
                return getDynamicInput();
            case XwPackage.PLAYABLE_TICKET__LATEST_ANALYSIS:
                return getLatestAnalysis();
            case XwPackage.PLAYABLE_TICKET__BOARD_COVERAGE:
                return getBoardCoverage();
            case XwPackage.PLAYABLE_TICKET__RESOLVED:
                return isResolved();
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
            case XwPackage.PLAYABLE_TICKET__DYNAMIC_INPUT:
                setDynamicInput((RevealedInformation)newValue);
                return;
            case XwPackage.PLAYABLE_TICKET__LATEST_ANALYSIS:
                setLatestAnalysis((PrizeAnalysis)newValue);
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
            case XwPackage.PLAYABLE_TICKET__DYNAMIC_INPUT:
                setDynamicInput((RevealedInformation)null);
                return;
            case XwPackage.PLAYABLE_TICKET__LATEST_ANALYSIS:
                setLatestAnalysis((PrizeAnalysis)null);
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
            case XwPackage.PLAYABLE_TICKET__FIXED_DETAILS:
                return fixedDetails != null;
            case XwPackage.PLAYABLE_TICKET__BOARD_CONTENT:
                return BOARD_CONTENT_EDEFAULT == null ? getBoardContent() != null : !BOARD_CONTENT_EDEFAULT.equals(getBoardContent());
            case XwPackage.PLAYABLE_TICKET__BONUS_WORD:
                return BONUS_WORD_EDEFAULT == null ? getBonusWord() != null : !BONUS_WORD_EDEFAULT.equals(getBonusWord());
            case XwPackage.PLAYABLE_TICKET__TWENTY_SPOT_INCLUDED:
                return isTwentySpotIncluded() != TWENTY_SPOT_INCLUDED_EDEFAULT;
            case XwPackage.PLAYABLE_TICKET__DYNAMIC_INPUT:
                return dynamicInput != null;
            case XwPackage.PLAYABLE_TICKET__LATEST_ANALYSIS:
                return latestAnalysis != null;
            case XwPackage.PLAYABLE_TICKET__BOARD_COVERAGE:
                return getBoardCoverage() != null;
            case XwPackage.PLAYABLE_TICKET__RESOLVED:
                return isResolved() != RESOLVED_EDEFAULT;
        }
        return super.eIsSet(featureID);
    }

} //PlayableTicketImpl

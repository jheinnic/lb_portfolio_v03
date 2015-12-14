/**
 */
package info.jchein.sandbox.app.crossword.resources.xw.impl;

import info.jchein.sandbox.app.crossword.resources.xw.BonusValue;
import info.jchein.sandbox.app.crossword.resources.xw.RevealedInformation;
import info.jchein.sandbox.app.crossword.resources.xw.XwPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Revealed Information</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.RevealedInformationImpl#getLetterPool <em>
 * Letter Pool</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.RevealedInformationImpl#isTwentySpotPaid
 * <em>Twenty Spot Paid</em>}</li>
 * <li>{@link info.jchein.sandbox.app.crossword.resources.xw.impl.RevealedInformationImpl#getBonusValue <em>
 * Bonus Value</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class RevealedInformationImpl extends MinimalEObjectImpl.Container implements RevealedInformation {
    /**
     * The default value of the '{@link #getLetterPool() <em>Letter Pool</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLetterPool()
     * @generated
     * @ordered
     */
    protected static final String LETTER_POOL_EDEFAULT = "__________________";

    /**
     * The cached value of the '{@link #getLetterPool() <em>Letter Pool</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getLetterPool()
     * @generated
     * @ordered
     */
    protected String letterPool = LETTER_POOL_EDEFAULT;

    /**
     * The default value of the '{@link #isTwentySpotPaid() <em>Twenty Spot Paid</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isTwentySpotPaid()
     * @generated
     * @ordered
     */
    protected static final boolean TWENTY_SPOT_PAID_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isTwentySpotPaid() <em>Twenty Spot Paid</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #isTwentySpotPaid()
     * @generated
     * @ordered
     */
    protected boolean twentySpotPaid = TWENTY_SPOT_PAID_EDEFAULT;

    /**
     * This is true if the Twenty Spot Paid attribute has been set. <!-- begin-user-doc --> <!--
     * end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    protected boolean twentySpotPaidESet;

    /**
     * The default value of the '{@link #getBonusValue() <em>Bonus Value</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBonusValue()
     * @generated
     * @ordered
     */
    protected static final BonusValue BONUS_VALUE_EDEFAULT = BonusValue.FOUR;

    /**
     * The cached value of the '{@link #getBonusValue() <em>Bonus Value</em>}' attribute. <!--
     * begin-user-doc --> <!-- end-user-doc -->
     * 
     * @see #getBonusValue()
     * @generated
     * @ordered
     */
    protected BonusValue bonusValue = BONUS_VALUE_EDEFAULT;

    /**
     * This is true if the Bonus Value attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     * @ordered
     */
    protected boolean bonusValueESet;


    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public RevealedInformationImpl( ) {
        super();
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    protected EClass eStaticClass( ) {
        return XwPackage.Literals.REVEALED_INFORMATION;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public String getLetterPool( ) {
        return letterPool;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setLetterPool( String newLetterPool ) {
        String oldLetterPool = letterPool;
        letterPool = newLetterPool;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                XwPackage.REVEALED_INFORMATION__LETTER_POOL,
                oldLetterPool,
                letterPool));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isTwentySpotPaid( ) {
        return twentySpotPaid;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setTwentySpotPaid( boolean newTwentySpotPaid ) {
        boolean oldTwentySpotPaid = twentySpotPaid;
        twentySpotPaid = newTwentySpotPaid;
        boolean oldTwentySpotPaidESet = twentySpotPaidESet;
        twentySpotPaidESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                XwPackage.REVEALED_INFORMATION__TWENTY_SPOT_PAID,
                oldTwentySpotPaid,
                twentySpotPaid,
                !oldTwentySpotPaidESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void unsetTwentySpotPaid( ) {
        boolean oldTwentySpotPaid = twentySpotPaid;
        boolean oldTwentySpotPaidESet = twentySpotPaidESet;
        twentySpotPaid = TWENTY_SPOT_PAID_EDEFAULT;
        twentySpotPaidESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.UNSET,
                XwPackage.REVEALED_INFORMATION__TWENTY_SPOT_PAID,
                oldTwentySpotPaid,
                TWENTY_SPOT_PAID_EDEFAULT,
                oldTwentySpotPaidESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isSetTwentySpotPaid( ) {
        return twentySpotPaidESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public BonusValue getBonusValue( ) {
        return bonusValue;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void setBonusValue( BonusValue newBonusValue ) {
        BonusValue oldBonusValue = bonusValue;
        bonusValue = newBonusValue == null ? BONUS_VALUE_EDEFAULT : newBonusValue;
        boolean oldBonusValueESet = bonusValueESet;
        bonusValueESet = true;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.SET,
                XwPackage.REVEALED_INFORMATION__BONUS_VALUE,
                oldBonusValue,
                bonusValue,
                !oldBonusValueESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public void unsetBonusValue( ) {
        BonusValue oldBonusValue = bonusValue;
        boolean oldBonusValueESet = bonusValueESet;
        bonusValue = BONUS_VALUE_EDEFAULT;
        bonusValueESet = false;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(
                this,
                Notification.UNSET,
                XwPackage.REVEALED_INFORMATION__BONUS_VALUE,
                oldBonusValue,
                BONUS_VALUE_EDEFAULT,
                oldBonusValueESet));
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    public boolean isSetBonusValue( ) {
        return bonusValueESet;
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public Object eGet( int featureID, boolean resolve, boolean coreType ) {
        switch (featureID) {
            case XwPackage.REVEALED_INFORMATION__LETTER_POOL :
                return getLetterPool();
            case XwPackage.REVEALED_INFORMATION__TWENTY_SPOT_PAID :
                return isTwentySpotPaid();
            case XwPackage.REVEALED_INFORMATION__BONUS_VALUE :
                return getBonusValue();
        }
        return super.eGet(
            featureID,
            resolve,
            coreType);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eSet( int featureID, Object newValue ) {
        switch (featureID) {
            case XwPackage.REVEALED_INFORMATION__LETTER_POOL :
                setLetterPool((String) newValue);
                return;
            case XwPackage.REVEALED_INFORMATION__TWENTY_SPOT_PAID :
                setTwentySpotPaid((Boolean) newValue);
                return;
            case XwPackage.REVEALED_INFORMATION__BONUS_VALUE :
                setBonusValue((BonusValue) newValue);
                return;
        }
        super.eSet(
            featureID,
            newValue);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public void eUnset( int featureID ) {
        switch (featureID) {
            case XwPackage.REVEALED_INFORMATION__LETTER_POOL :
                setLetterPool(LETTER_POOL_EDEFAULT);
                return;
            case XwPackage.REVEALED_INFORMATION__TWENTY_SPOT_PAID :
                unsetTwentySpotPaid();
                return;
            case XwPackage.REVEALED_INFORMATION__BONUS_VALUE :
                unsetBonusValue();
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public boolean eIsSet( int featureID ) {
        switch (featureID) {
            case XwPackage.REVEALED_INFORMATION__LETTER_POOL :
                return LETTER_POOL_EDEFAULT == null
                    ? letterPool != null
                    : !LETTER_POOL_EDEFAULT.equals(letterPool);
            case XwPackage.REVEALED_INFORMATION__TWENTY_SPOT_PAID :
                return isSetTwentySpotPaid();
            case XwPackage.REVEALED_INFORMATION__BONUS_VALUE :
                return isSetBonusValue();
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc --> <!-- end-user-doc -->
     * 
     * @generated
     */
    @Override
    public String toString( ) {
        if (eIsProxy())
            return super.toString();

        StringBuffer result = new StringBuffer(
            super.toString());
        result.append(" (letterPool: ");
        result.append(letterPool);
        result.append(", twentySpotPaid: ");
        if (twentySpotPaidESet)
            result.append(twentySpotPaid);
        else
            result.append("<unset>");
        result.append(", bonusValue: ");
        if (bonusValueESet)
            result.append(bonusValue);
        else
            result.append("<unset>");
        result.append(')');
        return result.toString();
    }

} // RevealedInformationImpl
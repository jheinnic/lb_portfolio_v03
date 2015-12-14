/**
 */
package info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl;

import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.AvailableLetter;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.PrizecalcPackage;
import info.jchein.sandbox.app.crossword.prizecalc.prizecalc.TicketState;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Ticket State</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TicketStateImpl#getBonusWordIndex <em>Bonus Word Index</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TicketStateImpl#isTripled <em>Tripled</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TicketStateImpl#getWordsCompleted <em>Words Completed</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TicketStateImpl#getIncompleteWords <em>Incomplete Words</em>}</li>
 *   <li>{@link info.jchein.sandbox.app.crossword.prizecalc.prizecalc.impl.TicketStateImpl#getAvailableLetters <em>Available Letters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TicketStateImpl extends MinimalEObjectImpl.Container implements TicketState {
    /**
     * The default value of the '{@link #getBonusWordIndex() <em>Bonus Word Index</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBonusWordIndex()
     * @generated
     * @ordered
     */
    protected static final int BONUS_WORD_INDEX_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getBonusWordIndex() <em>Bonus Word Index</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getBonusWordIndex()
     * @generated
     * @ordered
     */
    protected int bonusWordIndex = BONUS_WORD_INDEX_EDEFAULT;

    /**
     * The default value of the '{@link #isTripled() <em>Tripled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isTripled()
     * @generated
     * @ordered
     */
    protected static final boolean TRIPLED_EDEFAULT = false;

    /**
     * The cached value of the '{@link #isTripled() <em>Tripled</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #isTripled()
     * @generated
     * @ordered
     */
    protected boolean tripled = TRIPLED_EDEFAULT;

    /**
     * The default value of the '{@link #getWordsCompleted() <em>Words Completed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWordsCompleted()
     * @generated
     * @ordered
     */
    protected static final int WORDS_COMPLETED_EDEFAULT = 0;

    /**
     * The cached value of the '{@link #getWordsCompleted() <em>Words Completed</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getWordsCompleted()
     * @generated
     * @ordered
     */
    protected int wordsCompleted = WORDS_COMPLETED_EDEFAULT;

    /**
     * The cached value of the '{@link #getIncompleteWords() <em>Incomplete Words</em>}' attribute list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getIncompleteWords()
     * @generated
     * @ordered
     */
    protected EList<Integer> incompleteWords;

    /**
     * The cached value of the '{@link #getAvailableLetters() <em>Available Letters</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getAvailableLetters()
     * @generated
     * @ordered
     */
    protected EList<AvailableLetter> availableLetters;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected TicketStateImpl() {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass() {
        return PrizecalcPackage.Literals.TICKET_STATE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getBonusWordIndex() {
        return bonusWordIndex;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public boolean isTripled() {
        return tripled;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public int getWordsCompleted() {
        return wordsCompleted;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<Integer> getIncompleteWords() {
        if (incompleteWords == null) {
            incompleteWords = new EDataTypeUniqueEList<Integer>(Integer.class, this, PrizecalcPackage.TICKET_STATE__INCOMPLETE_WORDS);
        }
        return incompleteWords;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<AvailableLetter> getAvailableLetters() {
        if (availableLetters == null) {
            availableLetters = new EObjectContainmentEList<AvailableLetter>(AvailableLetter.class, this, PrizecalcPackage.TICKET_STATE__AVAILABLE_LETTERS);
        }
        return availableLetters;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
        switch (featureID) {
            case PrizecalcPackage.TICKET_STATE__AVAILABLE_LETTERS:
                return ((InternalEList<?>)getAvailableLetters()).basicRemove(otherEnd, msgs);
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
            case PrizecalcPackage.TICKET_STATE__BONUS_WORD_INDEX:
                return getBonusWordIndex();
            case PrizecalcPackage.TICKET_STATE__TRIPLED:
                return isTripled();
            case PrizecalcPackage.TICKET_STATE__WORDS_COMPLETED:
                return getWordsCompleted();
            case PrizecalcPackage.TICKET_STATE__INCOMPLETE_WORDS:
                return getIncompleteWords();
            case PrizecalcPackage.TICKET_STATE__AVAILABLE_LETTERS:
                return getAvailableLetters();
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
            case PrizecalcPackage.TICKET_STATE__BONUS_WORD_INDEX:
                return bonusWordIndex != BONUS_WORD_INDEX_EDEFAULT;
            case PrizecalcPackage.TICKET_STATE__TRIPLED:
                return tripled != TRIPLED_EDEFAULT;
            case PrizecalcPackage.TICKET_STATE__WORDS_COMPLETED:
                return wordsCompleted != WORDS_COMPLETED_EDEFAULT;
            case PrizecalcPackage.TICKET_STATE__INCOMPLETE_WORDS:
                return incompleteWords != null && !incompleteWords.isEmpty();
            case PrizecalcPackage.TICKET_STATE__AVAILABLE_LETTERS:
                return availableLetters != null && !availableLetters.isEmpty();
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
        result.append(" (bonusWordIndex: ");
        result.append(bonusWordIndex);
        result.append(", tripled: ");
        result.append(tripled);
        result.append(", wordsCompleted: ");
        result.append(wordsCompleted);
        result.append(", incompleteWords: ");
        result.append(incompleteWords);
        result.append(')');
        return result.toString();
    }

} //TicketStateImpl
